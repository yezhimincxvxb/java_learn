package com.yzm.io.stream.change;

import java.io.*;
import java.net.URL;
import java.nio.charset.StandardCharsets;

/**
 * 转换流
 * InputStreamReader
 * OutputStreamWriter
 */
public class ChangeDemo {

    private static final String url = "http://www.baidu.com";
    private static final String PARENT_PATH = System.getProperty("user.dir") + "\\io" + "\\src\\main\\resources\\stream";

    public static void main(String[] args) {
        demo01();
        demo02();
        demo03();
    }

    private static void demo01() {
        try {
            long start = System.currentTimeMillis();
            InputStream in = new URL(url).openStream();
            File file = new File(PARENT_PATH, "change_1.html");
            FileOutputStream fos = new FileOutputStream(file, true);

            byte[] b = new byte[1024];
            int len;
            while ((len = in.read(b)) != -1) {
                fos.write(b, 0, len);
            }

            in.close();
            fos.close();
            long end = System.currentTimeMillis();
            System.out.println("demo01耗时 ==> " + (end - start));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void demo02() {
        try {
            long start = System.currentTimeMillis();
            InputStream in = new URL(url).openStream();
            InputStreamReader isr = new InputStreamReader(in, StandardCharsets.UTF_8);

            File file = new File(PARENT_PATH, "change_2.html");
            FileOutputStream fos = new FileOutputStream(file, true);
            OutputStreamWriter osw = new OutputStreamWriter(fos, StandardCharsets.UTF_8);

            char[] c = new char[1024];
            int len;
            while ((len = isr.read(c)) != -1) {
                osw.write(c, 0, len);
            }

            isr.close();
            osw.close();
            long end = System.currentTimeMillis();
            System.out.println("demo02耗时 ==> " + (end - start));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void demo03() {
        try {
            long start = System.currentTimeMillis();
            InputStream in = new URL(url).openStream();
            InputStreamReader isr = new InputStreamReader(in, StandardCharsets.UTF_8);
            BufferedReader bf = new BufferedReader(isr);

            File file = new File(PARENT_PATH, "change_3.html");
            FileOutputStream fos = new FileOutputStream(file, true);
            OutputStreamWriter osw = new OutputStreamWriter(fos, StandardCharsets.UTF_8);
            BufferedWriter bw = new BufferedWriter(osw);

            String line;
            while ((line = bf.readLine()) != null) {
                bw.write(line);
            }

            bf.close();
            bw.close();
            long end = System.currentTimeMillis();
            System.out.println("demo03耗时 ==> " + (end - start));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}


