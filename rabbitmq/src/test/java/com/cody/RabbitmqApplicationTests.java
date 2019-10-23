package com.cody;

import com.cody.rabbitmq.Sender;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class RabbitmqApplicationTests {

    @Autowired
    private Sender sender;

    @Test
    public void hello() {
        sender.send();
    }

    @Test
    public void TestMessageAck() {
        sender.TestMessageAck();
    }

    @Test
    public void TestMessageAck1() {
        sender.TestMessageAck1();
    }

}
