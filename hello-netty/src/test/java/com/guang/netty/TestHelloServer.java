package com.guang.netty;

import com.guang.netty.server.HelloServer;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * Created by guang on 2019/3/30.
 */
public class TestHelloServer {
    private HelloServer server;

    @Before
    public void init() {
        server = new HelloServer(7878);
    }

    @Test
    public void run() {
        server.start();
    }

    @After
    public void cleanUp() {
        server.cleanUp();
    }

}
