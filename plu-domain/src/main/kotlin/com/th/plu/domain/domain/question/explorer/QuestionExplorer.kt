package com.th.plu.domain.domain.answer.explorer

import com.th.plu.common.exception.code.ErrorCode
import com.th.plu.common.exception.model.NotFoundException
import com.th.plu.domain.domain.question.Question
import com.th.plu.domain.domain.question.repository.QuestionRepository
import org.springframework.stereotype.Component

@Component
class QuestionExplorer(
        private val questionRepository: QuestionRepository
) {
    fun findQuestionById(id: Long): Question {
        return questionRepository.findQuestionById(id)
                ?: throw NotFoundException(ErrorCode.NOT_FOUND_QUESTION_EXCEPTION, "존재하지 않는 질문(ID: $id) 입니다")
    }

    fun findTodayQuestion(): Question {
        return questionRepository.findTodayQuestion()
                ?: throw NotFoundException(ErrorCode.NOT_FOUND_QUESTION_EXCEPTION, "오늘의 질문이 존재하지 않습니다")
    }
}