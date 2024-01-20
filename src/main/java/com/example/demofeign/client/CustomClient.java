package com.example.demofeign.client;

import com.example.demofeign.provide.ProvideEntity;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name="custom", url="${feign.url}")
public interface CustomClient {

    @GetMapping("/provide/{providerId}")
    ResponseEntity<ProvideEntity> retrieve(@PathVariable String providerId);

}
