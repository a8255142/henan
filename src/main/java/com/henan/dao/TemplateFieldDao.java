package com.henan.dao;

import java.util.List;
import java.util.Map;

import com.henan.entity.TemplateField;

public interface TemplateFieldDao
{
    
    /**
     * 根据证照id查询模板上所有字段项
     *
     * @param id
     * @return
     */
    List<TemplateField> selectByParams(Map<String, String> params);
}
