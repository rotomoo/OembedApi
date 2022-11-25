package me.bi.oembedapi.service;

import me.bi.oembedapi.exception.CustomException;
import me.bi.oembedapi.exception.ErrorCode;
import org.json.simple.JSONArray;
import org.junit.Assert;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@DisplayName("oembed 기능 테스트")
class OembedServiceTest {

    @Autowired
    private OembedService oembedService;

    @Value("${oembed.providers.url}")
    private String oembedProvidersUrl;

    @Test
    void getOembedJson() {
    }

    @Test
    void urlToJsonObject() {
    }

    @Test
    void findOembedFullUrl() {
    }

    @DisplayName("Success Case: JsonArray, url의 host를 통한 oembed url 반환 테스트")
    @Test
    void findOembedUrl() {
        String url = "https://www.youtube.com/watch?v=KqNN_8msHCc";
        JSONArray jsonArray = oembedService.urlToJsonArray(oembedProvidersUrl);
        String host = oembedService.findHost(url);

        String oembedUrl = oembedService.findOembedUrl(jsonArray, host);

        assertEquals(oembedUrl, "https://www.youtube.com/oembed");
    }

    @DisplayName("Success Case: Oembed 공급 url를 통한 JsonArray를 반환 테스트")
    @Test
    void urlToJsonArray() {
        JSONArray jsonArray = oembedService.urlToJsonArray(oembedProvidersUrl);
        String substring = jsonArray.toString().substring(0, 2);

        assertEquals(substring, "[{");
    }

    @DisplayName("Success Case: 유효한 url 호스트 테스트")
    @Test
    void findHost() {
        String url = "https://www.youtube.com/watch?v=KqNN_8msHCc";

        String host = oembedService.findHost(url);

        assertEquals(host, "youtube");
    }

    @DisplayName("fail Case: 프로토콜 없는 url 호스트 테스트")
    @Test
    void findHost2() {
        String url = "www.youtube.com/watch?v=KqNN_8msHCc";

        CustomException customException = Assert.assertThrows(CustomException.class, () -> oembedService.findHost(url));

        assertEquals(ErrorCode.INVALID_PROTOCOL, customException.getErrorCode());
    }

    @DisplayName("Success Case: 유효한 url 인코딩 테스트")
    @Test
    void findEncodeUrl() {
        String url = "https://search.shopping.naver.com/search/all?where=all&frm=NVSCTAB&query=%EC%87%BC%ED%95%91";

        String encodeUrl = oembedService.findEncodeUrl(url);

        assertEquals(encodeUrl, "https%3A%2F%2Fsearch.shopping.naver.com%2Fsearch%2Fall%3Fwhere%3Dall%26frm%3DNVSCTAB%26query%3D%25EC%2587%25BC%25ED%2595%2591");
    }
}