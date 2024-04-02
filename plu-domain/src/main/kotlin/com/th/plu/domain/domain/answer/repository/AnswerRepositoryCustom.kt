package com.th.plu.domain.domain.answer.repository

import com.th.plu.domain.domain.answer.Answer
import com.th.plu.domain.domain.answer.dto.EveryAnswerRetrieveResponse

interface AnswerRepositoryCustom {
    fun findAnswerById(id: Long): Answer?

    fun existsByMemberIdAndQuestionId(memberId: Long, questionId: Long): Boolean

    fun findEveryAnswersWithCursorAndPageSize(questionId: Long, lastAnswerId: Long, pageSize: Long): List<EveryAnswerRetrieveResponse>

    fun findPublicAnswersCountByQuestionId(questionId: Long): Long

    fun findPublicAnswersLikeTopN(questionId: Long, getCount: Long): List<EveryAnswerRetrieveResponse>
}
