package com.lxk.jdk.socket.twoway;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.Socket;

/**
 * client
 * 客户端发送消息到服务端。服务器接到后，返回信息给客户端。
 *
 * @author LiXuekai on 2019/10/21
 */
public class TwoWaySocketClient {
    private static final int MAX_BUFFER_SIZE = 1024;

    private String serverHost;
    private int serverPort;
    private Socket socket;
    private OutputStream outputStream;

    private TwoWaySocketClient(String host, int port) {
        this.serverHost = host;
        this.serverPort = port;
    }

    private void connectServer() throws IOException {
        this.socket = new Socket(this.serverHost, this.serverPort);
        this.outputStream = socket.getOutputStream();
    }

    private void sendSingle(String message) throws IOException {
        try {
            this.outputStream.write(message.getBytes("UTF-8"));
            // 告诉服务器，所有的发送动作已经结束，之后只能接收
            socket.shutdownOutput();
            InputStream inputStream = socket.getInputStream();
            byte[] readBytes = new byte[MAX_BUFFER_SIZE];

            int msgLen;
            StringBuilder stringBuilder = new StringBuilder();
            while ((msgLen = inputStream.read(readBytes)) != -1) {
                stringBuilder.append(new String(readBytes, 0, msgLen, "UTF-8"));
            }
            System.out.println("client get return msg from server : " + stringBuilder.toString());
        } catch (UnsupportedEncodingException e) {
            System.out.println(e.getMessage());
        }
        this.outputStream.close();
        this.socket.close();
    }

    public static void main(String[] args) {
        TwoWaySocketClient bc = new TwoWaySocketClient("127.0.0.1", 9799);
        try {
            bc.connectServer();
            String msg = "msg from two way socket client.";
            bc.sendSingle(msg);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
