package com.duing.handler;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.nio.charset.Charset;

@SpringBootTest
class EpidemicApplicationTests {

    @Test
    void contextLoads() {

        String message="";
        try {
            URL url=new URL("https://view.inews.qq.com/g2/getOnsInfo?name=disease_h5&callback=jQuery351036712784856123926_1622512850770&_=1622512850771");//获取URL
            HttpURLConnection connection= (HttpURLConnection) url.openConnection();//URLConection的子类HttpURLConnection
            connection.setRequestMethod("GET");//设置请求方式
            connection.setConnectTimeout(5*1000);//设置连接时间
            connection.connect();//连接
            InputStream inputStream=connection.getInputStream();//获取流管道
            byte[] data=new byte[1024];
            StringBuffer sb=new StringBuffer();
            int length=0;
            while ((length=inputStream.read(data))!=-1){
                String s=new String(data,0,length, Charset.forName("utf-8"));
                sb.append(s);
            }
            message=sb.toString();
            inputStream.close();
            connection.disconnect();
        } catch (Exception e) {
            e.printStackTrace();
        }
        message = message.substring(59,message.length()-3);
        message = message.replaceAll("\\\\","");

        System.out.println(message);

    }

}
