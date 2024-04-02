package com.th.plu.api.config.converter

import org.springframework.core.convert.converter.Converter
import org.springframework.stereotype.Component
import java.time.YearMonth
import java.time.format.DateTimeFormatter

@Component
class YearMonthConverter : Converter<String, YearMonth> {
    override fun convert(source: String): YearMonth =
        YearMonth.parse(source, DateTimeFormatter.ofPattern("yyyyMM"))
}