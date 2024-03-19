package com.th.plu.notification

import com.th.plu.common.PluCommonRoot
import com.th.plu.external.PluExternalRoot
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.scheduling.annotation.EnableScheduling

@EnableScheduling
@SpringBootApplication(scanBasePackageClasses = [
    PluNotificationApplication::class,
    PluCommonRoot::class,
    PluExternalRoot::class
])
class PluNotificationApplication

fun main(args: Array<String>) {
    runApplication<PluNotificationApplication>(*args)
}
