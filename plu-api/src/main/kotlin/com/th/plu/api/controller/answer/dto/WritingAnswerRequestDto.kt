package com.th.plu.api.controller.answer.dto

import com.th.plu.domain.domain.answer.AnswerWriting

data class WritingAnswerRequestDto(
    val body: String,
    val settings: AnswerSettingRequestDto,
)

data class AnswerSettingRequestDto(
    val open: Boolean,
)

fun toAnswerWriting(writingAnswerRequestDto: WritingAnswerRequestDto) = AnswerWriting(
    body = writingAnswerRequestDto.body,
    open = writingAnswerRequestDto.settings.open,
)