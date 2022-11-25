package me.bi.oembedapi.service;

import me.bi.oembedapi.exception.CustomException;
import me.bi.oembedapi.exception.ErrorCode;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.junit.Assert;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@DisplayName("oembed 기능 테스트")
class OembedServiceTest {

    @Autowired
    private OembedService oembedService;

    @Value("${oembed.providers.url}")
    private String oembedProvidersUrl;

    @Test
    @DisplayName("Success Case: 유튜브 oembed 통합 테스트")
    void youtubeTest() {
        String searchUrl = "https://www.youtube.com/watch?v=dBD54EZIrZo";
        
        String oembedJson = oembedService.getOembedJson(searchUrl);
        
        assertEquals(oembedJson.substring(0, 1), "{");
    }

    @DisplayName("Fail Case: 없는 입력 통합 테스트")
    @Test
    void getOembedJson2() {
        String searchUrl = "";

        CustomException customException = Assert.assertThrows(CustomException.class, () -> oembedService.getOembedJson(searchUrl));

        assertEquals(ErrorCode.INVALID_PROTOCOL, customException.getErrorCode());
    }

    @DisplayName("Fail Case: 잘못된 경로 통합 테스트")
    @Test
    void getOembedJson3() {
        String searchUrl = "https://www.youtube.com";

        CustomException customException = Assert.assertThrows(CustomException.class, () -> oembedService.getOembedJson(searchUrl));

        assertEquals(ErrorCode.INVALID_PATH, customException.getErrorCode());
    }

    @DisplayName("Success Case: 전체 url을 JsonObject로 변환하는 단위 기능 테스트")
    @Test
    void urlToJsonObject() {
        String oembedFullUrl = "https://vimeo.com/api/oembed.json?url=https%3A%2F%2Fvimeo.com%2F20097015";

        JSONObject jsonObject = oembedService.urlToJsonObject(oembedFullUrl);
        String substring = jsonObject.toString().substring(0, 2);

        assertEquals(substring, "{\"");
    }

    @DisplayName("Success Case: facebook, 전체 url을 찾는 단위 기능 테스트")
    @Test
    void findOembedFullUrl() {
        String url = "https://www.instagram.com/p/BUawPlPF_Rx/";
        JSONArray jsonArray = oembedService.urlToJsonArray(oembedProvidersUrl);
        String host = oembedService.findHost(url);
        String oembedUrl = oembedService.findOembedUrl(jsonArray, host);
        String encodeUrl = oembedService.findEncodeUrl(url);

        String oembedFullUrl = oembedService.findOembedFullUrl(oembedUrl, encodeUrl);

        assertThat(oembedFullUrl).contains("https://graph.facebook.com/v10.0/instagram_oembed?url" +
                "=https%3A%2F%2Fwww.instagram.com%2Fp%2FBUawPlPF_Rx%2F&format=json&access_token=input");
    }

    @DisplayName("Success Case: vimeo, 전체 url을 찾는 단위 기능 테스트")
    @Test
    void findOembedFullUrl2() {
        String url = "https://vimeo.com/20097015";
        JSONArray jsonArray = oembedService.urlToJsonArray(oembedProvidersUrl);
        String host = oembedService.findHost(url);
        String oembedUrl = oembedService.findOembedUrl(jsonArray, host);
        String encodeUrl = oembedService.findEncodeUrl(url);

        String oembedFullUrl = oembedService.findOembedFullUrl(oembedUrl, encodeUrl);

        assertEquals(oembedFullUrl, "https://vimeo.com/api/oembed.json?url=https%3A%2F%2Fvimeo.com%2F20097015");
    }

    @DisplayName("Success Case: youtube, 전체 url을 찾는 단위 기능 테스트")
    @Test
    void findOembedFullUrl3() {
        String url = "https://www.youtube.com/watch?v=KqNN_8msHCc";
        JSONArray jsonArray = oembedService.urlToJsonArray(oembedProvidersUrl);
        String host = oembedService.findHost(url);
        String oembedUrl = oembedService.findOembedUrl(jsonArray, host);
        String encodeUrl = oembedService.findEncodeUrl(url);

        String oembedFullUrl = oembedService.findOembedFullUrl(oembedUrl, encodeUrl);

        assertEquals(oembedFullUrl, "https://www.youtube.com/oembed?url=https%3A%2F%2F" +
                "www.youtube.com%2Fwatch%3Fv%3DKqNN_8msHCc&format=json");
    }

    @DisplayName("Success Case: JsonArray, url의 host를 통한 oembed url 반환 단위 기능 테스트")
    @Test
    void findOembedUrl() {
        String url = "https://www.youtube.com/watch?v=KqNN_8msHCc";
        JSONArray jsonArray = oembedService.urlToJsonArray(oembedProvidersUrl);
        String host = oembedService.findHost(url);

        String oembedUrl = oembedService.findOembedUrl(jsonArray, host);

        assertEquals(oembedUrl, "https://www.youtube.com/oembed");
    }

    @DisplayName("Success Case: Oembed 공급 url를 통한 JsonArray를 반환 단위 기능 테스트")
    @Test
    void urlToJsonArray() {
        JSONArray jsonArray = oembedService.urlToJsonArray(oembedProvidersUrl);
        String substring = jsonArray.toString().substring(0, 2);

        assertEquals(substring, "[{");
    }

    @DisplayName("Success Case: 유효한 url 호스트 단위 기능 테스트")
    @Test
    void findHost() {
        String url = "https://www.youtube.com/watch?v=KqNN_8msHCc";

        String host = oembedService.findHost(url);

        assertEquals(host, "youtube");
    }

    @DisplayName("Fail Case: 프로토콜 없는 url 호스트 단위 기능 테스트")
    @Test
    void findHost2() {
        String url = "www.youtube.com/watch?v=KqNN_8msHCc";

        CustomException customException = Assert.assertThrows(CustomException.class, () -> oembedService.findHost(url));

        assertEquals(ErrorCode.INVALID_PROTOCOL, customException.getErrorCode());
    }

    @DisplayName("Success Case: 유효한 url 인코딩 단위 기능 테스트")
    @Test
    void findEncodeUrl() {
        String url = "https://search.shopping.naver.com/search/all?where=all&frm=NVSCTAB&query=%EC%87%BC%ED%95%91";

        String encodeUrl = oembedService.findEncodeUrl(url);

        assertEquals(encodeUrl, "https%3A%2F%2Fsearch.shopping.naver.com%2Fsearch%2Fall%3Fwhere%3Dall%26frm%3DNVSCTAB%26query%3D%25EC%2587%25BC%25ED%2595%2591");
    }
}