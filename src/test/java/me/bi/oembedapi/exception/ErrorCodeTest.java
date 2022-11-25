package me.bi.oembedapi.exception;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("ExceptionMessage 테스트")
class ErrorCodeTest {

    @DisplayName("INVALID_ACCESSTOKEN 테스트")
    @Test
    void accessToken() {
        ErrorCode errorCode = ErrorCode.INVALID_ACCESSTOKEN;
        String message = errorCode.getDetail();
        assertThat(message).isEqualTo("잘못된 accessToken입니다.");
    }

    @DisplayName("NOT_SUPPORTED_URL 테스트")
    @Test
    void notSupportedUrl() {
        ErrorCode errorCode = ErrorCode.NOT_SUPPORTED_URL;
        String message = errorCode.getDetail();
        assertThat(message).isEqualTo("지원되지않는 URL 입니다.");
    }
}