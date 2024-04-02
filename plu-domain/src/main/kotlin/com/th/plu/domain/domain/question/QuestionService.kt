package com.th.plu.domain.domain.question

import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.time.LocalDateTime
import java.time.YearMonth

@Service
class QuestionService(
    private val questionExplorer: QuestionExplorer,
) {
    @Transactional(readOnly = true)
    fun getQuestionToday(): QuestionResultDto {
        val today = LocalDateTime.now()
        return questionExplorer.findQuestion(today).let { todayQuestion ->
            QuestionResultDto(
                questionId = todayQuestion.id,
                title = todayQuestion.title,
                content = todayQuestion.content,
                elementType = todayQuestion.elementType,
                exposedAt = todayQuestion.exposedAt,
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
                    exposedAt = it.exposedAt
                )
            }
            .sortedByDescending { it.exposedAt } // 최신순 조회

    @Transactional(readOnly = true)
    fun getYearMonthAnswered(memberId: Long) : Set<YearMonth> =
        questionExplorer.findAnsweredYearMonth(memberId)
}