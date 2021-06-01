package com.duing.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class DataBean {



    private String area;
    private int confirm;
    private int nowConfirm;
    private int dead;
    private boolean showRate;
    private int heal;

    @Override
    public String toString() {
        return "DataBean{" +
                "area='" + area + '\'' +
                ", confirm=" + confirm +
                ", nowConfirm=" + nowConfirm +
                ", dead=" + dead +
                ", showRate=" + showRate +
                ", heal=" + heal +
                '}';
    }
}
