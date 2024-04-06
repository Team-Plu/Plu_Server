package com.th.plu.domain.domain.question.explorer

import com.th.plu.common.exception.code.ErrorCode
import com.th.plu.common.exception.model.InternalServerException
import com.th.plu.common.exception.model.NotFoundException
import com.th.plu.domain.domain.question.Question
import com.th.plu.domain.domain.question.repository.QuestionRepository
import org.springframework.stereotype.Component
import java.time.LocalDateTime
import java.time.YearMonth

@Component
class QuestionExplorer(
    private val questionRepository: QuestionRepository,
) {
    fun findQuestion(id: Long): Question =
        questionRepository.findById(id).orElse(null)
            ?: throw NotFoundException(ErrorCode.NOT_FOUND_QUESTION_EXCEPTION, "존재하지 않는 질문 $id 입니다")

    fun findQuestionByDateTime(dateTime: LocalDateTime): Question {
        // 입력된 dateTime이 밤 10시 이후인지 확인
        val isAfterTenPM = dateTime.hour >= 22

        // 밤 10시 이후인 경우, startOfQuestionPeriod를 그 날의 밤 10시로 설정
        // 그렇지 않으면, startOfQuestionPeriod를 전날의 밤 10시로 설정
        val startOfQuestionPeriod = if (isAfterTenPM) {
            dateTime.toLocalDate().atTime(22, 0)
        } else {
            dateTime.toLocalDate().minusDays(1).atTime(22, 0)
        }

        // endOfQuestionPeriod는 startOfQuestionPeriod에서 하루를 더한 후, 밤 9시 59분 59초로 설정
        val endOfQuestionPeriod = startOfQuestionPeriod.plusDays(1).withHour(21).withMinute(59).withSecond(59)

        // 해당 기간 내의 첫 번째 질문을 조회
        return questionRepository.findByExposedAtOrNull(startOfQuestionPeriod, endOfQuestionPeriod)
            ?: throw InternalServerException(
                ErrorCode.DATA_NOT_READY_EXCEPTION,
                "($dateTime) 날짜의 질문데이터가 준비되지 않았습니다."
            )
    }

    fun findMyQuestionsMonthly(memberId: Long, yearMonth: YearMonth): List<Question> =
        questionRepository.findAllByExposedMonthIn(memberId, yearMonth)

    fun findAnsweredYearMonth(memberId: Long): Set<YearMonth> =
        questionRepository.findAllExposedAtInAnsweredMonth(memberId)
            .map { YearMonth.of(it.year, it.monthValue) }
            .toSet() // application 에서 중복 처리중, 500 넘는 warn log 발생시 월별 1건 조회하도록 쿼리 개선 필요!

    fun findTodayQuestion(): Question {
        return findQuestionByDateTime(LocalDateTime.now())
    }

}