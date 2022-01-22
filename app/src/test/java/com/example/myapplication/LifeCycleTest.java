package com.example.myapplication;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

// Show normal lifecycle
// Show lifecycle when error happens
public class LifeCycleTest {

    public LifeCycleTest() {
        System.out.printf("Constructor invoked. Instance: %s%n", this);
    }

    @BeforeClass
    public static void beforeClassMethod() {

        System.out.println("@BeforeClass static method invoked.");
    }

    @Test
    public void test1() {
        System.out.printf("@Test method 1  invoked. Instance: %s%n", this);
        //        throwError();
//        System.out.println("@Test method 1 cleanup");
    }

    private void throwError() {
        throw new RuntimeException("some error");
    }

    @Test
    public void test2() {
        System.out.printf("@Test method 2  invoked. Instance: %s%n", this);
    }

    @Before
    public void beforeMethod() {
        System.out.printf("@Before method invoked. Instance: %s%n", this);
    }

    @After
    public void afterMethod() {
        System.out.printf("@After method invoked. Instance: %s%n", this);
    }

    @AfterClass
    public static void afterClassMethod() {
        System.out.printf("@AfterClass static method invoked.%n");
    }
}

//@BeforeClass static method invoked.
//Constructor invoked. Instance: com.example.myapplication.LifeCycleTest@79e4c792
//@Before method invoked. Instance: com.example.myapplication.LifeCycleTest@79e4c792
//@Test method 1  invoked. Instance: com.example.myapplication.LifeCycleTest@79e4c792
//@After method invoked. Instance: com.example.myapplication.LifeCycleTest@79e4c792
//Constructor invoked. Instance: com.example.myapplication.LifeCycleTest@5afa3c9
//@Before method invoked. Instance: com.example.myapplication.LifeCycleTest@5afa3c9
//@Test method 2  invoked. Instance: com.example.myapplication.LifeCycleTest@5afa3c9
//@After method invoked. Instance: com.example.myapplication.LifeCycleTest@5afa3c9
