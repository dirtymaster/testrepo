package com.example.vpn;

import lombok.extern.slf4j.Slf4j;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
@Slf4j
public class ProxyController {

    @PostMapping("/post")
    public String fetch(@RequestParam String url, @RequestBody String body, @RequestHeader("Authorization") String authorization) {
        String result;
        try (CloseableHttpClient httpClient = HttpClients.createDefault()) {
            log.info("url: {}", url);
            log.info("body: {}", body);
            HttpPost request = new HttpPost("https://" + url);
            request.setEntity(new StringEntity(body));
            request.setHeader("Authorization", authorization);
            request.setHeader("Content-Type", "application/json");

            try (CloseableHttpResponse response = httpClient.execute(request)) {
                result = EntityUtils.toString(response.getEntity());
            }
        } catch (IOException e) {
            return "Error: " + e.getMessage();
        }
        log.info("result: {}", result);
        return result;
    }
}
