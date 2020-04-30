package com.henan.entity;

import java.io.Serializable;

import org.apache.commons.lang3.StringUtils;

public class TemplateField implements Serializable
{
    
    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    
    private String id;
    
    private Float fieldX;
    
    private Float fieldY;
    
    private Float width;
    
    private Float height;
    
    private String fieldName;
    
    private String itemNo;//信息项编号
    
    private String type;
    
    private String mutiText;
    
    private Integer font;
    
    private Float fontSize;
    
    private Integer licenseId;
    
    private boolean isCopy;
    
    private Float zoom;
    
    private Integer page;
    
    private boolean primaryKey;
    
    public Integer getPage()
    {
        return page;
    }
    
    public void setPage(Integer page)
    {
        this.page = page;
    }
    
    public Float getFieldX()
    {
        return fieldX;
    }
    
    public void setFieldX(Float fieldX)
    {
        this.fieldX = fieldX;
    }
    
    public Float getFieldY()
    {
        return fieldY;
    }
    
    public void setFieldY(Float fieldY)
    {
        this.fieldY = fieldY;
    }
    
    public Float getWidth()
    {
        return width;
    }
    
    public void setWidth(Float width)
    {
        this.width = width;
    }
    
    public Float getHeight()
    {
        return height;
    }
    
    public void setHeight(Float height)
    {
        this.height = height;
    }
    
    public String getFieldName()
    {
        return fieldName;
    }
    
    public void setFieldName(String fieldName)
    {
        this.fieldName = fieldName;
    }
    
    public String getMutiText()
    {
        return mutiText;
    }
    
    public void setMutiText(String mutiText)
    {
        this.mutiText = mutiText;
    }
    
    public Integer getFont()
    {
        return font;
    }
    
    public void setFont(Integer font)
    {
        this.font = font;
    }
    
    public Float getFontSize()
    {
        return fontSize;
    }
    
    public void setFontSize(Float fontSize)
    {
        this.fontSize = fontSize;
    }
    
    public Integer getLicenseId()
    {
        return licenseId;
    }
    
    public void setLicenseId(Integer licenseId)
    {
        this.licenseId = licenseId;
    }
    
    public boolean isCopy()
    {
        return isCopy;
    }
    
    public void setIsCopy(boolean isCopy)
    {
        this.isCopy = isCopy;
    }
    
    public Float getZoom()
    {
        return zoom;
    }
    
    public void setZoom(Float zoom)
    {
        this.zoom = zoom;
    }
    
    public String getItemNo()
    {
        return itemNo;
    }
    
    public void setItemNo(String itemNo)
    {
        this.itemNo = itemNo;
    }
    
    public boolean isPrimaryKey()
    {
        return primaryKey;
    }
    
    public void setPrimaryKey(boolean primaryKey)
    {
        this.primaryKey = primaryKey;
    }
    
    public String getType()
    {
        return type;
    }
    
    public void setType(String type)
    {
        this.type = type;
    }
    
    public String getId()
    {
        return id;
    }
    
    public void setId(String id)
    {
        this.id = id;
    }
    
    public static void main(String[] args)
    {
        System.out.println(StringUtils.lowerCase("ADDD"));
    }
}
