package com.example.demofeign.provide;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cloud.contract.spec.internal.HttpStatus;
import org.springframework.cloud.contract.spec.internal.MediaTypes;
import org.springframework.cloud.contract.wiremock.AutoConfigureWireMock;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.TestPropertySource;
import org.springframework.util.ResourceUtils;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import static com.github.tomakehurst.wiremock.client.WireMock.*;

@SpringBootTest
@AutoConfigureWireMock(port = 0)
@TestPropertySource(
        properties = {
                "feign.url=http://localhost:${wiremock.server.port}"
        })
class UseFeignControllerTest {

    @Autowired
    UseFeignController useFeignController;

    @Test
    void wire_mock_테스트() throws IOException {
        String providerId = "123";

        Path file = ResourceUtils.getFile("classpath:test-payload/sample.json").toPath();
        String fileContent = new String(Files.readAllBytes(file));
        fileContent = fileContent.replaceAll("\\$\\{\\{providerId\\}\\}", providerId);

        stubFor(
                get(urlEqualTo("/provide/"+providerId))
                        .willReturn(
                                aResponse()
                                        .withStatus(HttpStatus.OK)
                                        .withHeader("Content-Type", MediaTypes.APPLICATION_JSON)
                                        .withBody(fileContent)
                        )

        );

        ResponseEntity<ProvideEntity> response = useFeignController.retrieve(providerId);

        Assertions.assertEquals(providerId,response.getBody().getProviderId());
        Assertions.assertEquals(1000,response.getBody().getItem().getPrice());

    }

}