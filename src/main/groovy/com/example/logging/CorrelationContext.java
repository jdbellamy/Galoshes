package com.example.logging;

public class CorrelationContext {

    public static final String CORRELATION_ID_HEADER = "Correlation-Id";

    private static final ThreadLocal<String> id = new ThreadLocal<>();

    public static void setId(String correlationId) {
        id.set(correlationId);
    }

    public static String getId() {
        return id.get();
    }
}
