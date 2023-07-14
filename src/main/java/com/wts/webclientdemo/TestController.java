package com.wts.webclientdemo;

import org.springframework.http.*;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/test")
public class TestController {

    @GetMapping
    public ResponseEntity<?> get(){


        WebClient webClient = WebClient.create("https://dummyjson.com");

        Product productResponse = webClient.get()
                .uri("/products/1")
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .retrieve()
                .bodyToMono(Product.class).block();

        return new ResponseEntity<Product>(productResponse, HttpStatus.OK);
    }

    @GetMapping("/post-check")
    public ResponseEntity<?> create(){

        Product addProduct = Product.builder()
                .title("Test Product")
                .description("This is a Test")
                .price(100F)
                .stock(88)
                .brand("Test B")
                .build();

        WebClient webClient = WebClient.create("https://dummyjson.com");
        Product productResponse = webClient.post()
                .uri("/products/add")
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .body(Mono.just(addProduct), Product.class)
                .retrieve()
                .onStatus(HttpStatusCode::isError, response -> response.bodyToMono(String.class)
                        .flatMap(error-> Mono.error(new Exception(error)))
                )
                .bodyToMono(Product.class).block();

        return new ResponseEntity<Product>(productResponse, HttpStatus.OK);
    }

}
