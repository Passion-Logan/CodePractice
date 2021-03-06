package EndDefinition;

import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;
import java.util.Scanner;

/**
 * @File Name: TwoWay
 * @Author: WQL //作者及
 * @Date: 2019/9/6 21:34//完成日期
 * @Description: // 双向通信客户端
 * @Version: v0.0.1 // 版本信息
 * @Function List: // 主要函数及其功能
 * @Others: // 其它内容的说明
 * @History: // 历史修改记录
 */
public class SocketClient
{
    private String serverHost;
    private int serverPort;
    private Socket socket;
    private OutputStream outputStream;

    public SocketClient(String host, int port) {
        this.serverHost = host;
        this.serverPort = port;
    }

    public void connetServer() throws IOException
    {
        this.socket = new Socket(this.serverHost, this.serverPort);
        this.outputStream = socket.getOutputStream();
    }

    public void sendMessage(String message) throws IOException
    {
        String sendMsg = message + "\n";
        try
        {
            this.outputStream.write(sendMsg.getBytes("UTF-8"));
        }
        catch (IOException e)
        {
            System.out.println(e.getMessage());
        }
        this.outputStream.close();
        this.socket.close();
    }

    public static void main(String[] args)
    {
        SocketClient sc = new SocketClient("localhost", 9799);
        try
        {
            sc.connetServer();
            Scanner s = new Scanner(System.in);
            while (s.hasNext()) {
                String line = s.nextLine();
                sc.sendMessage(line);
            }
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }
}
