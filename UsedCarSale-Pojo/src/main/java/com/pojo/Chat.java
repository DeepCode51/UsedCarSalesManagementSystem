package com.pojo;

/**
 * @author bahjan123
 * @date 2021/5/8 15:58
 */
public class Chat {


    private  Integer id;

    private String content;

    private String time;
    //被发送人
    private Integer epmId;
    //发送人

    public Integer getEpmId1() {
        return epmId1;
    }

    public void setEpmId1(Integer epmId1) {
        this.epmId1 = epmId1;
    }

    private Integer epmId1;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public Integer getEpmId() {
        return epmId;
    }

    public void setEpmId(Integer epmId) {
        this.epmId = epmId;
    }
}
