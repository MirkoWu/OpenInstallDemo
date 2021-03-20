package com.fm.openinstalldemo;

import com.fm.openinstalldemo.signblock.read.PayloadReader;

import java.io.File;
import java.nio.Buffer;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.charset.Charset;
import java.nio.charset.CharsetDecoder;
import java.util.Arrays;
import java.util.Map;

/**
 * 美团多渠道打包技术
 * https://tech.meituan.com/2017/01/13/android-apk-v2-signature-scheme.html
 *
 */
public class SignBlockTest {

    public static void main(String[] args) {

        String filePath = "D:\\项目文档\\testapk\\load5.apk";//df056f82af02657f0547b57fb1e370e7
//        String filePath = "D:\\项目文档\\testapk\\app-release.apk";//df056f82af02657f0547b57fb1e370e7
//        String filePath = "D:\\项目文档\\testapk\\lqsw.apk";//df056f82af02657f0547b57fb1e370e7

        //key = 987894612 3ae21354 value = java.nio.HeapByteBuffer[pos=0 lim=306 cap=306]{"d":{"testKey":"testValue"},"m":"-QZAYff0HbcAAAF4SJMbcnM7-63h_S3UQpRraWZu79YeoR6Tn5R1F5TE-Wjppow7Y7357_ZWFCxkCaPBqWmLwR8rglVI28HRgEtHnVQJhSIIrxvHLN35L6UWEUKg6e99cHI8otkjXoVtQwFw7aJFLSDeIVpTKNpVrr5ikZKVpJfRdxmjp-SItY22cgIpReqDNXhb-BAFvQ5BO26hAKawlE6MXs2X7p3WZb2dO3aFeNyEJSwxrrRN1QOvHuNVvyc","r":"rdrtHZt8"}

        Map<Integer, ByteBuffer> map = PayloadReader.getAll(new File(filePath));

        if (map != null) {

            for (Map.Entry<Integer, ByteBuffer> e : map.entrySet()) {
                Integer key = e.getKey();
                ByteBuffer value = e.getValue();

                System.out.println("key = " + key + " " + Integer.toHexString(key) + " value = " + e.getValue() + "" + byteBufferToString(value));
            }

        } else {
            System.out.println("空---------");

        }
    }

    public static String byteBufferToString(ByteBuffer buffer) {
        CharBuffer charBuffer = null;
        try {
            Charset charset = Charset.forName("UTF-8");
            CharsetDecoder decoder = charset.newDecoder();
//            charBuffer = charset.decode(buffer);//会乱码
            charBuffer = decoder.decode(buffer);
            buffer.flip();
            return charBuffer.toString();
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }
}
