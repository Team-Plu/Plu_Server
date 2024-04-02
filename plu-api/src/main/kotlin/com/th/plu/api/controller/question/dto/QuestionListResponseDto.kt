package com.th.plu.api.controller.question.dto

import com.th.plu.domain.domain.question.QuestionResultDto

data class QuestionListResponseDto(
    val answerCount: Int,
    val questions: List<QuestionResponseDto>,
)

internal fun toQuestionListResponseDto(resultList: List<QuestionResultDto>): QuestionListResponseDto = QuestionListResponseDto(
    answerCount = resultList.size,
    questions = resultList.map { toQuestionResponseDto(it) }
)