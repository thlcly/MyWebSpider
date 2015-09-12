package com.clean;

import java.io.*;
import java.net.URL;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by tonghui on 2015/9/12.
 */
public class Utils {

    private static ExecutorService executor;

    private static String basePath = "F:\\temp";

    public static String convertHtml2String(InputStream in) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(in, "UTF-8"));
        String line;
        StringBuilder result = new StringBuilder("");
        while ((line = br.readLine()) != null) {
            result.append(line);
        }
        //System.out.println(result);
        return result.toString();
    }

    public static void downloadImage(String imageUrl) {
        File dir = new File(basePath);
        if (!dir.exists()) {
            dir.mkdirs();
            System.out.println("图片存放于" + basePath + "目录下");
        }
        String imageName = imageUrl.substring(imageUrl.lastIndexOf("/") + 1);
        try {
            File file = new File(basePath + "/" + "--" + imageName);
            OutputStream os = new FileOutputStream(file);
            //创建一个url对象
            URL url = new URL(imageUrl);
            InputStream is = url.openStream();
            byte[] buff = new byte[1024];
            while (true) {
                int readed = is.read(buff);
                if (readed == -1) {
                    break;
                }
                byte[] temp = new byte[readed];
                System.arraycopy(buff, 0, temp, 0, readed);
                //写入文件
                os.write(temp);
            }
            //System.out.println("第" + (count++) + "张妹子:" + file.getAbsolutePath());
            is.close();
            os.close();

        } catch (Exception e) {

        }
    }

    public static ExecutorService getThreadPool() {
        if (executor != null) {
            return executor;
        }

        executor = Executors.newFixedThreadPool(30);
        return executor;

    }
}
