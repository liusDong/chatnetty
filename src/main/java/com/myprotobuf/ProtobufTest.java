package com.myprotobuf;

import com.google.protobuf.InvalidProtocolBufferException;

public class ProtobufTest {
    public static void main(String[] args) throws InvalidProtocolBufferException {
        DataInfo.Student build = DataInfo.Student.newBuilder()
                .setAge(15).setName("zhangsan").setSex("man").build();

        byte[] studentByteArray = build.toByteArray();

        DataInfo.Student student = DataInfo.Student.parseFrom(studentByteArray);

        System.out.println(student);



    }
}
