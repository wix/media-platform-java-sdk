package com.wix.mediaplatform.http;

import org.apache.http.HttpResponse;
import org.apache.http.client.ServiceUnavailableRetryStrategy;
import org.apache.http.protocol.HttpContext;

public class RetryErrorsStrategy implements ServiceUnavailableRetryStrategy {
    private final int maxRetries;
    private final long retryIntervalMilliseconds;


    public RetryErrorsStrategy(int maxRetries, long retryIntervalMilliseconds) {
        this.maxRetries = maxRetries;
        this.retryIntervalMilliseconds = retryIntervalMilliseconds;
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
        return retryIntervalMilliseconds;
    }

}
