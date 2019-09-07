package EndDefinition;

import java.io.*;
import java.net.Socket;

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
    private InputStream inputStream;
    private BufferedReader br;
    private BufferedWriter bw;

    public SocketClient(String host, int port) {
        this.serverHost = host;
        this.serverPort = port;
    }

    public void connetServer() throws IOException
    {
        this.socket = new Socket(this.serverHost, this.serverPort);
        //this.outputStream = socket.getOutputStream();
        this.bw = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
    }

    public void sendMessage(String message) throws IOException
    {
        try
        {
            this.bw.write(message);
            //this.outputStream.write(message.getBytes("UTF-8"));
            // 告诉服务器，所有的发送动作已经结束，之后只能接收
            this.socket.shutdownOutput();

            //this.inputStream = socket.getInputStream();
            this.br = new BufferedReader(new InputStreamReader(this.socket.getInputStream()));

            String str;
            StringBuilder receipt = new StringBuilder();

            while ((str = br.readLine()) != null) {
                receipt.append(str);
            }

            /*byte[] readBytes = new byte[1024];

            int msgLen;
            StringBuilder receipt = new StringBuilder();

            while ((msgLen = inputStream.read(readBytes)) != -1) {
                receipt.append(new String(readBytes, 0, msgLen, "UTF-8"));
            }*/

            System.out.println("get receipt: " + receipt.toString());
        }
        catch (IOException e)
        {
            System.out.println(e.getMessage());
        }
        this.br.close();
        this.bw.close();
        /*this.inputStream.close();
        this.outputStream.close();*/
        this.socket.close();
    }

    public static void main(String[] args)
    {
        SocketClient sc = new SocketClient("localhost", 9799);
        try
        {
            sc.connetServer();
            sc.sendMessage("Hi from Socket");
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }
}
