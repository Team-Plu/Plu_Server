package com.th.plu.api.controller.question.dto

import com.th.plu.domain.domain.question.ElementType
import com.th.plu.domain.domain.question.QuestionResultDto
import java.time.LocalDateTime

data class QuestionResponseDto(
    val title: String,
    val content: String,
    val exposedAt: LocalDateTime,
    val characterImageUrl: String,
    val elementType: ElementType,
    val answered: Boolean,
)

internal fun toQuestionResponseDto(result: QuestionResultDto) = QuestionResponseDto(
    title = result.title,
    content = result.content,
    exposedAt = result.exposedAt,
    characterImageUrl = result.elementType.characterImageUrl,
    elementType = result.elementType,
    answered = result.answered,
)