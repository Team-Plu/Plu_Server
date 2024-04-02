package com.th.plu.api.controller.answer.dto

import com.th.plu.api.controller.question.dto.QuestionResponseDto
import com.th.plu.domain.domain.answer.WritingAnswerResult

data class WritingAnswerResponseDto(
    val question: QuestionResponseDto,
    val answer: AnswerResponseDto,
    val reaction: ReactionResponseDto,
)

internal fun toWritingAnswerResponse(writingAnswerResult: WritingAnswerResult) = WritingAnswerResponseDto(
    question = QuestionResponseDto(
        id = writingAnswerResult.questionId,
        title = writingAnswerResult.questionTitle,
        content = writingAnswerResult.questionContent,
        exposedAt = writingAnswerResult.questionExposedAt,
        elementType = writingAnswerResult.questionElementType,
        characterImageUrl = writingAnswerResult.characterImageUrl,
        answered = writingAnswerResult.questionAnswered,
    ),
    answer = AnswerResponseDto(
        id = writingAnswerResult.answerId,
        body = writingAnswerResult.answerBody,
    ),
    reaction = ReactionResponseDto(
        likeCount = writingAnswerResult.reactionLikeCount,
    )
)