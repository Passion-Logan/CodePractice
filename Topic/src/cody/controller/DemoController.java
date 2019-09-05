package cody.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @File Name: cody.controller
 * @Author: WQL //作者及
 * @Date: 2019/9/5 17:19//完成日期
 * @Description: // 描述
 * @Version: v0.0.1 // 版本信息
 * @Function List: // 主要函数及其功能
 * @Others: // 其它内容的说明
 * @History: // 历史修改记录
 */
@Controller
@RequestMapping("mvc")
public class DemoController
{

    @RequestMapping(value = "hello")
    public String hello() {
        return "hello";
    }
}
