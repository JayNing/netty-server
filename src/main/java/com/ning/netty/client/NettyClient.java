package com.ning.netty.client;

import com.ning.netty.server.ServerHandler;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;

/**
 * @author ningjianjian
 * @Date 2021/5/8 下午5:32
 * @Description
 */
public class NettyClient {
    public static void main(String[] args) {

        EventLoopGroup worker = new NioEventLoopGroup();

       try {
           Bootstrap bootstrap = new Bootstrap();

           bootstrap.group(worker)
                   .channel(NioSocketChannel.class)
                   .handler(new ChannelInitializer<SocketChannel>() {
                       @Override
                       protected void initChannel(SocketChannel socketChannel) throws Exception {
                           ChannelPipeline pipeline = socketChannel.pipeline();
                           pipeline.addLast(new StringDecoder());
                           pipeline.addLast(new StringEncoder());
                           pipeline.addLast(new ClientHandler());                       }
                   });

           ChannelFuture channelFuture = bootstrap.connect("127.0.0.1", 7000).sync();
           channelFuture.channel().closeFuture().sync();
       } catch (Exception e){
           e.printStackTrace();
       }finally {
           worker.shutdownGracefully();
       }

    }
}
