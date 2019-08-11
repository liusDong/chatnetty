package com.myprotobuf;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

public class ProtoServerHandler extends SimpleChannelInboundHandler<DataInfo.Student> {
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, DataInfo.Student msg) throws Exception {
        System.out.println(msg);

        DataInfo.Student student = DataInfo.Student.newBuilder()
                .setAge(35).setName("wangwu").setSex("man").build();

        ctx.channel().writeAndFlush(student);

    }



}
