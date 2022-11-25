package me.bi.oembedapi.service;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@DisplayName("oembed 기능 테스트")
class OembedServiceTest {

    @Autowired
    private OembedService oembedService;

    @Test
    void getOembedJson() {
    }

    @Test
    void urlToJsonObject() {
    }

    @Test
    void findOembedFullUrl() {
    }

    @Test
    void findOembedUrl() {
    }

    @Test
    void urlToJsonArray() {
    }

    @Test
    void findHost() {
    }

    @DisplayName("Success Case: 유효한 url 인코딩 테스트")
    @Test
    void findEncodeUrl() {
        String url = "https://search.shopping.naver.com/search/all?where=all&frm=NVSCTAB&query=%EC%87%BC%ED%95%91";

        String encodeUrl = oembedService.findEncodeUrl(url);

        assertEquals(encodeUrl, "https%3A%2F%2Fsearch.shopping.naver.com%2Fsearch%2Fall%3Fwhere%3Dall%26frm%3DNVSCTAB%26query%3D%25EC%2587%25BC%25ED%2595%2591");
    }
}