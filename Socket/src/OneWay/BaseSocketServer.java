package OneWay;

import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @File Name: OneWay
 * @Author: WQL //作者及
 * @Date: 2019/9/6 16:11//完成日期
 * @Description: // 单向通信服务端
 * @Version: v0.0.1 // 版本信息
 * @Function List: // 主要函数及其功能
 * @Others: // 其它内容的说明
 * @History: // 历史修改记录
 */
public class BaseSocketServer
{

    private ServerSocket server;
    private Socket socket;
    private int port;
    private InputStream inputStream;
    private static final int MAX_BUFFE_SIZE = 1024;

    
}
