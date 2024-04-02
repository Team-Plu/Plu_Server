package com.th.plu.domain.domain.question

import java.time.LocalDateTime

data class QuestionResultDto(
    val questionId: Long,
    val title: String,
    val content: String,
    val elementType: ElementType,
    val exposedAt: LocalDateTime,
    val answered: Boolean,
)
