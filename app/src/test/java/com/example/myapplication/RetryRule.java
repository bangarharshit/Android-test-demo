package com.example.myapplication;

import org.jetbrains.annotations.NotNull;
import org.junit.rules.TestRule;
import org.junit.runner.Description;
import org.junit.runners.model.Statement;

public final class RetryRule implements TestRule {

    @NotNull private Throwable[] errors = new Throwable[0];

    private int currentAttempt = 0;

    @Override public Statement apply(final Statement base, final Description description) {
        final Retry retryAnnotation = description.getAnnotation(Retry.class);
        if (retryAnnotation == null) {
            return base;
        }
        final int times = retryAnnotation.times();
        if (times <= 0) {
            throw new IllegalArgumentException(
                    "@" + Retry.class.getSimpleName() + " cannot be used with a \"times\" parameter less than 1"
            );
        }
        final long timeout = retryAnnotation.timeout();
        if (timeout < 0) {
            throw new IllegalArgumentException(
                    "@" + Retry.class.getSimpleName() + " cannot be used with a \"timeout\" parameter less than 0"
            );
        }

        errors = new Throwable[times];

        return new Statement() {
            @Override public void evaluate() throws Throwable {
                while (currentAttempt < times) {
                    try {
                        System.out.println("before base evaluate");
                        base.evaluate();
                        System.out.println("after base evaluate");
                        return;
                    } catch (Throwable t) {
                        errors[currentAttempt] = t;
                        currentAttempt++;
                        Thread.sleep(timeout);
                    }
                }
                throw RetryException.from(errors);
            }
        };
    }
}