package com.guang.netty;

import com.guang.netty.client.HelloClient;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * Created by guang on 2019/3/30.
 */
public class TestHelloClient {
    private HelloClient client;

    @Before
    public void init() {
        client = new HelloClient("127.0.0.1", 7878);
    }

    @Test
    public void run() {
        client.start();
    }

    @After
    public void cleanUp() {
        client.cleanUp();
    }
}
