package com.duing.util;

import com.duing.bean.DataBean;
import com.duing.service.imp.DataServiceImp;
import com.google.gson.Gson;

import java.io.File;
import java.io.FileInputStream;

import java.util.List;
import java.util.Map;

public class GsonTest {



    public static String gsonStr = "{\"lastUpdateTime\": \"2021-05-30 08:11:08\"}";

    public static void main(String[] args) {

        DataServiceImp dataServiceImp = new DataServiceImp();

        System.out.println(dataServiceImp.list());
//
////
//        GsonTest.dataStr();
//        System.out.println(gsonStr);
//        Gson gson = new Gson();
//
//        Map map = gson.fromJson(gsonStr, Map.class);
//        System.out.println(map.get("name"));

//        System.out.println(map.get("lastUpdateTime"));




    }

    public static void dataStr() {

        //获取到文件

        try {
            List<DataBean> list = null;
            FileInputStream is = new FileInputStream(new File("tmp.txt"));
            byte[] bytes = new byte[1024];
            int len = is.read(bytes);
//            while (len != -1){

                gsonStr = new String(bytes,"UTF-8");
//                is.read(bytes,0,len);
//                Gson gson = new Gson();
//                Type listType = new TypeToken<List<GsonBean>>(){}.getType();  //获得Json转换目标类型
//                list = gson.fromJson(json, listType);
//////            }
//            Gson gson = new Gson();
//
//            Map map = gson.fromJson(json, Map.class);
//            System.out.println(map.get("name"));
//            System.out.println(json);
//            System.out.println(gsonStr);
//            System.out.println(list);
        }catch (Exception e){
            e.printStackTrace();
        }

    }




}
