package com.henan.dao;

import com.henan.entity.HnCatalogRelease;

public interface HnCatalogReleaseMapper
{
    /**
     *
     * @mbg.generated 2018-08-24
     */
    int deleteByPrimaryKey(String catalogId);
    
    /**
     *
     * @mbg.generated 2018-08-24
     */
    int insert(HnCatalogRelease record);
    
    /**
     *
     * @mbg.generated 2018-08-24
     */
    int insertSelective(HnCatalogRelease record);
    
    /**
     *
     * @mbg.generated 2018-08-24
     */
    HnCatalogRelease selectByPrimaryKey(String catalogId);
    
    /**
     *
     * @mbg.generated 2018-08-24
     */
    int updateByPrimaryKeySelective(HnCatalogRelease record);
    
    /**
     *
     * @mbg.generated 2018-08-24
     */
    int updateByPrimaryKey(HnCatalogRelease record);
    
}