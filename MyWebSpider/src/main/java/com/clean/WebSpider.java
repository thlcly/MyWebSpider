package com.clean;

import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

/**
 * Created by tonghui on 2015/9/11.
 */
public class WebSpider {

    private static int PAGE = 1544;

    public static void main(String[] args) {


        try {
            //遍历所有的页面
            for (int i = PAGE; i > 0; i--) {
                //HttpClient 超时配置
                //RequestConfig globalConfig = RequestConfig.custom().setCookieSpec(CookieSpecs.STANDARD).setConnectionRequestTimeout(6000).setConnectTimeout(6000).build();
                //CloseableHttpClient httpClient = HttpClients.custom().setDefaultRequestConfig(globalConfig).build();
                //创建一个GET请求
                CloseableHttpClient httpClient = HttpClients.createDefault();
                HttpGet httpGet = new HttpGet("http://jandan.net/ooxx/page-" + i);
                httpGet.addHeader("User-Agent", "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/45.0.2454.85 Safari/537.36");
                httpGet.addHeader("Cookie", "2432700384=12; 2210899044=4; 2432700384=5; 2210899044=2; _gat=1; _ga=GA1.2.39277542.1436425807; Hm_lvt_fd93b7fb546adcfbcf80c4fc2b54da2c=1441977361,1441986158; Hm_lpvt_fd93b7fb546adcfbcf80c4fc2b54da2c=1441986169");

                //启动线程遍历每一个页面(一个线程一个页面)
                Utils.getThreadPool().execute(new Thread(new ParseHtml(httpClient, httpGet, i)));
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            Utils.getThreadPool().shutdown();
        }
    }

}
