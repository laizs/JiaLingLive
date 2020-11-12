package com.gzzsc.lai.entity;

import java.util.HashMap;
import java.util.Map;

/**
 * @ClassName CommunityMapping
 * @Deacription 小区的es映射配置
 * @Author laizs
 * @Date 2020/9/14 17:10
 **/
public class CommunityMapping {
    private boolean dynamic=false;
    private Map<String,Property> properties;

    public boolean isDynamic() {
        return dynamic;
    }

    public void setDynamic(boolean dynamic) {
        this.dynamic = dynamic;
    }

    public Map<String, Property> getProperties() {
        return properties;
    }

    public void setProperties(Map<String, Property> properties) {
        this.properties = properties;
    }

    public  static class Property{
        private String type;
        private Boolean index;
        private String analyzer;
        private Boolean fielddata;

        public Property(String type, Boolean index, String analyzer,Boolean fielddata) {
            this.type = type;
            this.index = index;
            this.analyzer = analyzer;
            this.fielddata=fielddata;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public Boolean getIndex() {
            return index;
        }

        public void setIndex(Boolean index) {
            this.index = index;
        }

        public String getAnalyzer() {
            return analyzer;
        }

        public void setAnalyzer(String analyzer) {
            this.analyzer = analyzer;
        }

        public Boolean getFielddata() {
            return fielddata;
        }

        public void setFielddata(Boolean fielddata) {
            this.fielddata = fielddata;
        }
    }
   public static  CommunityMapping build(){
       CommunityMapping communityMapping=new CommunityMapping();
       communityMapping.setDynamic(false);
       Map<String,Property> properties=new HashMap<>();
       communityMapping.setProperties(properties);
       Property id=new Property("keyword",true,null,null);
       properties.put("id",id);
       //Property name=new Property("keyword",true,"ik_smart");
       Property name=new Property("text",true,"ik_syno_smart",true);
       properties.put("name",name);
       Property nameKey=new Property("keyword",true,null,null);
       properties.put("nameKey",nameKey);
       Property nameCompletion=new Property("completion",null,"ik_syno_smart",null);
       properties.put("nameCompletion",nameCompletion);//completion类型
       Property namePinyin=new Property("completion",null,"pinyin_analyzer",null);
       properties.put("namePinyin",namePinyin);//completion类型

       Property address=new Property("text",true,"ik_syno_smart",null);
       properties.put("address",address);
       Property terminalCount=new Property("integer",true,null,null);
       properties.put("terminalCount",terminalCount);
       Property enablePromotionActive=new Property("boolean",true,null,null);
       properties.put("enablePromotionActive",enablePromotionActive);
       return communityMapping;
    }
}

