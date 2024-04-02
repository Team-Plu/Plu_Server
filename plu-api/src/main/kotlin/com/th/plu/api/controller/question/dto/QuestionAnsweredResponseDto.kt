package com.th.plu.api.controller.question.dto

import java.time.YearMonth

data class QuestionAnsweredResponseDto(
    val year: List<Int>,
    val yearMonth: List<YearMonthResponseDto>
)

data class YearMonthResponseDto(
    val year: Int,
    val month: Int,
)

internal fun toQuestionAnsweredResponse(yearMonths: Set<YearMonth>) = QuestionAnsweredResponseDto(
    year = yearMonths.map { it.year }.sortedDescending(),
    yearMonth = yearMonths.map { toYearMonthResponse(it) }
)

internal fun toYearMonthResponse(yearMonth: YearMonth) = YearMonthResponseDto(year = yearMonth.year, month = yearMonth.monthValue)