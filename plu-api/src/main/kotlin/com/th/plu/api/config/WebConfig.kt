package com.th.plu.api.config

import com.th.plu.api.config.converter.YearMonthConverter
import com.th.plu.api.config.interceptor.AuthInterceptor
import com.th.plu.api.config.resolver.MemberIdResolver
import org.springframework.context.annotation.Configuration
import org.springframework.format.FormatterRegistry
import org.springframework.web.method.support.HandlerMethodArgumentResolver
import org.springframework.web.servlet.config.annotation.InterceptorRegistry
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer

@Configuration
class WebConfig(
    private val authInterceptor: AuthInterceptor,
    private val memberIdResolver: MemberIdResolver,
    private val yearMonthConverter: YearMonthConverter,
) : WebMvcConfigurer {

    override fun addInterceptors(registry: InterceptorRegistry) {
        registry.addInterceptor(authInterceptor)
    }

    override fun addArgumentResolvers(resolvers: MutableList<HandlerMethodArgumentResolver>) {
        resolvers.add(memberIdResolver)
    }

    override fun addFormatters(registry: FormatterRegistry) {
        registry.addConverter(yearMonthConverter)
    }
}
