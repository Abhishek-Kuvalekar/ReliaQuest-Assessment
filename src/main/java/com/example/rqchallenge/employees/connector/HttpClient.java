package com.example.rqchallenge.employees.connector;

import com.example.rqchallenge.employees.exception.ApiException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import okhttp3.*;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Slf4j
@Component
@Scope(ConfigurableBeanFactory.SCOPE_SINGLETON)
public class HttpClient {
    private final OkHttpClient httpClient = new OkHttpClient();
    private final ObjectMapper objectMapper = new ObjectMapper();

    public<T> T get(String url, Class<T> responseClass) {
        Request request = new Request.Builder()
                            .url(url)
                            .get()
                            .build();
        return executeRequest(request, responseClass);
    }

    public<T, U> T post(String url, U requestBody, Class<T> responseClass) throws JsonProcessingException {
        RequestBody body = RequestBody.create(
                MediaType.parse("application/json"),
                objectMapper.writeValueAsString(requestBody)
        );

        Request request = new Request.Builder()
                            .url(url)
                            .post(body)
                            .build();
        return executeRequest(request, responseClass);
    }

    public<T, U> T delete(String url, U requestBody, Class<T> responseClass) throws JsonProcessingException {
        RequestBody body = null;
        if (Objects.nonNull(requestBody)) {
            body = RequestBody.create(
                    MediaType.parse("application/json"),
                    objectMapper.writeValueAsString(requestBody)
            );
        }

        Request.Builder builder = new Request.Builder().url(url);
        if (Objects.nonNull(body)) {
            builder.delete(body);
        } else {
            builder.delete();
        }

        Request request = builder.build();
        return executeRequest(request, responseClass);
    }

    private<T> T executeRequest(Request request, Class<T> responseClass) {
        ResponseBody body = null;
        Call call = httpClient.newCall(request);
        try {
            long startTime = System.currentTimeMillis();
            Response response = call.execute();
            long endTime = System.currentTimeMillis();

            if (!response.isSuccessful()) {
                log.error(
                       "Request ({} {}) failed with status: {}",
                       request.method(),
                       request.url(),
                       response.code()
               );
               throw new ApiException(response.message());
            }

            log.info(
                    "Request ({} {}) succeeded. Time taken: {}ms",
                    request.method(),
                    request.url(),
                    endTime - startTime
            );

            body = response.body();
            if (Objects.isNull(body)) {
               throw new ApiException("Null response body");
            }
            return objectMapper.readValue(body.byteStream(), responseClass);
        } catch (Exception ex) {
            log.error(
                    "Request ({} {}) failed with exception",
                    request.method(),
                    request.url(),
                    ex
            );
            throw new ApiException(ex.getMessage());
        } finally {
            if (Objects.nonNull(body)) {
                body.close();
            }
        }
    }
}
