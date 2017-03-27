package com.wix.mediaplatform;

import com.google.gson.Gson;
import org.apache.http.client.HttpClient;
import org.apache.http.conn.ssl.NoopHostnameVerifier;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.ssl.SSLContexts;
import org.apache.http.ssl.TrustStrategy;

import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;

public abstract class BaseTest {

    protected int PORT = 8443;

    protected HttpClient httpClient;

    protected Gson gson = MediaPlatform.getGson();

    public BaseTest() {

        System.setProperty("org.apache.commons.logging.Log","org.apache.commons.logging.impl.SimpleLog");
        System.setProperty("org.apache.commons.logging.simplelog.showdatetime", "true");
        System.setProperty("org.apache.commons.logging.simplelog.log.org.apache.http.wire", "DEBUG");

        try {
            this.httpClient = HttpClients.custom()
                    .disableAuthCaching()
                    .disableCookieManagement()
                    .disableConnectionState()
                    .setMaxConnPerRoute(50)
                    .setMaxConnTotal(100)
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
    }
}
