package com.babili.mockservice.controller;

import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/docs")
public class DocumentationController {

    @GetMapping("/openapi.yaml")
    public ResponseEntity<String> getOpenApiSpec() throws IOException {
        Resource resource = new ClassPathResource("openapi.yaml");
        String content = StreamUtils.copyToString(resource.getInputStream(), StandardCharsets.UTF_8);
        
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.valueOf("application/x-yaml"));
        headers.add("Content-Disposition", "inline; filename=openapi.yaml");
        
        return ResponseEntity.ok()
                .headers(headers)
                .body(content);
    }

    @GetMapping("/openapi.json")
    public ResponseEntity<String> getOpenApiJson() throws IOException {
        // We can convert YAML to JSON for this endpoint, for now we return a simple JSON
        Map<String, Object> apiInfo = new HashMap<>();
        apiInfo.put("openapi", "3.0.3");
        
        Map<String, Object> info = new HashMap<>();
        info.put("title", "Mock Microservices API");
        info.put("description", "Microservice mock application built with WireMock");
        info.put("version", "1.0.0");
        apiInfo.put("info", info);
        
        Map<String, Object> server = new HashMap<>();
        server.put("url", "http://localhost:8080");
        server.put("description", "Local development server");
        apiInfo.put("servers", new Object[]{server});
        
        apiInfo.put("message", "Use the /docs/openapi.yaml endpoint for the complete OpenAPI JSON specification");
        
        return ResponseEntity.ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(apiInfo.toString());
    }

    @GetMapping("/")
    public ResponseEntity<String> getDocumentationIndex() {
        StringBuilder html = new StringBuilder();
        html.append("<!DOCTYPE html>");
        html.append("<html lang=\"en\">");
        html.append("<head>");
        html.append("<meta charset=\"UTF-8\">");
        html.append("<meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">");
        html.append("<title>Mock Microservices API Documentation</title>");
        html.append("<style>");
        html.append("body { font-family: Arial, sans-serif; margin: 40px; background-color: #f5f5f5; }");
        html.append(".container { max-width: 800px; margin: 0 auto; background: white; padding: 30px; border-radius: 8px; box-shadow: 0 2px 10px rgba(0,0,0,0.1); }");
        html.append("h1 { color: #333; border-bottom: 3px solid #007bff; padding-bottom: 10px; }");
        html.append("h2 { color: #555; margin-top: 30px; }");
        html.append(".endpoint { background: #f8f9fa; padding: 15px; margin: 10px 0; border-radius: 5px; border-left: 4px solid #007bff; }");
        html.append(".method { font-weight: bold; color: #007bff; }");
        html.append(".url { font-family: monospace; background: #e9ecef; padding: 2px 6px; border-radius: 3px; }");
        html.append(".description { margin-top: 5px; color: #666; }");
        html.append("a { color: #007bff; text-decoration: none; }");
        html.append("a:hover { text-decoration: underline; }");
        html.append(".download-links { background: #e7f3ff; padding: 15px; border-radius: 5px; margin: 20px 0; }");
        html.append(".status { color: #28a745; font-weight: bold; }");
        html.append("</style>");
        html.append("</head>");
        html.append("<body>");
        html.append("<div class=\"container\">");
        html.append("<h1>🚀 Mock Microservices API Documentation</h1>");
        html.append("<p>A microservice mock application built with WireMock. This API simulates 10 different microservices and can be used for testing environments.</p>");
        html.append("<div class=\"download-links\">");
        html.append("<h3>📋 API Specification</h3>");
        html.append("<p>");
        html.append("<a href=\"/docs/openapi.yaml\">📄 Download OpenAPI YAML Specification</a><br>");
        html.append("<a href=\"/docs/openapi.json\">📄 OpenAPI JSON Information</a>");
        html.append("</p>");
        html.append("</div>");
        html.append("<h2>📊 Status</h2>");
        html.append("<p class=\"status\">✅ WireMock Server: Active (Port 8080)</p>");
        html.append("<p class=\"status\">✅ Spring Boot App: Active (Port 8081)</p>");
        html.append("<h2>🔗 Available API Endpoints</h2>");
        
        // Add endpoints
        String[] endpoints = {
            "GET|/api/users/{userId}|Get user information",
            "POST|/api/orders|Create new order",
            "GET|/api/products|Get product list",
            "POST|/api/payments|Process payment",
            "GET|/api/inventory/{productId}|Get product stock information",
            "POST|/api/notifications|Send notification",
            "POST|/api/auth/login|User login",
            "GET|/api/shipping/{trackingNumber}|Check shipping status",
            "GET|/api/catalog|Get category list",
            "GET|/api/analytics|Get analytics data"
        };
        
        for (String endpoint : endpoints) {
            String[] parts = endpoint.split("\\|");
            html.append("<div class=\"endpoint\">");
            html.append("<div class=\"method\">").append(parts[0]).append("</div>");
            html.append("<div class=\"url\">").append(parts[1]).append("</div>");
            html.append("<div class=\"description\">").append(parts[2]).append("</div>");
            html.append("</div>");
        }
        
        html.append("<h2>🧪 Test Examples</h2>");
        html.append("<p>For detailed test examples and usage guide, visit the <a href=\"https://github.com/your-repo/mock-microservices\">GitHub repository</a>.</p>");
        html.append("<h2>📞 Contact</h2>");
        html.append("<p>For questions: <a href=\"mailto:support@example.com\">support@example.com</a></p>");
        html.append("</div>");
        html.append("</body>");
        html.append("</html>");
        
        return ResponseEntity.ok()
                .contentType(MediaType.TEXT_HTML)
                .body(html.toString());
    }

    @GetMapping("/health")
    public ResponseEntity<Map<String, Object>> getHealthStatus() {
        Map<String, Object> health = new HashMap<>();
        health.put("status", "UP");
        health.put("application", "Mock Microservices");
        health.put("version", "1.0.0");
        health.put("wiremock_port", 8080);
        health.put("spring_boot_port", 8081);
        health.put("documentation_available", true);
        health.put("timestamp", System.currentTimeMillis());
        
        return ResponseEntity.ok(health);
    }
}