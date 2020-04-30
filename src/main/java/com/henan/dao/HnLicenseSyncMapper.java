package com.henan.dao;

import java.util.List;
import java.util.Map;

import com.henan.entity.HnLicenseSync;
import com.henan.entity.HnLicenseSyncWithBLOBs;

public interface HnLicenseSyncMapper
{
    /**
     *
     * @mbg.generated 2018-08-24
     */
    int deleteByPrimaryKey(String licenseFileNumber);
    
    /**
     *
     * @mbg.generated 2018-08-24
     */
    int insert(HnLicenseSyncWithBLOBs record);
    
    /**
     *
     * @mbg.generated 2018-08-24
     */
    int insertSelective(HnLicenseSyncWithBLOBs record);
    
    /**
     *
     * @mbg.generated 2018-08-24
     */
    HnLicenseSyncWithBLOBs selectByPrimaryKey(String licenseFileNumber);
    
    /**
     *
     * @mbg.generated 2018-08-24
     */
    int updateByPrimaryKeySelective(HnLicenseSyncWithBLOBs record);
    
    /**
     *
     * @mbg.generated 2018-08-24
     */
    int updateByPrimaryKeyWithBLOBs(HnLicenseSyncWithBLOBs record);
    
    /**
     *
     * @mbg.generated 2018-08-24
     */
    int updateByPrimaryKey(HnLicenseSync record);
    
    List<HnLicenseSyncWithBLOBs> findByLimit(Map<String, String> param);
    
    /**
    *
    * @mbg.generated 2018-08-24
    */
    int saveLicense(Map<String, Object> param);
}