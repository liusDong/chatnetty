package com.myprotobuf;

import com.lsd.MyClientInit;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Random;
import java.util.concurrent.TimeUnit;

public class ProtoClient {
    public static void main(String[] args) {
        EventLoopGroup eventLoopGroup = new NioEventLoopGroup();

        try{
            Bootstrap bootstrap = new Bootstrap();
            bootstrap.group(eventLoopGroup).channel(NioSocketChannel.class).handler(new ProtoClientInit());
            ChannelFuture ch = bootstrap.connect("127.0.0.1", 8899).sync();

            Channel channel = ch.channel();

            while (true){
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


                channel.writeAndFlush(myDataInfo);

                TimeUnit.SECONDS.sleep(2);

            }

//            ch.channel().closeFuture().sync();

        }catch (Exception e){
            e.printStackTrace();
        }finally {
            eventLoopGroup.shutdownGracefully();
        }
    }
}
