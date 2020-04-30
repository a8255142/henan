package com.henan.dao;

import java.util.Map;

import com.henan.entity.LicenseDetail;

/**
 * Created by zky on 2017/7/11.
 * 证照基本信息dao
 */
public interface LicenseDataDao
{
    public LicenseDetail selectByParams(Map<String, String> params);
}
