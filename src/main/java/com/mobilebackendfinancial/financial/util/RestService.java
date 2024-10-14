package com.mobilebackendfinancial.financial.util;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.json.JSONObject;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientResponseException;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.Objects;

@Service
@RequiredArgsConstructor
@Slf4j
public class RestService {


    final RestTemplate restTemplate;

    final Environment environment;

    public <T1, T2> T2 makeRequest(String url, HttpMethod method, T1 body, Class<T2> returnType, HttpHeaders headers) throws RestClientResponseException, URISyntaxException {
        log.info("url: {}", url);

//        log.info("headers: {}", headers);
        RequestEntity<T1> requestEntity = new RequestEntity<T1>(body, headers, method, new URI(url), returnType);
        ResponseEntity<T2> response = restTemplate.exchange(requestEntity, returnType);
        log.info("response.getBody(): {}", new JSONObject(Objects.requireNonNull(response.getBody())));
        return response.getBody();
    }

    public <T1, T2> T2 makeSecuredRequest(String url, HttpMethod method, T1 body, Class<T2> returnType, HttpHeaders headers) throws RestClientResponseException, URISyntaxException {
        log.info("secured url: {}", url);
//        log.info("secured headers: {}", headers);
        RequestEntity<T1> requestEntity = new RequestEntity<T1>(body, headers, method, new URI(url), returnType);
        headers.setBearerAuth(getAccessToken(headers));
        ResponseEntity<T2> response = restTemplate.exchange(requestEntity, returnType);
        return response.getBody();
    }

    public String getAccessToken(HttpHeaders headers) {
        String authTokenUrl = environment.getProperty("auth.token.url");
        String clientId = environment.getProperty("auth.token.client-id");
        String clientSecret = environment.getProperty("auth.token.client-secret");
        String url = authTokenUrl + "?grant_type=client_credentials&client_id=" + clientId + "&client_secret=" + clientSecret;
        try {
            String response = makeRequest(url, HttpMethod.POST, null, String.class, headers);
            JSONObject responseObject = new JSONObject(response);
            log.info("Token generation successful. Expires in: {}", responseObject.optInt("expires_in"));
            return responseObject.optString("access_token");
        } catch (Exception ex) {
            log.error("Exception occured while trying to generate access token because: {}", ex.getMessage());
        }
        return null;
    }


}