package TwoWay;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @File Name: TwoWay
 * @Author: WQL //作者及
 * @Date: 2019/9/6 17:34//完成日期
 * @Description: // 双向通信服务端
 * @Version: v0.0.1 // 版本信息
 * @Function List: // 主要函数及其功能
 * @Others: // 其它内容的说明
 * @History: // 历史修改记录
 */
public class SocketServer
{

    private ServerSocket server;
    private Socket socket;
    private int port;
    private InputStream inputStream;
    private OutputStream outputStream;

    public int getPort()
    {
        return port;
    }

    public void setPort(int port)
    {
        this.port = port;
    }

    public SocketServer(int port) {
        this.port = port;
    }

    public void runServer() throws IOException
    {
        this.server = new ServerSocket(port);
        this.socket = server.accept();
        this.inputStream = socket.getInputStream();

        byte[] readBytes = new byte[1024];

        int msgLen;
        StringBuilder stringBuilder = new StringBuilder();

        while ((msgLen = inputStream.read(readBytes)) != -1) {
            stringBuilder.append(new String(readBytes, 0, msgLen, "UTF-8"));
        }

        System.out.println("received message: " + stringBuilder.toString());

        // 告诉客户端已经接收完毕，之后只能发送
        this.socket.shutdownInput();

        this.outputStream = this.socket.getOutputStream();
        String receipt = "We received your message: " + stringBuilder.toString();
        outputStream.write(receipt.getBytes("UTF-8"));

        this.outputStream.close();
        this.socket.close();
    }

    public static void main(String[] args)
    {
        SocketServer ss = new SocketServer(9799);

        try
        {
            ss.runServer();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }
}
