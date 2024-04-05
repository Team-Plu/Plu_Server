package com.th.plu.api.service.question

import com.th.plu.domain.domain.answer.explorer.AnswerExplorer
import com.th.plu.domain.domain.question.QuestionResultDto
import com.th.plu.domain.domain.question.explorer.QuestionExplorer
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.time.LocalDateTime
import java.time.YearMonth

@Service
class QuestionService(
    private val questionExplorer: QuestionExplorer,
    private val answerExplorer: AnswerExplorer,
) {
    @Transactional(readOnly = true)
    fun getQuestionToday(memberId: Long): QuestionResultDto {
        val today = LocalDateTime.now()

        return questionExplorer.findQuestionDate(today).let { todayQuestion ->
            val answered = answerExplorer.hasAnswered(memberId, todayQuestion.id)

            QuestionResultDto(
                questionId = todayQuestion.id,
                title = todayQuestion.title,
                content = todayQuestion.content,
                elementType = todayQuestion.elementType,
                exposedAt = todayQuestion.exposedAt,
                answered = answered,
            )
        }
    }

    @Transactional(readOnly = true)
    fun getQuestionsAnsweredMonthly(memberId: Long, selectedYearMonth: YearMonth): List<QuestionResultDto> =
        questionExplorer.findMyQuestionsMonthly(memberId, selectedYearMonth)
            .map {
                QuestionResultDto(
                    questionId = it.id,
                    title = it.title,
                    content = it.content,
                    elementType = it.elementType,
                    exposedAt = it.exposedAt,
                    answered = true, // 내가 한 답변들
                )
            }
            .sortedByDescending { it.exposedAt } // 최신순 조회

    @Transactional(readOnly = true)
    fun getYearMonthAnswered(memberId: Long): Set<YearMonth> =
        questionExplorer.findAnsweredYearMonth(memberId)
}