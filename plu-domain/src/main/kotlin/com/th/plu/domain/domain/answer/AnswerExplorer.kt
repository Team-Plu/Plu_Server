package com.th.plu.domain.domain.answer

import com.th.plu.domain.domain.answer.repository.AnswerRepository
import org.springframework.stereotype.Component

@Component
class AnswerExplorer(
    private val answerRepository: AnswerRepository,
) {
    fun hasAnswered(memberId: Long, questionId: Long): Boolean {
        return answerRepository.existsByMemberIdAndQuestionId(memberId, questionId)
    }
}