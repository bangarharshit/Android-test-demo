package com.example.myapplication;

import org.junit.runners.BlockJUnit4ClassRunner;
import org.junit.runners.model.FrameworkMethod;
import org.junit.runners.model.InitializationError;
import org.junit.runners.model.TestClass;

// This runner does two things
// 1. Remove support for ignored annotation
// 2. Improve the description when the test fails
public class CustomRunner extends BlockJUnit4ClassRunner {
    public CustomRunner(Class<?> testClass) throws InitializationError {
        super(testClass);
    }

    protected CustomRunner(TestClass testClass) throws InitializationError {
        super(testClass);
    }

    @Override
    protected boolean isIgnored(FrameworkMethod child) {
        return false;
    }

    protected String testName(FrameworkMethod method) {
        if (method.getAnnotation(Description.class) != null) {
            return method.getName() + method.getAnnotation(Description.class).value();
        }
        return method.getName();
    }
}
