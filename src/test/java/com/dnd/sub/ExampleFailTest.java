package com.dnd.sub;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.fail;

public class ExampleFailTest {

    @Test
    void failTest() {
        fail("의도적으로 실패시킨 테스트입니다22.");
    }
}
