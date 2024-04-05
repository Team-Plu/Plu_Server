package com.th.plu.api.controller.notification.dto.request

data class MessageSendRequest(var title: String, var body: String) {
    companion object {
        fun dailyQuestionMessage() = MessageSendRequest(
            title = "오늘의 질문 알림",
            body = "오늘의 질문에 답할 시간입니다."
        )
    }
}
