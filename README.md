# OembedApi
Rest Api for oembed

official reference : https://oembed.com/

## ▶ 개발 환경
	1. 개발 환경 - Spring Boot 2.7.3, Java 11
    2. 핵심 라이브러리 - Spring Web, Json-simple
    3. 기타 라이브러리 - Lombok, Devtools, Test

****

## 기능 목록
- [x] Oembed요청하여 Json을 반환한다. - OembedService.getOembedJson()
  - [x] Oembed요청 전체url로 JsonObject객체를 반환할 수 있다. - OembedService.urlToJsonObject()
    - [x] Oembed요청 전체url이 잘못된 경로일경우 예외가 발생한다. - CustomException.INVALID_PATH
    - [x] Oembed요청이 권한이 필요하면 예외가 발생한다. -  CustomException.UNAUTHORIZED_CONTENT
    - [x] facebook Oembed요청에 accessToken이 일치하지 않으면 예외가 발생한다. - CustomException.INVALID_ACCESSTOKEN
    - [x] oembed url, encodeUrl로 Oembed요청 전체url을 찾을 수 있다. - OembedService.findOembedFullUrl()
      - [x] Oembed 공급 url의 JsonArray, 찾고자하는 url의 Host를 통해 oembed url를 찾을 수 있다. - OembedService.findOembedUrl() 
        - [x] Oembed 공급 url를 통해 JsonArray를 반환한다. - OembedService.urlToJsonArray()
          - [x] Oembed 공급 url이 변경되면 예외가 발생한다. - CustomException.NOT_SUPPORTED_URL
        - [x] 찾고자하는 url의 Host를 찾는다. - OembedService.findHost()
          - [x] 잘못된 url형식이면 프로토콜 예외가 발생한다. - CustomException.INVALID_PROTOCOL
      - [x] 찾고자하는 url을 utf-8로 인코딩 할 수 있다. - OembedService.findEncodeUrl()
        - [x] 잘못된 url형식이면 프로토콜 예외가 발생한다. - CustomException.INVALID_PROTOCOL