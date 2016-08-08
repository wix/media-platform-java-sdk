package com.wix.mediaplatform;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.apache.http.conn.ssl.NoopHostnameVerifier;
import org.apache.http.impl.nio.client.CloseableHttpAsyncClient;
import org.apache.http.impl.nio.client.HttpAsyncClients;
import org.apache.http.ssl.SSLContexts;
import org.apache.http.ssl.TrustStrategy;

import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;

public abstract class BaseTest {

    protected int PORT = 8443;

    protected CloseableHttpAsyncClient httpClient;

    protected Gson gson = new GsonBuilder().create();

    public BaseTest() {

        try {
            this.httpClient = HttpAsyncClients.custom()
                    .disableAuthCaching()
                    .disableCookieManagement()
                    .disableConnectionState()
                    .setSSLHostnameVerifier(NoopHostnameVerifier.INSTANCE)
                    .setSSLContext(SSLContexts.custom()
                            .loadTrustMaterial(null, new TrustStrategy() {
                                @Override
                                public boolean isTrusted(X509Certificate[] chain, String authType) throws CertificateException {
                                    return true;
                                }
                            })
                            .build())
                    .build();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        this.httpClient.start();
    }
}
