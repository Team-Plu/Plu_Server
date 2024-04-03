package com.th.plu.api

import com.th.plu.common.PluCommonRoot
import com.th.plu.domain.PluDomainRoot
import com.th.plu.external.PluExternalRoot
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.scheduling.annotation.EnableScheduling

@EnableScheduling
@SpringBootApplication(scanBasePackageClasses = [
    PluApiApplication::class,
    PluDomainRoot::class,
    PluCommonRoot::class,
    PluExternalRoot::class
])
class PluApiApplication

fun main(args: Array<String>) {
    runApplication<PluApiApplication>(*args)
}
