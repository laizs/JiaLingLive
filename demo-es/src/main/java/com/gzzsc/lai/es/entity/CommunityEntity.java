package com.gzzsc.lai.es.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

/**
 * @ClassName CommunityEntity
 * @Deacription TODO
 * @Author laizs
 * @Date 2020/9/7 22:32
 **/
@Document(indexName =Constants.COMMUNITY_INDEX,type = Constants.COMMUNITY_INDEX_TYPE,shards = 1,replicas = 0)
public class CommunityEntity {
    @Id
    private String id;
    @Field(type = FieldType.Text,analyzer = "ik_max_word")
    private String name;
    @Field(type =FieldType.Keyword )
    private String namekeyword;
    @Field(type = FieldType.Keyword)
    private String organizationSeq;
    @Field(type = FieldType.Text,analyzer = "ik_max_word")
    private String address;
    @Field(type = FieldType.Integer)
    private Integer terminalCount;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNamekeyword() {
        return namekeyword;
    }

    public void setNamekeyword(String namekeyword) {
        this.namekeyword = namekeyword;
    }

    public String getOrganizationSeq() {
        return organizationSeq;
    }

    public void setOrganizationSeq(String organizationSeq) {
        this.organizationSeq = organizationSeq;
    }

    public String getAddress() {
        return address;
    }

    @Override
    public String toString() {
        return "CommunityEntity{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", organizationSeq='" + organizationSeq + '\'' +
                ", address='" + address + '\'' +
                ", terminalCount=" + terminalCount +
                '}';
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Integer getTerminalCount() {
        return terminalCount;
    }

    public void setTerminalCount(Integer terminalCount) {
        this.terminalCount = terminalCount;
    }

}
