package com.th.plu.domain.domain.question

import com.th.plu.common.exception.code.ErrorCode
import com.th.plu.common.exception.model.InternalServerException
import com.th.plu.domain.domain.question.repository.QuestionRepository
import org.springframework.stereotype.Component
import java.time.LocalDateTime
import java.time.YearMonth

@Component
class QuestionExplorer(
    private val questionRepository: QuestionRepository,
) {
    fun findQuestion(date: LocalDateTime): Question =
        questionRepository.findByExposedAtOrNull(date) ?: throw InternalServerException(
            ErrorCode.DATA_NOT_READY_EXCEPTION,
            "($date) 날짜의 질문데이터가 준비되지 않았습니다. "
        )

    fun findMyQuestionsMonthly(memberId: Long, yearMonth: YearMonth): List<Question> =
        questionRepository.findAllByExposedMonthIn(memberId, yearMonth)

    fun findAnsweredYearMonth(memberId: Long): Set<YearMonth> =
        questionRepository.findAllExposedAtInAnsweredMonth(memberId)
            .map { YearMonth.of(it.year, it.monthValue) }
            .toSet() // application 에서 중복 처리중, 500 넘는 warn log 발생시 월별 1건 조회하도록 쿼리 개선 필요!
}