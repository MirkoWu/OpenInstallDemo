package com.fm.openinstalldemo;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.Arrays;

public class ZipTest {
    public static void main(String[] args) {
        String filePath = "D:\\项目文档\\testapk\\load33.zip";
        String filePath2 = "D:\\项目文档\\testapk\\load4.zip";
        String str1 = readApk(filePath);
        String str2 = readApk(filePath2);

        System.out.println("str1 = " + str1);
        System.out.println("str2 = " + str2);

    }

    public static String readApk(String path) {
        byte[] bytes = null;
        try {
            File file = new File(path);
            RandomAccessFile accessFile = new RandomAccessFile(file, "r");
            long index = accessFile.length();

            // 文件最后两个字节代表了comment的长度
            bytes = new byte[2];
            index = index - bytes.length;
            accessFile.seek(index);
            accessFile.readFully(bytes);

            int contentLength = bytes2Short(bytes, 0);

            // 获取comment信息
            bytes = new byte[contentLength];
            index = index - bytes.length;
            accessFile.seek(index);
            accessFile.readFully(bytes);

            return new String(bytes, "utf-8");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    private static short bytes2Short(byte[] bytes, int offset) {
        ByteBuffer buffer = ByteBuffer.allocate(2);
        buffer.order(ByteOrder.LITTLE_ENDIAN);
        buffer.put(bytes[offset]);
        buffer.put(bytes[offset + 1]);
        return buffer.getShort(0);
    }

}
