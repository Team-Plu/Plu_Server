package com.th.plu.api.controller.question

import com.th.plu.api.config.interceptor.Auth
import com.th.plu.api.config.resolver.MemberId
import com.th.plu.api.controller.question.dto.*
import com.th.plu.common.dto.response.ApiResponse
import com.th.plu.api.service.question.QuestionService
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.format.annotation.DateTimeFormat
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import java.time.YearMonth

@Tag(name = "Questions")
@RestController
class QuestionController(
    private val questionService: QuestionService,
) {
    @Auth
    @Operation(summary = "오늘의 질문")
    @GetMapping("/api/v1/questions/today")
    fun getQuestionToday(
        @MemberId memberId: Long,
    ): ApiResponse<QuestionTodayResponseDto> = questionService.getQuestionToday(memberId).let {
        ApiResponse.success(toQuestionTodayResponseDto(it))
    }

    @Auth
    @Operation(summary = "내가 답변한 질문 월별 조회")
    @GetMapping("/api/v1/questions/my")
    fun getQuestionsWhatIAnsweredMonthly(
        @MemberId memberId: Long,
        @RequestParam("yearMonth") @DateTimeFormat(pattern = "yyyyMM") selectedYearMonth: YearMonth,
    ): ApiResponse<QuestionListResponseDto> =
        questionService.getQuestionsAnsweredMonthly(memberId, selectedYearMonth).let {
            ApiResponse.success(toQuestionListResponseDto(it))
        }

    @Auth
    @Operation(summary = "답변 기록이 있는 년월 얻기")
    @GetMapping("/api/v1/questions/answeredDate")
    fun getYearMonthWhenIAnswered(
        @MemberId memberId: Long,
    ): ApiResponse<QuestionAnsweredResponseDto> =
        questionService.getYearMonthAnswered(memberId).let { ApiResponse.success(toQuestionAnsweredResponse(it)) }
}