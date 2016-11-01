package com.wowchina.util;

import org.apache.commons.lang.StringUtils;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.StatusLine;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.config.ConnectionConfig;
import org.apache.http.config.SocketConfig;
import org.apache.http.conn.ConnectionKeepAliveStrategy;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.protocol.HttpContext;
import org.apache.http.util.EntityUtils;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;

public class Test2 {

    private static final PoolingHttpClientConnectionManager mgr;

    static {
        final ConnectionConfig connectionConfig = ConnectionConfig.custom()
                .setBufferSize(8 * 1024)
                .setFragmentSizeHint(8 * 1024)
                .build();
        final SocketConfig socketConfig = SocketConfig.custom()
                .setSoTimeout(15000)
                .build();
        mgr = new PoolingHttpClientConnectionManager();
        mgr.setDefaultSocketConfig(socketConfig);
        mgr.setDefaultConnectionConfig(connectionConfig);
        mgr.setMaxTotal(2000);
        mgr.setDefaultMaxPerRoute(100);
    }

	public static void main(String[] args) throws IOException {
        int tryNum = 0;
        while (tryNum++ <= 5) {
            HttpGet httpGet = new HttpGet("http://mp.weixin.qq.com/s?__biz=MjM5MDEzNzA5Ng==&mid=2654775565&idx=1&sn=2d0bf73202e26018cc2d9bce5ab4acd1&chksm=bd81676a8af6ee7c791fad0985a367d507899ea8c63f24f92c9f1f3967d1aa326c84f03942e9&mpshare=1&scene=1&srcid=1019IuA4ywvHuiroliMoqPZl#rd");
            RequestConfig.Builder configBuilder = RequestConfig.custom();

            configBuilder.setConnectTimeout(4000);
            configBuilder.setSocketTimeout(10000);
            configBuilder.setConnectionRequestTimeout(1);
            HttpClientBuilder builder = HttpClients.custom().setConnectionManager(mgr).setConnectionManagerShared(true)
                    .setDefaultRequestConfig(configBuilder.build());
            builder.setKeepAliveStrategy(new ConnectionKeepAliveStrategy(){
                @Override
                public long getKeepAliveDuration(HttpResponse httpResponse, HttpContext httpContext) {
                    return 5 * 1000; // keepAliveTimeout设为5s
                }
            });
            CloseableHttpClient httpClient =  builder.build();
            try {
                CloseableHttpResponse response = httpClient.execute(httpGet);
                try {
                    HttpEntity responseEntity = response.getEntity();
                    StatusLine statusLine = response.getStatusLine();
                    int code = statusLine.getStatusCode();
                    String contentType = responseEntity.getContentType() == null ? "":responseEntity.getContentType().getValue();
                    String contentEncoding = responseEntity.getContentEncoding() == null ?"":responseEntity.getContentEncoding().getValue();
                    long contentLength = responseEntity.getContentLength();
                    System.out.println(code + contentType+contentEncoding+ contentLength);
                    String content = EntityUtils.toString(responseEntity, "utf-8");
                } finally {
                    response.close();
                }
                Thread.sleep(1000);
            } catch (Exception ex) {
                ex.printStackTrace();
                break;
            }
        }
    }
}
