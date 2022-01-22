package com.example.myapplication;

import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.BlockJUnit4ClassRunner;

//report -  http://localhost:63342/9k5egr8siwn0acnbuzdx5riw5qiwuoj9m3n6b/My%20Application/build/reports/tests/testDebugUnitTest/classes/com.example.myapplication.LifeCycleTest.html#test1Before%20class%20method
// Talk about BlockJUnit4ClassRunner, NonIgnoredRunner
//@RunWith(BlockJUnit4ClassRunner.class)
@RunWith(CustomRunner.class)
public class RunnerDemoTest {

    @Before
    public void beforeMethod() {
        System.out.printf("@Before method invoked. Instance: %s%n", this);
    }

    @After
    public void afterMethod() {
        System.out.printf("@After method invoked. Instance: %s%n", this);
    }

    @Ignore
    @Description(value = "Before class method")
    @Test
    public void test1() {
        System.out.printf("@Test method 1  invoked. Instance: %s%n", this);
//        throwError();
    }

    private void throwError() {
        throw new RuntimeException("some error");
    }
}
