package com.ning.netty.server;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandler;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.channel.ChannelOutboundHandler;

/**
 * @author ningjianjian
 * @Date 2021/5/8 下午5:22
 * @Description
 */
public class ServerHandler extends ChannelInboundHandlerAdapter {

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        System.out.println("新的连接进入，" + ctx.name());
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        System.out.println("消费端接收到消息：" + msg);
        ctx.writeAndFlush("你好，客户端，我已收到你的消息");
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        System.out.println("消费端接收出现异常：" + cause.getMessage());
        ctx.channel().close();
    }
}
