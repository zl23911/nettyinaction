package com.action;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.socket.nio.NioSocketChannel;

import java.net.InetSocketAddress;
import java.nio.charset.Charset;

/**
 * create by zl239
 */
public class ConnectExample {

    private static final Channel CHANNEL_FROM_SOMEWHERE = new NioSocketChannel();

    public static void connect(){
        final Channel channel = CHANNEL_FROM_SOMEWHERE;
        final ChannelFuture future = channel.connect(new InetSocketAddress("localhost", 1212));

        future.addListener(new ChannelFutureListener() {
            public void operationComplete(ChannelFuture channelFuture) throws Exception {
                if(channelFuture.isSuccess()) {
                    ByteBuf byteBuf = Unpooled.copiedBuffer("Hello", Charset.defaultCharset());

                    ChannelFuture cf = future.channel().writeAndFlush(byteBuf);

                } else {
                    Throwable cause = future.cause();
                    cause.printStackTrace();
                }
            }
        });
    }

    public static void main(String[] args) {
        connect();
    }

}