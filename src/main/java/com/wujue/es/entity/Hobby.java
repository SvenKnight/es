package com.wujue.es.entity;

/**
 * @author wujue
 * @version 1.0
 * @date 2020/11/25 下午2:38
 * @since jdk11
 */
public class Hobby {

    private String id;

    private String name;

    private String proficiency;

    public Hobby() {
    }

    public Hobby(String id, String name, String proficiency) {
        this.id = id;
        this.name = name;
        this.proficiency = proficiency;
    }

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

    public String getProficiency() {
        return proficiency;
    }

    public void setProficiency(String proficiency) {
        this.proficiency = proficiency;
    }
}
