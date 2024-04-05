package com.th.plu.api.service.answer

import com.th.plu.api.controller.answer.dto.response.AnswerInfoResponse
import com.th.plu.api.controller.answer.dto.response.EveryAnswerInfoResponse
import com.th.plu.api.service.like.LikeValidator
import com.th.plu.common.exception.code.ErrorCode
import com.th.plu.common.exception.model.ConflictException
import com.th.plu.domain.domain.answer.AnswerRegister
import com.th.plu.domain.domain.answer.AnswerWriting
import com.th.plu.domain.domain.answer.WritingAnswerResult
import com.th.plu.domain.domain.answer.dto.EveryAnswerRetrieveResponses
import com.th.plu.domain.domain.answer.explorer.AnswerExplorer
import com.th.plu.domain.domain.answer.repository.AnswerRepository
import com.th.plu.domain.domain.like.Like
import com.th.plu.domain.domain.like.explorer.LikeExplorer
import com.th.plu.domain.domain.like.repository.LikeRepository
import com.th.plu.domain.domain.member.explorer.MemberExplorer
import com.th.plu.domain.domain.question.explorer.QuestionExplorer
import com.th.plu.domain.isUniqueError
import org.springframework.dao.DataIntegrityViolationException
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class AnswerService(
    private val answerExplorer: AnswerExplorer,
    private val answerRegister: AnswerRegister,
    private val answerRepository: AnswerRepository,
    private val answerValidator: AnswerValidator,
    private val likeRepository: LikeRepository,
    private val likeExplorer: LikeExplorer,
    private val likeValidator: LikeValidator,
    private val memberExplorer: MemberExplorer,
    private val questionExplorer: QuestionExplorer,
) {
    @Transactional(readOnly = true)
    fun findAnswerInfoById(answerId: Long, memberId: Long): AnswerInfoResponse {
        val answer = answerExplorer.findAnswerById(answerId)
        if (!answer.isPublic) {
            answerValidator.validateIsMemberOwnerOfAnswer(answerId, memberId)
        }
        val question = questionExplorer.findQuestion(answer.questionId)

        return AnswerInfoResponse.of(question, answer)
    }

    @Transactional
    fun createLike(memberId: Long, answerId: Long) {
        val member = memberExplorer.findMemberById(memberId)
        val answer = answerExplorer.findAnswerById(answerId)

        likeValidator.validateNotExistLike(member = member, answer = answer, question = answer.question)
        likeRepository.save(Like.newInstance(answer = answer, member = member, question = answer.question))
    }

    @Transactional
    fun deleteLike(memberId: Long, answerId: Long) {
        val member = memberExplorer.findMemberById(memberId)
        val answer = answerExplorer.findAnswerById(answerId)

        val like = likeExplorer.findLikeByMemberAndAnswerAndQuestion(member = member, answer = answer, question = answer.question)
        likeRepository.delete(like)
    }

    @Transactional(readOnly = true)
    fun findEveryAnswersWithCursor(lastAnswerId: Long, pageSize: Long): EveryAnswerRetrieveResponses {
        val todayQuestionId = questionExplorer.findTodayQuestion().id
        val answers = answerRepository.findEveryAnswersWithCursorAndPageSize(todayQuestionId, lastAnswerId, pageSize)
        return EveryAnswerRetrieveResponses(answers)
    }

    @Transactional(readOnly = true)
    fun findEveryAnswerInfo(): EveryAnswerInfoResponse {
        val todayQuestion = questionExplorer.findTodayQuestion()
        val answerCount = answerRepository.findPublicAnswersCountByQuestionId(todayQuestion.id)

        return EveryAnswerInfoResponse.of(todayQuestion, answerCount)
    }

    fun findEveryAnswersLikeTopN(getCount: Long): EveryAnswerRetrieveResponses {
        val todayQuestion = questionExplorer.findTodayQuestion()
        val answers = answerRepository.findPublicAnswersLikeTopN(todayQuestion.id, getCount)

        return EveryAnswerRetrieveResponses(answers)
    }

    @Transactional
    fun writeAnswer(memberId: Long, questionId: Long, answerWriting: AnswerWriting): WritingAnswerResult {
        // validate not found
        val memberEntity = memberExplorer.findMemberById(memberId)
        val questionEntity = questionExplorer.findQuestion(questionId)

        return try {
            answerRegister.registerAnswer(memberEntity, questionEntity, answerWriting.body, answerWriting.open).let {
                WritingAnswerResult(
                    questionId = questionEntity.id,
                    questionTitle = questionEntity.title,
                    questionContent = questionEntity.content,
                    questionExposedAt = questionEntity.exposedAt,
                    questionElementType = questionEntity.elementType,
                    questionAnswered = true,
                    answerId = it.id,
                    answerBody = it.content,
                    reactionLikeCount = 0 // 최초 생성시는 0
                )
            }
        } catch (e: DataIntegrityViolationException) {
            if (e.isUniqueError()) {
                throw ConflictException(ErrorCode.CONFLICT_ANSWER_EXCEPTION, "이미 답변한 질문에 답변을 요청했습니다.")
            } else {
                throw e
            }
        }
    }
}