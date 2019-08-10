package com.lsd;

import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.group.ChannelGroup;
import io.netty.channel.group.DefaultChannelGroup;
import io.netty.util.concurrent.GlobalEventExecutor;

public class MyChannelHandler extends SimpleChannelInboundHandler<String> {

    static final ChannelGroup channelGroup = new DefaultChannelGroup(GlobalEventExecutor.INSTANCE);
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, String msg) throws Exception {
        Channel channel = ctx.channel();
        System.out.println("【client】"+channel.remoteAddress()+"say:" + msg);
        channelGroup.forEach(ch->{
            if(ch != channel){
                channelGroup.writeAndFlush("【client】"+channel.remoteAddress()+"say:" + msg+"\r\n");
            }else {
                channelGroup.writeAndFlush("yourself say:" + msg+"\r\n");
            }
        });
    }

    @Override
    public void handlerAdded(ChannelHandlerContext ctx) throws Exception {
        Channel channel = ctx.channel();

        System.out.println("【client】："+channel.remoteAddress()+" enter the chat room");
        channelGroup.writeAndFlush("【client】："+channel.remoteAddress()+" enter the chat room");

        channelGroup.add(channel);
    }

    @Override
    public void handlerRemoved(ChannelHandlerContext ctx) throws Exception {
        Channel channel = ctx.channel();
        System.out.println("【client】："+channel.remoteAddress()+" leave the chat room");
        channelGroup.writeAndFlush("【client】："+channel.remoteAddress()+" leave the chat room");

        channelGroup.add(channel);
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        Channel channel = ctx.channel();
        System.out.println("【client】："+channel.remoteAddress()+"on line");
        channelGroup.writeAndFlush("【client】："+channel.remoteAddress()+"on line");

        channelGroup.add(channel);
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        Channel channel = ctx.channel();
        System.out.println("【client】："+channel.remoteAddress()+" out line");
        channelGroup.writeAndFlush("【client】："+channel.remoteAddress()+" out line");

        channelGroup.add(channel);
    }


    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
    }
}
