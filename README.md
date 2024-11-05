# OembedApi
Rest Api for oembed

official reference : https://oembed.com/

## ▶ 개발 환경
	1. 개발 환경 - Spring Boot 2.7.3, Java 11
    2. 핵심 라이브러리 - Spring Web, Json-simple
    3. 기타 라이브러리 - Lombok, Devtools, Test

## ▶ 개발 순서
    1. 기능 목록 작성
    2. 기능 개발 - 단위 테스트, 통합 Exception 포함
    4. 통합 테스트 개발
    3. 컨트롤러, 뷰 구현

## ▶ 기능 목록
- [x] Oembed요청하여 Json을 반환한다.
  - [x] Oembed요청 전체url로 JsonObject객체를 반환할 수 있다.
    - [x] Oembed요청 전체url이 잘못된 경로일경우 예외가 발생한다.
    - [x] Oembed요청이 권한이 필요하면 예외가 발생한다.
    - [x] facebook Oembed요청에 accessToken이 일치하지 않으면 예외가 발생한다.
    - [x] oembed url, encodeUrl로 Oembed요청 전체url을 찾을 수 있다.
      - [x] Oembed 공급 url의 JsonArray, 찾고자하는 url의 Host를 통해 oembed url를 찾을 수 있다.
        - [x] Oembed 공급 url를 통해 JsonArray를 반환한다.
          - [x] Oembed 공급 url이 변경되면 예외가 발생한다.
        - [x] 찾고자하는 url의 Host를 찾는다.
          - [x] 잘못된 url형식이면 프로토콜 예외가 발생한다.
      - [x] 찾고자하는 url을 utf-8로 인코딩 할 수 있다.
        - [x] 잘못된 url형식이면 프로토콜 예외가 발생한다.

## ▶ 동작 영상
https://user-images.githubusercontent.com/30739181/203954276-31ff9239-a00f-41d6-9fc5-abc915d07db4.mp4
