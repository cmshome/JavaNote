package com.lxk.jdk.socket.single;

import java.io.IOException;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.Socket;
import java.nio.charset.StandardCharsets;

/**
 * client
 * 客户端发送消息到服务端。单向的
 *
 * @author LiXuekai on 2019/10/21
 */
public class BaseSocketClient {

    private String serverHost;
    private int serverPort;
    private Socket socket;
    private OutputStream outputStream;

    private BaseSocketClient(String host, int port) {
        this.serverHost = host;
        this.serverPort = port;
    }

    private void connectServer() throws IOException {
        this.socket = new Socket(this.serverHost, this.serverPort);
        this.outputStream = socket.getOutputStream();
    }

    private void sendSingle(String message) throws IOException {
        try {
            this.outputStream.write(message.getBytes(StandardCharsets.UTF_8));
        } catch (UnsupportedEncodingException e) {
            System.out.println(e.getMessage());
        }
        this.outputStream.close();
        this.socket.close();
    }

    public static void main(String[] args) {
        BaseSocketClient bc = new BaseSocketClient("127.0.0.1", 9799);
        try {
            bc.connectServer();
            String msg = "msg from socket client.";
            bc.sendSingle(msg);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
