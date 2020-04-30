package com.henan.service;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.henan.dao.HnLicenseSyncMapper;
import com.henan.entity.HnLicenseSyncWithBLOBs;

@Service
public class SaveService
{
    
    @Autowired
    private HnLicenseSyncMapper hnLicenseSyncMapper;
    
    public void save(HnLicenseSyncWithBLOBs lic)
    {
        hnLicenseSyncMapper.insert(lic);
        
    }
    
    public void saveLicense(Map<String, Object> param)
    {
        hnLicenseSyncMapper.saveLicense(param);
    }
    
}
