package com.xianyu.yixian_client.Model.Tcp;

import com.xianyu.yixian_client.Core;
import com.xianyu.yixian_client.Model.RPC.ClientResponseModel;
import com.xianyu.yixian_client.Model.RPC.ServerRequestModel;

import java.nio.charset.Charset;
import java.util.List;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;

public class CustomDecoder extends ByteToMessageDecoder {
    int headSize = 32;//头包长度
    int bodySize = 4;//数据大小长度
    int patternSize = 1;//消息类型长度
    int futureSize = 27;//后期看情况加
    //下面这部分的byte用于接收数据
    private byte[] head = new byte[headSize + 1];
    private byte[] pattern = new byte[patternSize];
    private byte[] future = new byte[futureSize];
    int needRemain = 0;
    StringBuilder sb = new StringBuilder();
    @Override
    protected void decode(ChannelHandlerContext ctx, ByteBuf in, List<Object> out) throws Exception {
        int count = in.array().length;
        int pos = 0;
        while(pos < count){
            if(needRemain != 0){
                if(needRemain <= count - pos){
                    String data = in.toString(pos,needRemain, Charset.forName("UTF-8"));
                    if(pattern[0] == 0){
                        out.add(Core.gson.fromJson(sb.toString(), ServerRequestModel.class));
                    }
                    else {
                        out.add(Core.gson.fromJson(sb.toString(), ClientResponseModel.class));
                    }
                    pos = needRemain + pos;
                    needRemain = 0;
                    continue;
                }
                else {
                    sb.append(in.toString(pos,count - pos, Charset.forName("UTF-8")));
                    needRemain  = needRemain - count + pos;
                    break;
                }
            }
            else {
                if(count - pos < headSize - head[0]){
                    System.arraycopy(in.array(),pos,head,head[0]+1,count - pos);
                    head[0] += (byte)(count - pos);
                    break;
                }
                else {
                    System.arraycopy(in.array(),pos,head,head[0]+1,headSize - head[0]);
                    pos += headSize - head[0];
                    head[0]  = 0;
                    needRemain = ((head[1] & 0xff) << 24 )|((head[2]& 0xff) <<16 )|((head[3] & 0xff)<<8)|(head[4] & 0xff);
                    pattern[0] = head[bodySize + 1];
                    System.arraycopy(head,bodySize + patternSize + 1,future,0,futureSize);
                    continue;
                }
            }
        }
    }
}
