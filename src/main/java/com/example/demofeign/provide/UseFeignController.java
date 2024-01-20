package com.example.demofeign.provide;

import com.example.demofeign.client.CustomClient;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
public class UseFeignController {

    @Autowired
    CustomClient customClient;

    @GetMapping("/feign/provide/{providerId}")
    public ResponseEntity<ProvideEntity> retrieve(@PathVariable String providerId) {
        log.info("UseFeignController.retrieve");
        return customClient.retrieve(providerId);
    }
}

