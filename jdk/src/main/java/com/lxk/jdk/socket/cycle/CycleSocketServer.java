package com.lxk.jdk.socket.cycle;

import lombok.Getter;
import lombok.Setter;

import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

/**
 * server
 * 客户端一直写，server一直打印。
 *
 * @author LiXuekai on 2019/10/21
 */
public class CycleSocketServer {

    @Getter
    @Setter
    private int port;


    private CycleSocketServer(int port) {
        this.port = port;
    }

    private void runServerSingle() throws IOException {
        ServerSocket server = new ServerSocket(this.port);

        System.out.println("base socket server started.");
        // the code will block here till the request come.
        Socket socket = server.accept();

        InputStream inputStream = socket.getInputStream();

        Scanner sc = new Scanner(inputStream);
        while (sc.hasNextLine()) {
            // 循环接收并输出消息内容
            System.out.println("get info from client: " + sc.nextLine());
        }

        inputStream.close();
        socket.close();
    }

    public static void main(String[] args) {
        CycleSocketServer bs = new CycleSocketServer(9799);
        try {
            bs.runServerSingle();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
