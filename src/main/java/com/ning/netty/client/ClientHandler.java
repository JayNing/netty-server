package com.ning.netty.client;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

/**
 * @author ningjianjian
 * @Date 2021/5/8 下午5:22
 * @Description
 */
public class ClientHandler extends ChannelInboundHandlerAdapter {

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        System.out.println("新的连接开启，" + ctx.name());
        ctx.writeAndFlush("你好，我是客户端，我想建立新的连接！");
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        System.out.println("客户端接收到消息：" + msg);
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        System.out.println("客户端端接收出现异常：" + cause.getMessage());
        ctx.channel().close();
    }
}
