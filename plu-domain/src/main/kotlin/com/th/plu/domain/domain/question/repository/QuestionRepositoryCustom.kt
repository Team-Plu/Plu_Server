package com.th.plu.domain.domain.question.repository

import com.th.plu.domain.domain.question.Question


interface QuestionRepositoryCustom {
    fun findQuestionById(id: Long): Question?
    fun findTodayQuestion(): Question?
}
