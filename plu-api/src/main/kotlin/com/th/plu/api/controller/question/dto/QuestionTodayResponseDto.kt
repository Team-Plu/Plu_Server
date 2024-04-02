package com.th.plu.api.controller.question.dto

import com.th.plu.domain.domain.question.QuestionResultDto

data class QuestionTodayResponseDto(
    val question: QuestionResponseDto,
)

internal fun toQuestionTodayResponseDto(result: QuestionResultDto): QuestionTodayResponseDto = QuestionTodayResponseDto(
    question = toQuestionResponseDto(result)
)