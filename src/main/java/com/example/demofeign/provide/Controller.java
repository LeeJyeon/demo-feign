package com.example.demofeign.provide;

import jakarta.websocket.server.PathParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Random;
import java.util.UUID;

@RestController
@Slf4j
public class Controller {

    @GetMapping("/provide/{providerId}")
    public ResponseEntity<ProvideEntity> retrieve(@PathVariable String providerId) {
        log.info("Controller.retrieve");
        ProvideEntity provide = new ProvideEntity(providerId,
                new ProvideEntity.Item(UUID.randomUUID().toString(), new Random().nextLong()));
        return new ResponseEntity<>(provide, HttpStatus.OK);
    }
}
