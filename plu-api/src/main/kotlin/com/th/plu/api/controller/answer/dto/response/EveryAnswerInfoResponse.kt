package com.th.plu.api.controller.answer.dto.response

import com.th.plu.domain.domain.question.Question

data class EveryAnswerInfoResponse(
        val questionTitle: String,
        val questionId: Long,
        val answerCount: Long,
        val characterImageUrl: String,
        val elementImageUrl: String,
        val colorCode: String,
) {
    companion object {
        fun of(question: Question, answerCount: Long): EveryAnswerInfoResponse {
            val elementType = question.elementType
            return EveryAnswerInfoResponse(
                    questionTitle = question.title,
                    questionId = question.id!!,
                    answerCount = answerCount,
                    characterImageUrl = elementType.characterImageUrl,
                    elementImageUrl = elementType.elementImageUrl,
                    colorCode = elementType.colorCode
            )
        }
    }
}
