package com.th.plu.domain.domain.answer.explorer

import com.th.plu.common.exception.code.ErrorCode
import com.th.plu.common.exception.model.NotFoundException
import com.th.plu.domain.domain.answer.Answer
import com.th.plu.domain.domain.answer.repository.AnswerRepository
import org.springframework.stereotype.Component

@Component
class AnswerExplorer(
    private val answerRepository: AnswerRepository
) {
    fun findAnswerById(id: Long): Answer {
        return answerRepository.findAnswerById(id)
            ?: throw NotFoundException(ErrorCode.NOT_FOUND_ANSWER_EXCEPTION, "존재하지 않는 답변(ID: $id) 입니다")
    }

    fun hasAnswered(memberId: Long, questionId: Long): Boolean {
        return answerRepository.existsByMemberIdAndQuestionId(memberId, questionId)
    }
}