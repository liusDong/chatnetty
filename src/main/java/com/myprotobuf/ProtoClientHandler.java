package com.myprotobuf;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

import javax.xml.crypto.Data;
import java.util.Random;

public class ProtoClientHandler extends SimpleChannelInboundHandler<DataInfo.MyDataInfo> {
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, DataInfo.MyDataInfo msg) throws Exception {

        System.out.println(msg);
//        DataInfo.Student student = DataInfo.Student.newBuilder()
//                .setAge(15).setName("lisi").setSex("man").build();
//        ctx.channel().writeAndFlush(student);

    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {

        int randomInt = new Random().nextInt(3);
        DataInfo.MyDataInfo myDataInfo=null;
        if( 0 == randomInt){
            myDataInfo = DataInfo.MyDataInfo.newBuilder()
                    .setDataType(DataInfo.MyDataInfo.DataType.StudentType).setStudent(DataInfo.Student.newBuilder()
                            .setAge(15).setName("lisi").setSex("man").build()).build();
        }else if(1 == randomInt){
            myDataInfo = DataInfo.MyDataInfo.newBuilder()
                    .setDataType(DataInfo.MyDataInfo.DataType.DogType).setDog(DataInfo.Dog.newBuilder()
                            .setAge(15).setName("doga").build()).build();
        }else{
            myDataInfo = DataInfo.MyDataInfo.newBuilder()
                    .setDataType(DataInfo.MyDataInfo.DataType.CatType).setCat(DataInfo.Cat.newBuilder()
                            .setCity("london").setName("cata").build()).build();
        }


        ctx.channel().writeAndFlush(myDataInfo);

    }
}
