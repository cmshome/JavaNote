package com.lxk.tool;

import javax.net.ssl.HttpsURLConnection;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.Map;

/**
 * HttpsUtils
 *
 * @author lxk
 */
public class HttpsUtils {
    private static final int TIMEOUT = 45000;
    private static final String ENCODING = "UTF-8";

    /**
     * 创建HTTP连接
     *
     * @param url              地址
     * @param method           方法
     * @param headerParameters 头信息
     * @param body             请求内容
     * @return HttpURLConnection
     * @throws Exception Exception
     */
    private static HttpURLConnection createConnection(String url, String method, Map<String, String> headerParameters, String body) throws Exception {
        URL url1 = new URL(url);
        trustAllHttpsCertificates();
        HttpURLConnection httpConnection = (HttpURLConnection) url1.openConnection();
        // 设置请求时间
        httpConnection.setConnectTimeout(TIMEOUT);
        // 设置 header
        if (headerParameters != null) {
            for (String key : headerParameters.keySet()) {
                httpConnection.setRequestProperty(key, headerParameters.get(key));
            }
        }
        httpConnection.setRequestProperty("connection", "Keep-Alive");
        httpConnection.setRequestProperty("content-Type", "application/json;charset=" + ENCODING);
        httpConnection.setRequestProperty("user-agent", "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
        // 设置请求方法
        httpConnection.setRequestMethod(method);
        httpConnection.setDoOutput(true);
        httpConnection.setDoInput(true);
        // 写query数据流
        if (!(body == null || body.trim().equals(""))) {
            OutputStream writer = httpConnection.getOutputStream();
            try {
                writer.write(body.getBytes(ENCODING));
            } finally {
                if (writer != null) {
                    writer.flush();
                    writer.close();
                }
            }
        }

        // 请求结果
        int responseCode = httpConnection.getResponseCode();
        if (responseCode != 200) {
            throw new Exception(responseCode + ":" + inputStream2String(httpConnection.getErrorStream(), ENCODING));
        }

        return httpConnection;
    }

    /**
     * POST请求
     *
     * @param address          请求地址
     * @param headerParameters 参数
     * @param body             body
     * @return String
     * @throws Exception Exception
     */
    public static String post(String address, Map<String, String> headerParameters, String body) throws Exception {
        return proxyHttpRequest(address, "POST", headerParameters, null);
    }

    /**
     * GET请求
     *
     * @param address          address
     * @param headerParameters headerParameters
     * @param bodyParameters   bodyParameters
     * @return String
     * @throws Exception Exception
     */
    public static String get(String address, Map<String, String> bodyParameters, Map<String, String> headerParameters) throws Exception {
        String httpAddress;
        if (bodyParameters != null) {
            httpAddress = address + "?" + getRequestBody(bodyParameters);
        } else {
            httpAddress = address;
        }
        return proxyHttpRequest(httpAddress, "GET", headerParameters, null);
    }

    /**
     * 读取网络文件
     *
     * @param address          address
     * @param headerParameters headerParameters
     * @param file             file
     * @return String
     * @throws Exception Exception
     */
    public static String getFile(String address, Map<String, String> headerParameters, File file) throws Exception {
        String result;

        HttpURLConnection httpConnection = null;
        try {
            httpConnection = createConnection(address, "POST", null, getRequestBody(headerParameters));
            result = readInputStream(httpConnection.getInputStream(), file);

        } finally {
            if (httpConnection != null) {
                httpConnection.disconnect();
            }

        }

        return result;
    }

    public static byte[] getFileByte(String address, Map<String, String> headerParameters) throws Exception {
        byte[] result;

        HttpURLConnection httpConnection = null;
        try {
            httpConnection = createConnection(address, "POST", null, getRequestBody(headerParameters));
            result = readInputStreamToByte(httpConnection.getInputStream());

        } finally {
            if (httpConnection != null) {
                httpConnection.disconnect();
            }
        }

        return result;
    }

    /**
     * 读取文件流
     */
    private static String readInputStream(InputStream in, File file) throws Exception {
        FileOutputStream out = null;
        ByteArrayOutputStream output = null;

        try {
            output = new ByteArrayOutputStream();
            byte[] buffer = new byte[1024];
            int len;
            while ((len = in.read(buffer)) != -1) {
                output.write(buffer, 0, len);
            }

            out = new FileOutputStream(file);
            out.write(output.toByteArray());

        } finally {
            if (output != null) {
                output.close();
            }
            if (out != null) {
                out.close();
            }
        }
        return "success";
    }

    private static byte[] readInputStreamToByte(InputStream in) throws Exception {
        FileOutputStream out = null;
        ByteArrayOutputStream output = null;
        byte[] byteFile = null;

        try {
            output = new ByteArrayOutputStream();
            byte[] buffer = new byte[1024];
            int len = 0;
            while ((len = in.read(buffer)) != -1) {
                output.write(buffer, 0, len);
            }
            byteFile = output.toByteArray();
        } finally {
            if (output != null) {
                output.close();
            }
            if (out != null) {
                out.close();
            }
        }

        return byteFile;
    }

    /**
     * HTTP请求
     *
     * @param address          地址
     * @param method           方法
     * @param headerParameters 头信息
     * @param body             请求内容
     */
    private static String proxyHttpRequest(String address, String method, Map<String, String> headerParameters, String body) throws Exception {
        String result;
        HttpURLConnection httpConnection = null;

        try {
            httpConnection = createConnection(address, method, headerParameters, body);
            String encoding = "UTF-8";
            if (httpConnection.getContentType() != null && httpConnection.getContentType().indexOf("charset=") >= 0) {
                encoding = httpConnection.getContentType().substring(httpConnection.getContentType().indexOf("charset=") + 8);
            }
            result = inputStream2String(httpConnection.getInputStream(), encoding);
        } finally {
            if (httpConnection != null) {
                httpConnection.disconnect();
            }
        }
        return result;
    }

    /**
     * 将参数化为 body
     */
    public static String getRequestBody(Map<String, String> params) {
        return getRequestBody(params, true);
    }

    /**
     * 将参数化为 body
     */
    public static String getRequestBody(Map<String, String> params, boolean urlEncode) {
        StringBuilder body = new StringBuilder();

        if (params == null) {
            return null;
        }
        for (String key : params.keySet()) {
            String value = params.get(key);

            if (urlEncode) {
                try {
                    body.append(key).append("=").append(URLEncoder.encode(value, ENCODING)).append("&");
                } catch (UnsupportedEncodingException e) {
                }
            } else {
                body.append(key).append("=").append(value).append("&");
            }
        }

        if (body.length() == 0) {
            return "";
        }
        return body.substring(0, body.length() - 1);
    }

    /**
     * 读取inputStream 到 string
     */
    private static String inputStream2String(InputStream input, String encoding) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(input, encoding));
        StringBuilder result = new StringBuilder();
        String temp = null;
        while ((temp = reader.readLine()) != null) {
            result.append(temp);
        }

