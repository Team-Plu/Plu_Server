package com.th.plu.domain.domain.question.repository

import com.querydsl.jpa.impl.JPAQueryFactory
import com.th.plu.common.Slf4JKotlinLogging.log
import com.th.plu.domain.domain.answer.QAnswer.answer
import com.th.plu.domain.domain.member.QMember.member
import com.th.plu.domain.domain.question.QQuestion.question
import com.th.plu.domain.domain.question.Question
import org.springframework.stereotype.Repository
import java.time.LocalDateTime
import java.time.YearMonth

@Repository
class QuestionRepositoryImpl(private val queryFactory: JPAQueryFactory) : QuestionRepositoryCustom {

    override fun findQuestionById(id: Long): Question? {
        return queryFactory
            .selectFrom(question)
            .where(question._id.eq(id))
            .fetchOne()
    }


    override fun findByExposedAtOrNull(startOfPeriod: LocalDateTime, endOfPeriod: LocalDateTime): Question? {
        return queryFactory
            .selectFrom(question)
            .where(question.exposedAt.between(startOfPeriod, endOfPeriod))
            .fetchFirst()
    }

    override fun findAllByExposedMonthIn(memberId: Long, yearMonth: YearMonth): List<Question> {
        return queryFactory
            .selectFrom(question)
            .innerJoin(member).on(member.id.eq(memberId))
            .innerJoin(answer).on(
                answer.member.id.eq(member.id),
                answer.question._id.eq(question._id),
            )
            .where(
                // start (first day of month)
                question.exposedAt.goe(
                    LocalDateTime.of(yearMonth.year, yearMonth.monthValue, 1, 0, 0)
                ),

                // end (first day of next month)
                question.exposedAt.lt(
                    yearMonth.plusMonths(1).let {
                        LocalDateTime.of(it.year, it.monthValue, 1, 0, 0)
                    },
                )
            )
            .fetch()
    }

    // 쿼리 개선 필요, from 절 sub query 필요 -> JPA 외 다른 tool 사용해야함.
    // 현재는 작성한 모든 질문의 날짜 조회됨 (full scan, 10000개 썼으면 10000개 read 됨)
    // 월별로 1개씩 조회되도록 쿼리 개선 필요!
    override fun findAllExposedAtInAnsweredMonth(memberId: Long): List<LocalDateTime> {
        return queryFactory
            .select(question.exposedAt)
            .from(question)
            .innerJoin(member).on(member.id.eq(memberId))
            .innerJoin(answer).on(
                answer.member.id.eq(member.id),
                answer.question._id.eq(question._id),
            )
            .fetch()
            .also {
                if (it.size > 500) {
                    log.warn { "작성한 질문이 500개 가 넘는 유저가 발생했습니다. 쿼리 개선이 필요합니다!" }
                }
            }
    }

}