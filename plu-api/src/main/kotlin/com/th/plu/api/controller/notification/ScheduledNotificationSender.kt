package com.th.plu.api.controller.notification

import com.th.plu.api.controller.notification.dto.request.MessageSendRequest
import com.th.plu.api.service.notification.NotificationService
import com.th.plu.common.Slf4JKotlinLogging.log
import org.springframework.scheduling.annotation.Scheduled
import org.springframework.stereotype.Component

@Component
class ScheduledNotificationSender(private val notificationService: NotificationService) {
    @Scheduled(cron = "0 0 22 * * ?")
    fun sendDailyNotification() {
        notificationService.sendMessageToAllMember(MessageSendRequest.DAILY_QUESTION_MESSAGE())
        log.info { "오늘의 질문 알림을 보냈습니다." }
    }
}