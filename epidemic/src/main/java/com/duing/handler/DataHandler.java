package com.duing.handler;



import com.duing.bean.DataBean;
import com.google.gson.Gson;

import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Map;

public class DataHandler {


//    public static void main(String[] args) {
//
//
//        DataHandler.data();
//
//    }

    public static ArrayList<DataBean> data(){

        FileReader fr = null;
        try {
            fr = new FileReader("D:\\gitProject\\-Epidemic-statistics\\epidemic\\src\\main\\resources\\tmp.txt");
            int cRead = 0 ;
            char[] cBuf = new char[1024];
            StringBuilder  builder = new StringBuilder ();
            while ((cRead  = fr.read(cBuf)) > 0){
                //将获取的字节数据转换成string
                builder.append(new String(cBuf,0,cRead));
            }
            System.out.println(builder.toString());

            // 整体是个map
            Gson gson = new Gson();
            Map map =  gson.fromJson(builder.toString(),Map.class);

            ArrayList areaList = (ArrayList) map.get("areaTree");
            Map dataChina = (Map) areaList.get(0); //找到中国
            ArrayList areas = (ArrayList) dataChina.get("children");//所有的地区


            ArrayList<DataBean> dataBeans = new ArrayList<>();
            for(int i = 0 ; i < areas.size() ; i++){

                Map singleArea = (Map) areas.get(i);
                String name = (String) singleArea.get("name");

                Map totalMap = (Map) singleArea.get("total");

                double confirm = (double) totalMap.get("confirm");
                double nowConfirm = (double)totalMap.get("nowConfirm");
                double dead = (double)totalMap.get("dead");
                boolean showRate = (boolean)totalMap.get("showRate");
                double heal = (double)totalMap.get("heal");

                dataBeans.add(new DataBean(name,(int)confirm,(int)nowConfirm,(int)dead,showRate,(int)heal));

            }
            return dataBeans;





        }catch (Exception e){
            e.printStackTrace();
        }finally {
            if(fr != null){
                try {
                    fr.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        return null;

    }

    public static ArrayList<DataBean> urlData(){


        String message="";
        try {
            URL url=new URL("https://view.inews.qq.com/g2/getOnsInfo?name=disease_h5&callback=jQuery351036712784856123926_1622512850770&_=1622512850771");//获取URL
            HttpURLConnection connection= (HttpURLConnection) url.openConnection();//URLConection的子类HttpURLConnection
            connection.setRequestMethod("GET");//设置请求方式
            connection.setConnectTimeout(5*1000);//设置连接时间
            connection.connect();//连接
            //判读 是否是200




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
        // 整体是个map
        Gson gson = new Gson();
        Map map =  gson.fromJson(message,Map.class);
        System.out.println(map);

        ArrayList areaList = (ArrayList) map.get("areaTree");
        Map dataChina = (Map) areaList.get(0); //找到中国
        ArrayList areas = (ArrayList) dataChina.get("children");//所有的地区


        ArrayList<DataBean> dataBeans = new ArrayList<>();
        for(int i = 0 ; i < areas.size() ; i++){

            Map singleArea = (Map) areas.get(i);
            String name = (String) singleArea.get("name");

            Map totalMap = (Map) singleArea.get("total");

            double confirm = (double) totalMap.get("confirm");
            double nowConfirm = (double)totalMap.get("nowConfirm");
            double dead = (double)totalMap.get("dead");
            boolean showRate = (boolean)totalMap.get("showRate");
            double heal = (double)totalMap.get("heal");

            dataBeans.add(new DataBean(name,(int)confirm,(int)nowConfirm,(int)dead,showRate,(int)heal));

        }
        return dataBeans;

    }


}
