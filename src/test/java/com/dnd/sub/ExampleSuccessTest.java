package com.dnd.sub;

import org.junit.jupiter.api.Test;

import static org.junit.platform.commons.function.Try.success;

public class ExampleSuccessTest {

    @Test
    void successTest() {
        success("성공시킨 테스트입니다.");
    }
}
