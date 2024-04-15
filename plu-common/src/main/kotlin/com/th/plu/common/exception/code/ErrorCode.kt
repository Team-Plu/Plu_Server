package com.th.plu.common.exception.code

enum class ErrorCode(val code: String, val message: String) {

    // Validation Exception
    VALIDATION_EXCEPTION("V001", "잘못된 요청입니다."),
    METHOD_NOT_ALLOWED_EXCEPTION("V002", "지원하지 않는 메소드입니다."),
    UNSUPPORTED_MEDIA_TYPE("V003", "허용하지 않는 미디어 타입입니다."),
    VALIDATION_INVALID_TOKEN_EXCEPTION("V004", "잘못된 토큰입니다."),
    BIND_EXCEPTION("V005", "요청 값을 바인딩하는 과정에서 오류가 발생하였습니다."),
    METHOD_ARGUMENT_NOT_VALID_EXCEPTION("V006", "요청 값이 검증되지 않은 값 입니다."),
    INVALID_FORMAT_EXCEPTION("V007", "요청 값이 유효하지 않은 데이터입니다."),
    INVALID_ANSWER_OWNER("V008", "질문의 소유자가 아닙니다."),

    // Unauthorized Exception
    UNAUTHORIZED_EXCEPTION("U001", "토큰이 만료되었습니다. 다시 로그인 해주세요."),

    // Forbidden Exception
    FORBIDDEN_EXCEPTION("F001", "허용하지 않는 접근입니다."),
    FORBIDDEN_ADMIN_EXCEPTION("F002", "관리자에게만 허용된 요청입니다."),

    // NotFound Exception
    NOT_FOUND_EXCEPTION("N001", "존재하지 않습니다."),
    NOT_FOUND_MEMBER_EXCEPTION("N002", "탈퇴했거나 존재하지 않는 회원입니다."),
    NOT_FOUND_ANSWER_EXCEPTION("N003", "존재하지 않는 답변입니다."),
    NOT_FOUND_QUESTION_EXCEPTION("N004", "존재하지 않는 질문입니다."),
    NOT_FOUND_LIKE_EXCEPTION("N005", "존재하지 않는 공감 정보입니다."),
    NOT_FOUND_ENDPOINT_EXCEPTION("N006", "존재하지 않는 엔드포인트입니다."),
    NOT_FOUND_ONBOARDING_EXCEPTION("N007", "존재하지 않는 엔드포인트입니다."),

    // Conflict Exception
    CONFLICT_EXCEPTION("C001", "이미 존재합니다."),
    CONFLICT_MEMBER_EXCEPTION("C002", "이미 해당 계정으로 회원가입하셨습니다.\n로그인 해주세요."),
    CONFLICT_LIKE_EXCEPTION("C003", "이미 해당 답변에 대한 공감이 되어있습니다."),
    CONFLICT_NICKNAME_EXCEPTION("C004", "이미 사용 중인 닉네임 입니다."),
    CONFLICT_ANSWER_EXCEPTION("C005", "이미 존재한 답변이 있습니다."),

    // Internal Server Exception
    INTERNAL_SERVER_EXCEPTION("I001", "서버 내부에서 에러가 발생하였습니다."),
    DATA_NOT_READY_EXCEPTION("I002", "질문 데이터가 준비되지 않았습니다."),

    // Bad Gateway Exception
    BAD_GATEWAY_EXCEPTION("B001", "외부 연동 중 에러가 발생하였습니다."),

    // Illegal Argument Exception
    Illegal_ARGUMENT_NICKNAME_EXCEPTION("IA001", "잘못된 닉네임 형식입니다."),
    Illegal_ARGUMENT_ANSWER_COUNT_EXCEPTION("IA002", "answerCount는 비어있을 수 없습니다."),
}
