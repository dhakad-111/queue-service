package com.nimesa.tech.sub.controllers;

import com.nimesa.tech.sub.service.SubscriberService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import java.util.List;
import java.util.concurrent.ExecutionException;

@RestController
@RequestMapping("/api/v1/subscribe")
@RequiredArgsConstructor
public class SubscriberController {
    private final SubscriberService subscribe;

    @GetMapping
    public ResponseEntity<List<String>> listTopics() throws ExecutionException, InterruptedException {
        return new ResponseEntity<>(this.subscribe.getTopics(), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<List<String>> subscribe(@RequestParam String topic, @RequestParam String name){
        return new ResponseEntity<>(subscribe.subscribe(topic, name), HttpStatus.OK);
    }
}
