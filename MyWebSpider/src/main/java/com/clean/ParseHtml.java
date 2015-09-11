package com.clean;


import org.apache.http.client.config.CookieSpecs;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by tonghui on 2015/9/12.
 */
public class ParseHtml implements Runnable {

    private HttpGet hg;
    private CloseableHttpClient hc;
    private int page;



    public ParseHtml(CloseableHttpClient hc,HttpGet hg,int page){
        this.hc = hc;
        this.hg = hg;
        this.page = page;
    }

    public void run() {

        try {
            CloseableHttpResponse response = hc.execute(hg);
            InputStream in = response.getEntity().getContent();
            String html = Utils.convertHtml2String(in);
            List<String> imagesLink = new ArrayList<String>();
            Document doc = Jsoup.parse(html);
            Elements lis = doc.select("li[id^=comment-]");
            //获取页面中所有的img src
            for (Element li : lis){
                Elements imgs = li.select("img");
                for (Element img : imgs){
                    imagesLink.add(img.attr("src"));
                }
            }

            //todo
            //下载所有的图片
            for (String image : imagesLink) {
                    Utils.downloadImage(image);
            }
            System.out.println(Thread.currentThread().getName()+" exit,"+"current page:"+page);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
