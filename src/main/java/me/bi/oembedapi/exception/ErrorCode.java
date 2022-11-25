package me.bi.oembedapi.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

import static org.springframework.http.HttpStatus.*;

@Getter
@AllArgsConstructor
public enum ErrorCode {

    INVALID_PROTOCOL(BAD_REQUEST, "잘못된 URL 형식입니다. 프로토콜을 입력해 주세요. ex)HTTP"),
    NOT_SUPPORTED_URL(BAD_REQUEST, "지원되지않는 URL 입니다."),
    INVALID_PATH(BAD_REQUEST,"잘못된 경로입니다. 알맞은 경로를 입력해 주세요 ex).com/zCun17KBF90"),
    INVALID_ACCESSTOKEN(BAD_REQUEST, "잘못된 accessToken입니다."),
    UNAUTHORIZED_CONTENT(UNAUTHORIZED, "해당 url은 oembed가 허용되지 않습니다.");

    private final HttpStatus httpStatus;
    private final String detail;
}
