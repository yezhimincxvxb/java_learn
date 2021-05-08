package com.yzm.io.input_stream;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;

public class InputStreamDemo {

    private static final String PARENT_PATH = System.getProperty("user.dir") + "\\io" + "\\src\\main\\resources\\stream";

    public static void main(String[] args) {
//        demo01();
//        demo01_2();
//        demo01_3();
        demo02();
//        demo02_2();
    }

    /**
     * 文件字节写入
     */
    private static void demo01() {
        OutputStream fos;
        try {
            fos = new FileOutputStream(new File(PARENT_PATH, "1.txt"));
            fos.write("姓名：张三".getBytes());
            fos.write("\r\n".getBytes());
            fos.write("年龄：18".getBytes());
            System.out.println("写入完成");
            fos.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 文件字节读取
     */
    private static void demo01_2() {
        InputStream fis;
        try {
            File file = new File(PARENT_PATH, "1.txt");
            fis = new FileInputStream(file);
            byte[] b = new byte[(int) file.length()];
            fis.read(b);
            System.out.println(new String(b));
            fis.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 从1.txt读取数据保存到1_copy.txt
     */
    private static void demo01_3() {
        InputStream fis;
        OutputStream fos;
        try {
            File fileDest = new File(PARENT_PATH, "1_copy.txt");
            if (fileDest.exists()) fileDest.createNewFile();

            File fileSrc = new File(PARENT_PATH, "1.txt");
            fis = new FileInputStream(fileSrc);
            fos = new FileOutputStream(fileDest);

            byte[] b = new byte[1024 * 4];
            while (fis.read(b) != -1) {
                fos.write(b);
            }

            fis.close();
            fos.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 读取网络图片
     */
    private static void demo02() {
        InputStream is;
        OutputStream os;
        ByteArrayOutputStream bos;
        try {
            //获取连接网络的输入流
            URL url = new URL("http://www.51gjie.com/Images/image1/lkqixikw.lqs.jpg");
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.setConnectTimeout(5 * 1000);
            is = conn.getInputStream();

            //得到图片的二进制数据，以二进制封装得到数据，具有通用性
            bos = new ByteArrayOutputStream();
            byte[] b = new byte[1024];
            int len;
            while ((len = is.read(b)) != -1) {
                bos.write(b, 0, len);
            }
            byte[] imageData = bos.toByteArray();

            //输出
            File imageFile = new File(PARENT_PATH, "io_image.jpg");
            if (imageFile.exists()) imageFile.createNewFile();
            os = new FileOutputStream(imageFile);
            os.write(imageData);

            is.close();
            bos.close();
            os.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 文件-字符-写入
     */
    private static void demo021() {
        Writer fw;
        try {
            fw = new FileWriter(new File(PARENT_PATH, "2.txt"));
            fw.write("姓名：李四");
            fw.write("\r\n");
            fw.write("年龄：28");
            System.out.println("写入完成");
            fw.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 文件-字符-读取
     */
    private static void demo021_2() {
        Reader fr;
        try {
            File file = new File(PARENT_PATH, "2.txt");
            fr = new FileReader(file);
            char[] c = new char[(int) file.length()];
            fr.read(c);
            System.out.println(new String(c));
            fr.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
