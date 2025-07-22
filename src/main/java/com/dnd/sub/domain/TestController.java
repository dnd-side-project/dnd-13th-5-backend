package com.dnd.sub.domain;

import com.dnd.sub.global.common.dto.ApiResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {
    @GetMapping
    public ApiResponse<String> test() {
        return ApiResponse.ok("10001", "test");
    }

    @GetMapping("/error")
    public ApiResponse<String> error() {
        throw new RuntimeException("error");
    }
}
