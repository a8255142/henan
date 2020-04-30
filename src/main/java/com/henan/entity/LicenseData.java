package com.henan.entity;

import java.io.Serializable;
import java.util.Map;

public class LicenseData implements Serializable
{
    
    /**
     * 注释内容
     */
    private static final long serialVersionUID = -5892409040214635631L;
    
    private String code;//证照编码
    
    private String licenseNo;//证照唯一编号
    
    private String status;//证照状态:新录入(0),合成(2),盖章(3),注销(4)
    
    private String inputDate;//录入时间
    
    private String holdBy;//持证人
    
    private String expiryDate;//过期时间
    
    private String deptNo;//所属部门
    
    private String licenseName;
    
    private String licenseVersion;//证照版本
    
    private String holdByNo;
    
    private Boolean canceled;//是否注销
    
    private Map<String, Object> dataMap;//照面数据项
    
    private Map<String, Object> fileMap;//照面文件项
    
    private String fileStream;//文件流正本
    
    private String copyStream;//文件流副本
    
    public String getCode()
    {
        return code;
    }
    
    public void setCode(String code)
    {
        this.code = code;
    }
    
    public String getLicenseNo()
    {
        return licenseNo;
    }
    
    public void setLicenseNo(String licenseNo)
    {
        this.licenseNo = licenseNo;
    }
    
    public String getStatus()
    {
        return status;
    }
    
    public void setStatus(String status)
    {
        this.status = status;
    }
    
    public Map<String, Object> getDataMap()
    {
        return dataMap;
    }
    
    public void setDataMap(Map<String, Object> dataMap)
    {
        this.dataMap = dataMap;
    }
    
    public String getInputDate()
    {
        return inputDate;
    }
    
    public void setInputDate(String inputDate)
    {
        this.inputDate = inputDate;
    }
    
    public String getHoldBy()
    {
        return holdBy;
    }
    
    public void setHoldBy(String holdBy)
    {
        this.holdBy = holdBy;
    }
    
    public String getExpiryDate()
    {
        return expiryDate;
    }
    
    public void setExpiryDate(String expiryDate)
    {
        this.expiryDate = expiryDate;
    }
    
    public String getDeptNo()
    {
        return deptNo;
    }
    
    public void setDeptNo(String deptNo)
    {
        this.deptNo = deptNo;
    }
    
    public String getLicenseName()
    {
        return licenseName;
    }
    
    public void setLicenseName(String licenseName)
    {
        this.licenseName = licenseName;
    }
    
    public String getLicenseVersion()
    {
        return licenseVersion;
    }
    
    public void setLicenseVersion(String licenseVersion)
    {
        this.licenseVersion = licenseVersion;
    }
    
    public String getHoldByNo()
    {
        return holdByNo;
    }
    
    public void setHoldByNo(String holdByNo)
    {
        this.holdByNo = holdByNo;
    }
    
    public Boolean getCanceled()
    {
        return canceled;
    }
    
    public void setCanceled(Boolean canceled)
    {
        this.canceled = canceled;
    }
    
    public String getFileStream()
    {
        return fileStream;
    }
    
    public void setFileStream(String fileStream)
    {
        this.fileStream = fileStream;
    }
    
    public String getCopyStream()
    {
        return copyStream;
    }
    
    public void setCopyStream(String copyStream)
    {
        this.copyStream = copyStream;
    }
    
    public Map<String, Object> getFileMap()
    {
        return fileMap;
    }
    
    public void setFileMap(Map<String, Object> fileMap)
    {
        this.fileMap = fileMap;
    }
}
