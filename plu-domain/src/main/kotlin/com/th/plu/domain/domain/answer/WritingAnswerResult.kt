package com.th.plu.domain.domain.answer

import com.th.plu.domain.domain.question.ElementType
import java.time.LocalDateTime

data class WritingAnswerResult(
    val questionId: Long,
    val questionTitle: String,
    val questionContent: String,
    val questionExposedAt: LocalDateTime,
    val questionElementType: ElementType,
    val questionAnswered: Boolean,

    val answerId: Long,
    val answerBody: String,

    val reactionLikeCount: Long,
) {
    val characterImageUrl = questionElementType.characterImageUrl
}