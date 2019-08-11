package com.myprotobuf;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

public class ProtoServerHandler extends SimpleChannelInboundHandler<DataInfo.MyDataInfo> {
//    @Override
//    protected void channelRead0(ChannelHandlerContext ctx, DataInfo.Student msg) throws Exception {
//        System.out.println(msg);
//
//        DataInfo.Student student = DataInfo.Student.newBuilder()
//                .setAge(35).setName("wangwu").setSex("man").build();
//
//        ctx.channel().writeAndFlush(student);
//
//    }


    @Override
    protected void channelRead0(ChannelHandlerContext ctx, DataInfo.MyDataInfo msg) throws Exception {
        DataInfo.MyDataInfo.DataType dataType = msg.getDataType();

        if(dataType == DataInfo.MyDataInfo.DataType.StudentType){
            System.out.println(msg.getStudent());
        }else if (dataType == DataInfo.MyDataInfo.DataType.DogType){
            System.out.println(msg.getDog());
        }else {
            System.out.println(msg.getCat());
        }

    }
}
