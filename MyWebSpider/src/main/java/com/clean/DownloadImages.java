package com.clean;

import org.apache.http.client.config.CookieSpecs;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by tonghui on 2015/9/12.
 */
public class DownloadImages implements Runnable {

    private List<String> images = new ArrayList<String>();

    public DownloadImages(List<String>images) {
        this.images = images;
    }


    public void run() {

        for (String image : images) {
                Utils.downloadImage(image);
        }
    }
}
