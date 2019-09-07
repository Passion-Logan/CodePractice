package EndDefinition;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

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
        System.out.println("base socket server started.");

        this.socket = server.accept();

        this.inputStream = socket.getInputStream();
        Scanner sc = new Scanner(this.inputStream);
        while (sc.hasNextLine()) {
            System.out.println("get info from client: " + sc.nextLine());
        }

        this.inputStream.close();
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
