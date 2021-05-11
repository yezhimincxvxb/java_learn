package com.yzm.io.stream.change;

import java.io.*;
import java.net.URL;

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
            InputStream is = new URL(url).openStream();

            File file2 = new File(PARENT_PATH, "a_copy.txt");
            FileOutputStream fos = new FileOutputStream(file2);

            byte[] b = new byte[1024];
            int len;
            while ((len = is.read(b)) != -1) {
                fos.write(b, 0, len);
            }

            is.close();
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
            InputStreamReader isr = new InputStreamReader(new URL(url).openStream());

            File file2 = new File(PARENT_PATH, "a_copy_2.txt");
            OutputStreamWriter osw = new OutputStreamWriter(new FileOutputStream(file2));

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
            InputStreamReader isr = new InputStreamReader(new URL(url).openStream());
            BufferedReader br = new BufferedReader(isr);

            File file2 = new File(PARENT_PATH, "a_copy_3.txt");
            OutputStreamWriter osw = new OutputStreamWriter(new FileOutputStream(file2));
            BufferedWriter bw = new BufferedWriter(osw);

            char[] c = new char[1024];
            int len;
            while ((len = br.read(c)) != -1) {
                bw.write(c, 0, len);
            }

            br.close();
            bw.close();
            long end = System.currentTimeMillis();
            System.out.println("demo03耗时 ==> " + (end - start));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}


