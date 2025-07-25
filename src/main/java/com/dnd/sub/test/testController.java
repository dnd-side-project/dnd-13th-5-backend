package com.dnd.sub.test;

import com.dnd.sub.global.dto.ApiResponse;
import com.dnd.sub.global.exception.GlobalErrorCode;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class testController {
    @GetMapping
    public ApiResponse<String> test() {
        return ApiResponse.ok("test", "test");
    }

    @GetMapping("/error")
    public ApiResponse<String> test2() {
        return ApiResponse.fail(GlobalErrorCode.NOT_FOUND);
    }
}
