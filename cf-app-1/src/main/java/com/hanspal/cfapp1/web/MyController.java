package com.hanspal.cfapp1.web;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.UUID;

@RestController
@RequestMapping("/mytest")
public class MyController {

    @GetMapping
    public ResponseEntity<MyResponse> get() {
        MyResponse response = new MyResponse(UUID.randomUUID().toString(), new Date());
        return ResponseEntity.ok(response);
    }

}
