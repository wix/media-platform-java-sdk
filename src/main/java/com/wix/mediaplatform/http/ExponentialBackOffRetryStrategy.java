package com.wix.mediaplatform.http;

import org.apache.http.HttpResponse;
import org.apache.http.client.ServiceUnavailableRetryStrategy;
import org.apache.http.protocol.HttpContext;

public class ExponentialBackOffRetryStrategy implements ServiceUnavailableRetryStrategy {
    public static final int defaultMaxRetries = 5;
    public static final int defaultInitialWaitMilliseconds = 500;
    public static final int defaultMaxWaitMilliseconds = 5000;
    public static final int defaultBackOffFactor = 2;

    private final int maxRetries;
    private final int maxWaitMilliseconds;
    private final int backOffFactor;
    private int retryIntervalMilliseconds;

    public ExponentialBackOffRetryStrategy() {
        this(defaultMaxRetries, defaultInitialWaitMilliseconds, defaultMaxWaitMilliseconds, defaultBackOffFactor);
    }

    public ExponentialBackOffRetryStrategy(int maxRetries, int initialWaitMilliseconds, int maxWaitMilliseconds,
                                           int backOffFactor) {
        this.maxRetries = maxRetries;
        this.maxWaitMilliseconds = maxWaitMilliseconds;
        this.backOffFactor = backOffFactor;
        this.retryIntervalMilliseconds = initialWaitMilliseconds;
    }

    @Override
    public boolean retryRequest(HttpResponse httpResponse, int executionCount, HttpContext httpContext) {
        if (executionCount >= maxRetries) {
            return false;
        }

        int statusCode = httpResponse.getStatusLine().getStatusCode();
        return ((statusCode >= 500) && (statusCode <= 599)) || (statusCode == 429);
    }

    @Override
    public long getRetryInterval() {
        int currentRetryInterval = retryIntervalMilliseconds;
        retryIntervalMilliseconds = Math.min(retryIntervalMilliseconds * backOffFactor, maxWaitMilliseconds);
        return currentRetryInterval;
    }

}
