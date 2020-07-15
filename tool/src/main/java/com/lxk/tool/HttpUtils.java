package com.lxk.tool;

import com.google.common.base.Strings;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.apache.http.NameValuePair;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.*;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static org.apache.http.HttpStatus.SC_INTERNAL_SERVER_ERROR;
import static org.apache.http.HttpStatus.SC_OK;

/**
 * HTTPClient工具类
 *
 * @author LiXuekai on 2019/11/15
 */
public class HttpUtils {

    /**
     * 编码格式
     */
    private static final String ENCODING = "UTF-8";

    /**
     * 连接超时时间, 单位毫秒
     */
    private static final int CONNECT_TIME = 10000;

    /**
     * 请求获取数据的超时时间, 单位毫秒
     */
    private static final int SOCKET_TIME = 10000;


    /**
     * get请求(加cookie的get)
     *
     * @param url 请求路径
     */
    public static HttpClientResult doGet(String url, String tokenKey, String tokenValue) throws IOException {
        CloseableHttpClient httpClient = HttpClients.createDefault();
        HttpGet httpGet = new HttpGet(url);
        if (!Strings.isNullOrEmpty(tokenKey) && !Strings.isNullOrEmpty(tokenValue)) {
            httpGet.setHeader("Cookie", tokenKey + "=" + tokenValue);
        }
        RequestConfig requestConfig = RequestConfig.custom().setConnectTimeout(CONNECT_TIME).setSocketTimeout(SOCKET_TIME).build();
        httpGet.setConfig(requestConfig);

        CloseableHttpResponse httpResponse = null;

        try {
            return getHttpClientResult(httpClient, httpResponse, httpGet);
        } finally {
            // 释放资源
            release(httpClient, httpResponse);
        }
    }

    /**
     * get请求
     *
     * @param url 请求路径
     */
    public static HttpClientResult doGet(String url) throws IOException {
        CloseableHttpClient httpClient = HttpClients.createDefault();
        HttpGet httpGet = new HttpGet(url);
        RequestConfig requestConfig = RequestConfig.custom().setConnectTimeout(CONNECT_TIME).setSocketTimeout(SOCKET_TIME).build();
        httpGet.setConfig(requestConfig);

        CloseableHttpResponse httpResponse = null;

        try {
            return getHttpClientResult(httpClient, httpResponse, httpGet);
        } finally {
            // 释放资源
            release(httpClient, httpResponse);
        }
    }

    /**
     * post请求
     * 无请求头
     *
     * @param url    请求路径
     * @param params 请求参数
     */
    public static HttpClientResult doPost(String url, Map<String, String> params) throws IOException {
        return doPost(url, null, params);
    }

    /**
     * post请求
     *
     * @param url     请求路径
     * @param headers 请求头
     * @param params  请求参数
     */
    public static HttpClientResult doPost(String url, Map<String, String> headers, Map<String, String> params) throws IOException {
        CloseableHttpClient httpClient = HttpClients.createDefault();

        HttpPost httpPost = new HttpPost(url);

        RequestConfig requestConfig = RequestConfig.custom().setConnectTimeout(CONNECT_TIME).setSocketTimeout(SOCKET_TIME).build();

        httpPost.setConfig(requestConfig);

        // 封装请求头
        packageHeader(headers, httpPost);

        // 封装请求参数
        packageParams(params, httpPost);

        CloseableHttpResponse httpResponse = null;

        try {
            return getHttpClientResult(httpClient, httpResponse, httpPost);
        } finally {
            // 释放资源
            release(httpClient, httpResponse);
        }
    }

    /**
     * 封装请求头
     *
     * @param params  请求头参数集合
     * @param request 请求
     */
    private static void packageHeader(Map<String, String> params, HttpRequestBase request) {
        if (params != null) {
            for (Map.Entry<String, String> entry : params.entrySet()) {
                request.addHeader(entry.getKey(), entry.getValue());
            }
        }

    }

    /**
     * 封装请求参数
     *
     * @param params  请求参数集合
     * @param request 请求
     */
    private static void packageParams(Map<String, String> params, HttpEntityEnclosingRequestBase request) throws UnsupportedEncodingException {
        if (params != null) {
            List<NameValuePair> nameValuePairList = new ArrayList<>();
            for (Map.Entry<String, String> entry : params.entrySet()) {
                nameValuePairList.add(new BasicNameValuePair(entry.getKey(), entry.getValue()));
            }

            request.setEntity(new UrlEncodedFormEntity(nameValuePairList, ENCODING));
        }
    }

    /**
     * 释放资源
     */
    private static void release(CloseableHttpClient httpClient, CloseableHttpResponse httpResponse) throws IOException {
        if (httpClient != null) {
            httpClient.close();
        }

        if (httpResponse != null) {
            httpResponse.close();
        }
    }

    /**
     * 获取响应结果
     */
    private static HttpClientResult getHttpClientResult(CloseableHttpClient httpClient, CloseableHttpResponse httpResponse, HttpRequestBase request) throws IOException {
        httpResponse = httpClient.execute(request);

        if (httpResponse != null && httpResponse.getStatusLine() != null) {
            String content = null;
            if (httpResponse.getEntity() != null) {
                content = EntityUtils.toString(httpResponse.getEntity(), ENCODING);
            }
            return new HttpClientResult(httpResponse.getStatusLine().getStatusCode(), content);
        }

        return new HttpClientResult(SC_INTERNAL_SERVER_ERROR);
    }

    /**
     * 返回结果集
     * 封装response
     */
    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class HttpClientResult {

        /**
         * 响应状态码
         */
        private int code;

        /**
         * 响应数据
         */
        private String content;

        public HttpClientResult(int code) {
            this.code = code;
        }

        public boolean isOk() {
            return code == SC_OK;
        }
    }

}
