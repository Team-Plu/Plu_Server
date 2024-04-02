package com.th.plu.domain.domain.answer.repository

import com.querydsl.jpa.impl.JPAQueryFactory
import com.th.plu.domain.domain.answer.Answer
import com.th.plu.domain.domain.answer.QAnswer.answer
import com.th.plu.domain.domain.answer.dto.EveryAnswerRetrieveResponse
import com.th.plu.domain.domain.answer.dto.QEveryAnswerRetrieveResponse
import com.th.plu.domain.domain.like.QLike.like
import org.springframework.stereotype.Repository


@Repository
class AnswerRepositoryImpl(private val queryFactory: JPAQueryFactory) : AnswerRepositoryCustom {
    override fun findAnswerById(id: Long): Answer? {
        return queryFactory
                .selectFrom(answer)
                .where(answer.id.eq(id))
                .fetchOne()
    }

    override fun findEveryAnswersWithCursorAndPageSize(questionId: Long, lastAnswerId: Long, pageSize: Long): List<EveryAnswerRetrieveResponse> {
        return queryFactory
                .select(QEveryAnswerRetrieveResponse(answer.id, like.answer.id.count(), answer.content))
                .from(answer)
                .leftJoin(like).on(like.answer.id.eq(answer.id))
                .where(
                        answer.isPublic.eq(true),
                        answer.question.id.eq(questionId),
                        answer.id.lt(lastAnswerId),
                )
                .groupBy(answer.id)
                .orderBy(answer.id.desc())
                .limit(pageSize)
                .fetch()
    }

    override fun findPublicAnswersCountByQuestionId(questionId: Long): Long {
        return queryFactory
                .select(answer.id.count())
                .from(answer)
                .where(answer.question.id.eq(questionId))
                .fetchOne()!!
    }

    override fun findPublicAnswersLikeTopN(questionId: Long, getCount: Long): List<EveryAnswerRetrieveResponse> {
        return queryFactory
                .select(QEveryAnswerRetrieveResponse(answer.id, like.answer.id.count(), answer.content))
                .from(answer)
                .leftJoin(like).on(like.answer.id.eq(answer.id))
                .where(
                        answer.isPublic.eq(true),
                        answer.question.id.eq(questionId)
                )
                .groupBy(answer.id)
                .orderBy(like.answer.id.count().desc())
                .limit(getCount)
                .fetch()
    }

    override fun existsByMemberIdAndQuestionId(memberId: Long, questionId: Long): Boolean {
        return queryFactory
            .selectFrom(answer)
            .where(
                answer.member.id.eq(memberId),
                answer.question._id.eq(questionId),
            )
            .fetchFirst() != null
    }
}
