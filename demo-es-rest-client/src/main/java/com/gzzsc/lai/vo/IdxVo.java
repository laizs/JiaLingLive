package com.gzzsc.lai.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Map;

/**
 * @ClassName IdxVo
 * @Deacription 创建索引模板，用于解析为json格式
 * @Author laizs
 * @Date 2020/9/10 15:12
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
public class IdxVo {
    private String idxName;
    private IdxSql idxSql;

    /**
     * 静态内部类
     */
    public static class IdxSql{
        private boolean dynamic=false;
        private Map<String, Map<String,Object>> properties;

        public boolean isDynamic() {
            return dynamic;
        }

        public void setDynamic(boolean dynamic) {
            this.dynamic = dynamic;
        }

        public Map<String, Map<String, Object>> getProperties() {
            return properties;
        }

        public void setProperties(Map<String, Map<String, Object>> properties) {
            this.properties = properties;
        }
    }
}
