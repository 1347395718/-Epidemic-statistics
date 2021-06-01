package com.duing.service.imp;

import com.duing.bean.DataBean;
import com.duing.handler.DataHandler;
import com.duing.service.DataService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class DataServiceImp implements DataService {
    @Override
    public ArrayList<DataBean> list() {
        return DataHandler.urlData();
    }
}
