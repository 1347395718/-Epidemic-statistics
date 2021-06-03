package com.duing.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.duing.bean.DataBean;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;


@Mapper
public interface DataMapper extends BaseMapper<DataBean>{


}
