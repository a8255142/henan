package com.henan.dao;

import com.henan.entity.HnCatalogSync;

public interface HnCatalogSyncMapper
{
    /**
     *
     * @mbg.generated 2018-08-24
     */
    int deleteByPrimaryKey(String catalogNo);
    
    /**
     *
     * @mbg.generated 2018-08-24
     */
    int insert(HnCatalogSync record);
    
    /**
     *
     * @mbg.generated 2018-08-24
     */
    int insertSelective(HnCatalogSync record);
    
    /**
     *
     * @mbg.generated 2018-08-24
     */
    HnCatalogSync selectByPrimaryKey(String catalogNo);
    
    /**
     *
     * @mbg.generated 2018-08-24
     */
    int updateByPrimaryKeySelective(HnCatalogSync record);
    
    /**
     *
     * @mbg.generated 2018-08-24
     */
    int updateByPrimaryKeyWithBLOBs(HnCatalogSync record);
    
    /**
     *
     * @mbg.generated 2018-08-24
     */
    int updateByPrimaryKey(HnCatalogSync record);
}