package com.kdt;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.Objects;

import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

public class Test {

    public static void main(String[] args) {
        String apiUrl = "https://api.vworld.kr/ned/data/getEBOfficeInfo";
        String authKey = "32313C80-CF6D-3E59-953F-930749A348A4";
        String bsnmCmpnm = "에이스 부동산";

        // RestTemplate 객체 생성
        RestTemplate restTemplate = new RestTemplate();

        // 한글 부분을 URLEncoder로 인코딩하여 URL에 추가
        String encodedBsnmCmpnm = UriComponentsBuilder.newInstance()
                .queryParam("bsnmCmpnm", URLEncoder.encode(bsnmCmpnm, StandardCharsets.UTF_8))
                .build()
                .getQuery();

        // UriComponentsBuilder를 사용하여 URL 및 매개변수 구성
        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(apiUrl)
                .queryParam("key", authKey)
                .queryParam("domain", "http://localhost:3000")
                .queryParam("sttusSeCode", "1")
                .queryParam("format", "json")
                .query(encodedBsnmCmpnm)
                .queryParam("pageSize", "10")
                .queryParam("pageNo", "1");

        try {
            ResponseEntity<String> response = restTemplate.getForEntity(builder.toUriString(), String.class);
            if (Objects.requireNonNull(response.getStatusCode()).is2xxSuccessful()) {
                System.out.println(response.getBody());
            } else {
                System.out.println("HTTP Error: " + response.getStatusCode());
                System.out.println("Response Body: " + response.getBody());
            }
        } catch (Exception e) {
            System.out.println("Exception: " + e.getMessage());
        }
    }
}