package com.th.plu.domain.domain.answer

import com.th.plu.domain.domain.answer.repository.AnswerRepository
import com.th.plu.domain.domain.member.Member
import com.th.plu.domain.domain.question.Question
import org.springframework.stereotype.Component

@Component
class AnswerRegister(
    private val answerRepository: AnswerRepository,
) {
    // may throw unique exception
    fun registerAnswer(memberEntity: Member, questionEntity: Question, body: String, answerOpen: Boolean) =
        answerRepository.save(newAnswerInstance(memberEntity, questionEntity, body, answerOpen))
}