        return result.toString();

    }


    /**
     * 设置 https 请求
     */
    private static void trustAllHttpsCertificates() throws Exception {
        HttpsURLConnection.setDefaultHostnameVerifier((str, session) -> true);
        javax.net.ssl.TrustManager[] trustAllCerts = new javax.net.ssl.TrustManager[1];
        javax.net.ssl.TrustManager tm = new MiTM();
        trustAllCerts[0] = tm;
        javax.net.ssl.SSLContext sc = javax.net.ssl.SSLContext.getInstance("SSL");
        sc.init(null, trustAllCerts, null);
        HttpsURLConnection.setDefaultSSLSocketFactory(sc.getSocketFactory());
    }



}

/**
 * 设置 https 请求证书
 */
class MiTM implements javax.net.ssl.TrustManager, javax.net.ssl.X509TrustManager {

    @Override
    public java.security.cert.X509Certificate[] getAcceptedIssuers() {
        return null;
    }

    public boolean isServerTrusted(java.security.cert.X509Certificate[] certs) {
        return true;
    }

    public boolean isClientTrusted(java.security.cert.X509Certificate[] certs) {
        return true;
    }

    @Override
    public void checkServerTrusted(java.security.cert.X509Certificate[] certs, String authType) {
    }

    @Override
    public void checkClientTrusted(java.security.cert.X509Certificate[] certs, String authType) {
    }
}
