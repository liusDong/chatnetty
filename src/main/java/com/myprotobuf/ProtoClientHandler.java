package com.myprotobuf;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

public class ProtoClientHandler extends SimpleChannelInboundHandler<DataInfo.Student> {
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, DataInfo.Student msg) throws Exception {

        System.out.println(msg);
        DataInfo.Student student = DataInfo.Student.newBuilder()
                .setAge(15).setName("lisi").setSex("man").build();
        ctx.channel().writeAndFlush(student);

    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        DataInfo.Student student = DataInfo.Student.newBuilder()
                .setAge(15).setName("lisi").setSex("man").build();

        System.out.println(student);

        ctx.channel().writeAndFlush(student);

    }
}
