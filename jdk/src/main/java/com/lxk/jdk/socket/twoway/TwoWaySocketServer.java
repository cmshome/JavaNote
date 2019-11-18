package com.lxk.jdk.socket.twoway;

import com.lxk.tool.thread.ThreadUtils;
import lombok.Getter;
import lombok.Setter;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.charset.StandardCharsets;

/**
 * server
 * 先运行服务端，再运行客户端，就可以看到效果。
 *
 * @author LiXuekai on 2019/10/21
 */
public class TwoWaySocketServer {

    private static final int MAX_BUFFER_SIZE = 1024;

    @Getter
    @Setter
    private int port;


    private TwoWaySocketServer(int port) {
        this.port = port;
    }

    private void runServerSingle() throws IOException {
        ServerSocket server = new ServerSocket(this.port);

        System.out.println("base socket server started.");
        // the code will block here till the request come.
        Socket socket = server.accept();

        InputStream inputStream = socket.getInputStream();

        byte[] readBytes = new byte[MAX_BUFFER_SIZE];

        int msgLen;
        StringBuilder stringBuilder = new StringBuilder();

        while ((msgLen = inputStream.read(readBytes)) != -1) {
            stringBuilder.append(new String(readBytes, 0, msgLen, StandardCharsets.UTF_8));
        }

        System.out.println("get message from client:");
        System.out.println(stringBuilder);
        // 告诉客户端接收已经完毕，之后只能发送
        socket.shutdownInput();

        // write the receipt.
        OutputStream outputStream = socket.getOutputStream();
        ThreadUtils.sleep(59000);
        String receipt = "We received your message:[" + stringBuilder.toString() + "] and send it back.";
        receipt = "00001735<?xml version=\"1.0\" encoding=\"UTF-8\"?><service>    <SysHead>        <SvcCd>60012000016</SvcCd>        <ScnCd>01</ScnCd>        <CnsmrSysNo>CnsmrSysNo</CnsmrSysNo>        <CnsmrSrlNo>CnsmrSrlNo</CnsmrSrlNo>        <BsnSrlNo>CnsmrSrlNo</BsnSrlNo>        <TxnDt>20190821</TxnDt>        <TxnTm>163045</TxnTm>        <PvdrSysNo>501500</PvdrSysNo>        <PvdrSrlNo>500102019082100001999</PvdrSrlNo>        <RetSt>S</RetSt>        <RetInfArry>            <RetCd>000000</RetCd>            <RetMsg>校验成功</RetMsg>        </RetInfArry>        <OrgnlCnsmrSysNo/>        <OrgnlTmlIndNo/>        <TmlIndNo/>        <OrgnlCnsmrSvcNo/>        <CnsmrSvcNo/>        <PvdrSvcNo/>        <UsrLng/>        <FileFlg/>        <CnlTp/>        <CnlDtlTp/>        <MACVal/>        <SvcVerNo/>        <PrtyLvl/>    </SysHead>    <AppHead>        <LglPrsnCd/>        <InstNo/>        <UsrNo/>        <UsrPswd/>        <UsrLvl/>        <UsrTp/>        <TlrSrlNo/>        <ChkFlg/>        <AuthFlg/>        <CnterTxnCd/>        <PblcPreRsrvFld1/>        <PblcPreRsrvFld2/>        <PblcPreRsrvFld3/>    </AppHead>    <Body>        <UsrCd>2671</UsrCd>        <LogonNm>admin</LogonNm>        <Email/>        <UsrNm>冯延波</UsrNm>        <PstDsc>助审</PstDsc>        <MblNo1>139*****3</MblNo1>        <MblNo2/>        <FixTel>0953-2020188</FixTel>        <EmplyWrkNo>0023090</EmplyWrkNo>        <DeptCd>0015025</DeptCd>        <DeptNo>241</DeptNo>        <RlCd>1</RlCd>        <BlngArea/>        <CommNo>4</CommNo>        <FbdUseFlg>0</FbdUseFlg>        <DelFlg>0</DelFlg>        <MblShrtNo/>        <CommNm>黄河银行</CommNm>        <PhtAdr>https://mobileoa.bankyellowriver.com/photos/f22fc04208c1a2d4d07fffb2fec98d5e</PhtAdr>    </Body></service>";
        outputStream.write(receipt.getBytes(StandardCharsets.UTF_8));

        inputStream.close();
        socket.close();
        server.close();
    }

    public static void main(String[] args) {
        TwoWaySocketServer bs = new TwoWaySocketServer(9799);
        try {
            bs.runServerSingle();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
