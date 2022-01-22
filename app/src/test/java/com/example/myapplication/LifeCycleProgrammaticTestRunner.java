package com.example.myapplication;

import org.junit.runner.JUnitCore;

public class LifeCycleProgrammaticTestRunner {
    // Run with Coverage
    public static void main(String[] args) {
        JUnitCore junit = new JUnitCore();
        junit.run(LifeCycleTest.class);
        System.out.println("----------------");
        junit.run(LifeCycleTest.class);
    }

//    @BeforeClass static method invoked.
//    Constructor invoked. Instance: com.logicbig.example.LifeCycleTest@4a83c530
//    @Before method invoked. Instance: com.logicbig.example.LifeCycleTest@4a83c530
//    @Test method 1  invoked. Instance: com.logicbig.example.LifeCycleTest@4a83c530
//    @After method invoked. Instance: com.logicbig.example.LifeCycleTest@4a83c530
//    Constructor invoked. Instance: com.logicbig.example.LifeCycleTest@63446401
//    @Before method invoked. Instance: com.logicbig.example.LifeCycleTest@63446401
//    @Test method 2  invoked. Instance: com.logicbig.example.LifeCycleTest@63446401
//    @After method invoked. Instance: com.logicbig.example.LifeCycleTest@63446401
//    @AfterClass static method invoked.
//            ----------------
//    @BeforeClass static method invoked.
//    Constructor invoked. Instance: com.logicbig.example.LifeCycleTest@49a46be8
//    @Before method invoked. Instance: com.logicbig.example.LifeCycleTest@49a46be8
//    @Test method 1  invoked. Instance: com.logicbig.example.LifeCycleTest@49a46be8
//    @After method invoked. Instance: com.logicbig.example.LifeCycleTest@49a46be8
//    Constructor invoked. Instance: com.logicbig.example.LifeCycleTest@9806ab1
//    @Before method invoked. Instance: com.logicbig.example.LifeCycleTest@9806ab1
//    @Test method 2  invoked. Instance: com.logicbig.example.LifeCycleTest@9806ab1
//    @After method invoked. Instance: com.logicbig.example.LifeCycleTest@9806ab1
//    @AfterClass static method invoked.
}