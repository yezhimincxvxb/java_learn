package com.yzm.io.file;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;

/**
 * 文件
 */
public class FileDemo {

    public static void main(String[] args) {
        demo01();
    }

    private static void demo01() {
        File file  = new File("C:\\b.txt");
        System.out.println(file.isFile());
        System.out.println(file.isHidden());

    }

    private static void demo01_() {
        String parentPath = System.getProperty("user.dir") + "\\io" + "\\src\\main\\resources";
        try {
            File file = new File(parentPath, "a.txt");
            FileReader fr = new FileReader(file);
            char[] c = new char[1024];
            int leg = fr.read(c);
            String s = new String(c, 0, leg);
            System.out.println(s);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
