package com.th.plu.domain.domain.question.repository

import com.th.plu.domain.domain.question.Question
import java.time.LocalDateTime
import java.time.YearMonth
import java.util.*


interface QuestionRepositoryCustom {
    fun findQuestionById(id: Long): Question?

    fun findByExposedAtOrNull(exposedAt: LocalDateTime): Question?

    fun findAllByExposedMonthIn(memberId: Long, yearMonth: YearMonth): List<Question>

    fun findAllExposedAtInAnsweredMonth(memberId: Long): List<LocalDateTime>

    fun findTodayQuestion(): Question?
    
}
