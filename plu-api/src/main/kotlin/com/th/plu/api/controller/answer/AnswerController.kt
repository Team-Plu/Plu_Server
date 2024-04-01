package com.th.plu.api.controller.answer

import com.th.plu.api.config.interceptor.Auth
import com.th.plu.api.config.resolver.MemberId
import com.th.plu.api.controller.answer.dto.response.AnswerInfoResponse
import com.th.plu.api.controller.answer.dto.response.EveryAnswerInfoResponse
import com.th.plu.api.service.answer.AnswerService
import com.th.plu.common.dto.response.ApiResponse
import com.th.plu.domain.domain.answer.dto.EveryAnswerRetrieveResponses
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.web.bind.annotation.*

@Tag(name = "Answer")
@RestController
@RequestMapping("/api")
class AnswerController(
        private val answerService: AnswerService
) {
    @Auth
    @Operation(summary = "[인증] 질문 답변 조회")
    @GetMapping("/v1/answer/{answerId}")
    fun findAnswerInfoById(@PathVariable answerId: Long, @MemberId memberId: Long): ApiResponse<AnswerInfoResponse> {
        return ApiResponse.success(answerService.findAnswerInfoById(answerId = answerId, memberId = memberId))
    }

    @Auth
    @Operation(summary = "[인증] 질문 답변 공감")
    @PostMapping("/v1/answer/like/{answerId}")
    fun likeAnswer(@PathVariable answerId: Long, @MemberId memberId: Long): ApiResponse<Any> {
        answerService.createLike(memberId, answerId)
        return ApiResponse.success()
    }

    @Auth
    @Operation(summary = "[인증] 질문 답변 공감 취소")
    @DeleteMapping("/v1/answer/like/{answerId}")
    fun dislikeAnswer(@PathVariable answerId: Long, @MemberId memberId: Long): ApiResponse<Any> {
        answerService.deleteLike(memberId, answerId)
        return ApiResponse.success()
    }

    @Auth
    @Operation(summary = "[인증] 모두의 답변 정보 조회")
    @GetMapping("/v1/answers/info")
    fun findEveryAnswerInfo(
    ): ApiResponse<EveryAnswerInfoResponse> {
        return ApiResponse.success(answerService.findEveryAnswerInfo())
    }

    @Auth
    @Operation(summary = "[인증] 모두의 답변 조회(무한 스크롤)")
    @GetMapping("/v1/answers")
    fun paginateAnswersByCursor(
            @RequestParam(defaultValue = Long.MAX_VALUE.toString()) lastAnswerId: Long,
            @RequestParam(defaultValue = "10") pageSize: Long,
    ): ApiResponse<EveryAnswerRetrieveResponses> {
        return ApiResponse.success(answerService.findEveryAnswersWithCursor(lastAnswerId, pageSize))
    }

    @Auth
    @Operation(summary = "[인증] 모두의 답변 조회(좋아요 TopN)")
    @GetMapping("/v1/answers/popular")
    fun getAnswersAboutLikeTopN(
            @RequestParam(defaultValue = "10") getCount: Long,
    ): ApiResponse<EveryAnswerRetrieveResponses> {
        return ApiResponse.success(answerService.findEveryAnswersLikeTopN(getCount))
    }
}
