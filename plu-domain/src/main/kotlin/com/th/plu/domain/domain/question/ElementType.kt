package com.th.plu.domain.domain.question

enum class ElementType(
    val characterImageUrl: String,
    val elementImageUrl: String,
    val colorCode: String
) {
    // TODO: 엘리먼트 네이밍 체크 필요
    WATER("", "", ""),
    FIRE("", "", ""),
    GROUND("", "", ""),
    CLOUD("", "", ""),
}
