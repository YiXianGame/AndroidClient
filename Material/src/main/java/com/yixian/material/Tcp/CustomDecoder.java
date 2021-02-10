package com.yixian.material.Tcp;

import android.util.JsonReader;
import android.util.Log;

import com.yixian.material.Exception.RPCException;
import com.yixian.material.Log.Log.Tag;
import com.yixian.material.RPC.ClientResponseModel;
import com.yixian.material.RPC.RPCType;
import com.yixian.material.RPC.ServerRequestModel;
import com.yixian.material.Utils.Utils;

import java.io.StringReader;
import java.nio.charset.StandardCharsets;
import java.util.List;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.UnpooledByteBufAllocator;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;

public class CustomDecoder extends ByteToMessageDecoder {
    int headSize = 32;//头包长度
    int bodySize = 4;//数据大小长度
    int patternSize = 1;//消息类型长度
    int futureSize = 27;//后期看情况加
    //下面这部分的byte用于接收数据
    private byte  pattern;
    private byte[] future = new byte[futureSize];
    int needRemain = 0;
    //申请了一个动态ByteBuf,因为使用动态StringBuilder预转码，会导致多字节符号会乱码.申请一次就一直用,所以直接非池化了.
    ByteBuf content = UnpooledByteBufAllocator.DEFAULT.directBuffer(1024,102400);
    @Override
    protected void decode(ChannelHandlerContext ctx, ByteBuf in, List<Object> out) throws Exception {
        //能提高一点性能是一点,减少函数调用与资源返回=-=
        int writerIndex = in.writerIndex();
        int readerIndex = 0;
        while(readerIndex < writerIndex){
            if(needRemain != 0){
                if(needRemain <= writerIndex - readerIndex){
                    //收到的数据足够成包,开始组包.
                    content.writeBytes(in.array(),readerIndex,needRemain);
                    if(pattern == 0){
                        Log.d(Tag.RemoteRepository,"[服-请求]:" + content.toString(StandardCharsets.UTF_8));
                        out.add(Utils.gson.fromJson(content.toString(StandardCharsets.UTF_8), ServerRequestModel.class));
                    }
                    else {
                        Log.d(Tag.RemoteRepository,"[客-返回]:" + content.toString(StandardCharsets.UTF_8));
                        out.add(Utils.gson.fromJson(content.toString(StandardCharsets.UTF_8), ClientResponseModel.class));
                    }   
                    content.resetWriterIndex();
                    readerIndex += needRemain;
                    needRemain = 0;
                    continue;
                }
                else {
                    //收到的数据全部接受,并继续等待
                    int remain = writerIndex - readerIndex;
                    content.writeBytes(in.array(), readerIndex,remain);
                    needRemain  -= remain;
                    break;
                }
            }
            else {
                int remain = writerIndex - readerIndex;
                if(writerIndex - readerIndex < headSize){
                    //收到的数据不足成头包
                    in.setBytes(0,in.array(), readerIndex,remain);
                    in.writerIndex(remain);
                    return;
                }
                else {
                    //收到的数据足以成头包
                    needRemain = ((in.array()[readerIndex + 3] & 0xff) << 24 )|((in.array()[readerIndex + 2] & 0xff) <<16 )|((in.array()[readerIndex + 1] & 0xff)<<8)|(in.array()[readerIndex] & 0xff);
                    pattern = in.array()[bodySize];
                    System.arraycopy(in.array(), readerIndex + bodySize + patternSize,future,0,futureSize);
                    readerIndex += headSize;
                    continue;
                }
            }
        }
        in.resetWriterIndex();
    }
}
