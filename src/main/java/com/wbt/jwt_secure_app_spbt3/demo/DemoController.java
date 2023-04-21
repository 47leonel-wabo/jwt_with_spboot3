package com.wbt.jwt_secure_app_spbt3.demo;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = {"/api/v1/demo"})
public class DemoController {

    @GetMapping
    public ResponseEntity<DemoResponse> demoTest() {
        return ResponseEntity.ok(new DemoResponse("Demonstration from secured endpoint!"));
    }

}
