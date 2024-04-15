package com.th.plu.domain.domain.question.repository

import com.th.plu.domain.domain.question.Question
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.YearMonth


interface QuestionRepositoryCustom {
    fun findQuestionById(id: Long): Question?

    fun findByExposedAtOrNull(startOfPeriod: LocalDateTime, endOfPeriod: LocalDateTime): Question?

    fun findAllByExposedMonthIn(memberId: Long, yearMonth: YearMonth): List<Question>

    fun findAllExposedAtInAnsweredMonth(memberId: Long): List<LocalDateTime>

}
