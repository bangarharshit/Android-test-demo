package com.example.myapplication;

import org.junit.Rule;
import org.junit.Test;

public class RetryRuleDemoTest {

    @Rule public final RetryRule retryRule = new RetryRule();
    private static int COUNT = 0;

    @Retry(times = 4)
    @Test
    public void test1() {
        System.out.println("Test invoked");
        someFunctionWhichThrowsExceptionTwice();
    }

    private void someFunctionWhichThrowsExceptionTwice() {
        if (COUNT < 2) {
            COUNT++;
            throw new RuntimeException("method thrown an error");
        }
    }
}

//before base evaluate
//Test invoked
//before base evaluate
//Test invoked
//before base evaluate
//Test invoked
//after base evaluate
