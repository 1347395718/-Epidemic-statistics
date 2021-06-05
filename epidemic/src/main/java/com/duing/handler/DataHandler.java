package com.duing.handler;



import com.duing.bean.DataBean;
import com.duing.bean.GraphBean;
import com.duing.service.imp.DataServiceImp;
import com.duing.util.HttpURLConnectionUtil;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Component
public class DataHandler {
//    public static void main(String[] args) {
//
//
//        DataHandler.data();
//
//    }

//    public static ArrayList<DataBean> data(){
//
//        FileReader fr = null;
//        try {
//            fr = new FileReader("D:\\gitProject\\-Epidemic-statistics\\epidemic\\src\\main\\resources\\tmp.txt");
//            int cRead = 0 ;
//            char[] cBuf = new char[1024];
//            StringBuilder  builder = new StringBuilder ();
//            while ((cRead  = fr.read(cBuf)) > 0){
//                //将获取的字节数据转换成string
//                builder.append(new String(cBuf,0,cRead));
//            }
//            System.out.println(builder.toString());
//
//            // 整体是个map
//            Gson gson = new Gson();
//            Map map =  gson.fromJson(builder.toString(),Map.class);
//
//            ArrayList areaList = (ArrayList) map.get("areaTree");
//            Map dataChina = (Map) areaList.get(0); //找到中国
//            ArrayList areas = (ArrayList) dataChina.get("children");//所有的地区
//
//
//            ArrayList<DataBean> dataBeans = new ArrayList<>();
//            for(int i = 0 ; i < areas.size() ; i++){
//
//                Map singleArea = (Map) areas.get(i);
//                String name = (String) singleArea.get("name");
//
//                Map totalMap = (Map) singleArea.get("total");
//
//                double confirm = (double) totalMap.get("confirm");
//                double nowConfirm = (double)totalMap.get("nowConfirm");
//                double dead = (double)totalMap.get("dead");
////                boolean showRate = (boolean)totalMap.get("showRate");
//                double heal = (double)totalMap.get("heal");
//
//                dataBeans.add(new DataBean(0L,name,(int)confirm,(int)nowConfirm,(int)dead,(int)heal));
//
//            }
//            return dataBeans;
//
//
//
//
//
//        }catch (Exception e){
//            e.printStackTrace();
//        }finally {
//            if(fr != null){
//                try {
//                    fr.close();
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
//            }
//        }
//
//        return null;
//
//    }

    @Autowired
    private DataServiceImp serviceImp ;

    //数据初始化存入数据库中
    @PostConstruct
    public void initData(){
        serviceImp.remove(null);//先删除全部数据
        serviceImp.saveBatch(DataHandler.urlData());
    }

    //定时任务

    @Scheduled(cron = "0/20 0 0/1 * * ?")
    public void scheduled(){
        serviceImp.remove(null);//先删除全部数据
        serviceImp.saveBatch(DataHandler.urlData());
        
    }


    public static String url = "https://view.inews.qq.com/g2/getOnsInfo?name=disease_h5";

    public static List<GraphBean> urlGraphBean(){
        String str = HttpURLConnectionUtil.doGet("https://api.inews.qq.com/newsqa/v1/query/inner/publish/modules/list?modules=chinaDayList,chinaDayAddList,nowConfirmStatis,provinceCompare");
        Gson gson = new Gson();
        Map strMap = gson.fromJson(str,Map.class);

        System.out.println(strMap);

        Map dataMap = (Map) strMap.get("data");

        ArrayList dayAddList = (ArrayList) dataMap.get("chinaDayAddList");
        ArrayList<GraphBean> graphBeans = new ArrayList<>();

        for(int i = 0 ; i < dayAddList.size() ; i++){
            Map map = (Map) dayAddList.get(i);
            String dataStr = (String) map.get("date");
            double confirm = (double) map.get("confirm");
            double suspect = (double) map.get("suspect");

            graphBeans.add(new GraphBean(0l,dataStr,(int)confirm,(int)suspect));

        }

        return graphBeans;
    }

    public static ArrayList<DataBean> urlData(){


        try {

            String str = HttpURLConnectionUtil.doGet(url);



            // 整体是个map
            Gson gson = new Gson();
            Map strMap =  gson.fromJson(str,Map.class);

            System.out.println(strMap);

            String subStr = (String) strMap.get("data");
            Map map = gson.fromJson(subStr,Map.class);


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
//                boolean showRate = (boolean)totalMap.get("showRate");
                double heal = (double)totalMap.get("heal");

                dataBeans.add(new DataBean(0L,name,(int)confirm,(int)nowConfirm,(int)dead,(int)heal));

            }

            return dataBeans;


        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;

    }







}
