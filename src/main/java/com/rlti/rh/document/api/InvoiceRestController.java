/*
package com.rlti.rh.document.api;

import com.amazonaws.HttpMethod;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.GeneratePresignedUrlRequest;
import com.rlti.rh.document.api.response.UrlResponse;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RestController;

import java.time.Duration;
import java.time.Instant;
import java.util.Date;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
public class InvoiceRestController implements InvoiceApi {

    private AmazonS3  amazonS3;

    @Value("${aws.s3.bucketName}")
    private String bucketName;

    @Override
    public UrlResponse createInvoiceUrl() {
        UrlResponse urlResponse  = new UrlResponse();
        Instant expirationTime = Instant.now().plus(Duration.ofMinutes(5));
        String processId= UUID.randomUUID().toString();

        GeneratePresignedUrlRequest generatePresignedUrlRequest =
                new GeneratePresignedUrlRequest(bucketName, processId)
                        .withMethod(HttpMethod.PUT)
                        .withExpiration(Date.from(expirationTime));

        urlResponse.setExpirationTime(expirationTime.getEpochSecond());
        urlResponse.setUrl(amazonS3.generatePresignedUrl(generatePresignedUrlRequest).toString());
        return urlResponse;
    }
}
*/
