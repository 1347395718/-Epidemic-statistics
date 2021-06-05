package com.duing.bean;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class GraphBean {


    private long gId;
    private String date;
    private int confirm;
    private int suspect;


}
