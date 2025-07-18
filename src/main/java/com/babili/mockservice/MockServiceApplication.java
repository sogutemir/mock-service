package com.babili.mockservice;

import com.github.tomakehurst.wiremock.WireMockServer;
import com.github.tomakehurst.wiremock.core.WireMockConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

@SpringBootApplication
public class MockServiceApplication {
    
    private WireMockServer wireMockServer;
    
    public static void main(String[] args) {
        SpringApplication.run(MockServiceApplication.class, args);
    }
    
    @PostConstruct
    public void startWireMock() {
        wireMockServer = new WireMockServer(
            WireMockConfiguration.options()
                .port(8080)
                .usingFilesUnderClasspath("wiremock")
        );
        wireMockServer.start();
        System.out.println("WireMock server started on port 8080");
    }
    
    @PreDestroy
    public void stopWireMock() {
        if (wireMockServer != null) {
            wireMockServer.stop();
        }
    }
}
