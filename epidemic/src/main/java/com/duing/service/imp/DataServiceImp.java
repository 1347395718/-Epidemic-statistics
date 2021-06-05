package com.duing.service.imp;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.duing.bean.DataBean;
import com.duing.handler.DataHandler;
import com.duing.mapper.DataMapper;
import com.duing.service.DataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class DataServiceImp extends ServiceImpl<DataMapper,DataBean> implements DataService  {




    @Override
    public ArrayList<DataBean> list() {

        return DataHandler.urlData();
    }





}
