package com.nimesa.tech.pub.controllers;

import com.nimesa.tech.pub.service.PublisherService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@RestController
@RequestMapping("/api/v1/publish")
@RequiredArgsConstructor
public class PublisherController {
    private final PublisherService publishService;

    @PostMapping
    public ResponseEntity<String> message(@RequestParam String message, @RequestParam("topic-name") String topicName){
        return new ResponseEntity<>(this.publishService.message(message, topicName), HttpStatus.CREATED);
    }

}
