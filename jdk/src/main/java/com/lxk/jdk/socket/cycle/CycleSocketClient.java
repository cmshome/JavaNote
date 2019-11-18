package com.lxk.jdk.socket.cycle;

import java.io.IOException;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

/**
 * client
 * 客户端一直写，server一直打印。
 *
 * @author LiXuekai on 2019/10/21
 */
public class CycleSocketClient {

    private String serverHost;
    private int serverPort;
    private Socket socket;
    private OutputStream outputStream;

    private CycleSocketClient(String host, int port) {
        this.serverHost = host;
        this.serverPort = port;
    }

    private void connectServer() throws IOException {
        this.socket = new Socket(this.serverHost, this.serverPort);
        this.outputStream = socket.getOutputStream();
    }

    private void send(String message) throws IOException {
        // we mark \n as a end of line.
        String sendMsg = message + "\r\n";
        try {
            this.outputStream.write(sendMsg.getBytes(StandardCharsets.UTF_8));
        } catch (UnsupportedEncodingException e) {
            System.out.println(e.getMessage());
        }
        //this.outputStream.close();
        //this.socket.close();
    }

    public static void main(String[] args) {
        CycleSocketClient cc = new CycleSocketClient("127.0.0.1", 9799);
        try {
            cc.connectServer();
            Scanner sc = new Scanner(System.in);
            while (sc.hasNext()) {
                String line = sc.nextLine();
                cc.send(line);
            }
        }catch (IOException e) {
            e.printStackTrace();
        }
    }
}
