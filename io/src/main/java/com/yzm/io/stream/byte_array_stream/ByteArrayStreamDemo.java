package com.yzm.io.stream.byte_array_stream;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * 数组流
 * ByteArrayOutputStream
 * ByteArrayInputStream
 */
public class ByteArrayStreamDemo {

    private static final String PARENT_PATH = System.getProperty("user.dir") + "\\io" + "\\src\\main\\resources\\stream";

    public static void main(String[] args) {
//        demo01();
//        demo01_1();
        demo02();
    }

    private static void demo01() {
        FileInputStream fis;
        try {
            File file = new File(PARENT_PATH, "1.txt");
            fis = new FileInputStream(file);

            byte[] b = new byte[(int) file.length()];
            while (fis.read(b) != -1) {
                String s = new String(b);
                System.out.println("读取内容 ==> \r\n" + s);
            }

            fis.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void demo01_1() {
        FileInputStream fis;
        ByteArrayOutputStream bao;
        try {
            fis = new FileInputStream(new File(PARENT_PATH, "1.txt"));
            bao = new ByteArrayOutputStream();

            int i;
            while ((i = fis.read()) != -1) {
                bao.write(i);
            }

            System.out.println("读取内容 ==> \r\n" + bao.toString());
            fis.close();
            bao.close();
            //bao关闭流后，依旧可以调用方法
            System.out.println("读取内容 ==> \r\n" + bao.toString());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void demo02() {
        FileOutputStream fos;
        ByteArrayInputStream bai;
        try {
            fos = new FileOutputStream(new File(PARENT_PATH, "array_1.txt"));
            bai = new ByteArrayInputStream("北京欢迎你".getBytes());

            int i;
            while ((i = bai.read()) != -1) {
                fos.write(i);
            }

            bai.close();
            fos.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    /**
     * 读取网络图片
     */
    private static void demo03() {
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
            os = new FileOutputStream(imageFile);
            os.write(imageData);

            is.close();
            bos.close();
            os.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
