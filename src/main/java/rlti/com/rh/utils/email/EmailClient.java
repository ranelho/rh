package rlti.com.rh.utils.email;

import feign.Headers;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "emailClient", url = "${emailClient.url}")
public interface EmailClient {

    @PostMapping(value = "/email", consumes = "application/json")
    @Headers("Content-Type: text/html")
    void enviarEmail(@RequestBody EmailRequest request);

}