package com.example.cay.baidu.bean;



/**
 * Created by jingbin on 2016/11/25.
 */

public class PersonBean  {
    /**
     * alt : https://movie.douban.com/celebrity/1050059/
     * avatars : {"small":"https://img3.doubanio.com/img/celebrity/small/1691.jpg","large":"https://img3.doubanio.com/img/celebrity/large/1691.jpg","medium":"https://img3.doubanio.com/img/celebrity/medium/1691.jpg"}
     * name : 范冰冰  or
     * name : 冯小刚
     * id : 1050059
     * type: 导演 或 演员
     */
    private String alt;

    // 导演或演员
    private String type;

    private ImagesBean avatars;
    private String name;
    private String id;

    public String getAlt() {
        return alt;
    }

    public void setAlt(String alt) {
        this.alt = alt;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public ImagesBean getAvatars() {
        return avatars;
    }

    public void setAvatars(ImagesBean avatars) {
        this.avatars = avatars;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "PersonBean{" +
                "alt='" + alt + '\'' +
                ", type='" + type + '\'' +
                ", avatars=" + avatars +
                ", name='" + name + '\'' +
                ", id='" + id + '\'' +
                '}';
    }
}
