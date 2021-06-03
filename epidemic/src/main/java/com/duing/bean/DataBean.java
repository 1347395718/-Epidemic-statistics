package com.duing.bean;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("epidemic")
public class DataBean implements Serializable {


    private static final long serialVersionUID = -2769844064744081860L;

    private long id;
    private String area;
    private int confirm;
    @TableField("newConfirm")
    private int newConfirm;
    private int dead;
    private int heal;

}
