package com.example.cay.baidu.bean;

import java.util.List;

/**
 * Created by Cay on 2017/2/22.
 */

public class AllBean {
    private RatingBean rating;  //评分 JSON
    private String title;   //电影名
    private String year;    //上映时间
    private ImagesBean images; //图片JSON
    private List<String> genres;//类型
    private List<String> aka; //另名 集合
    private String summary; //简介
    private List<PersonBean> casts; //主演
    private List<PersonBean> directors; //导演
    private String subtype;//影视类型
    private List<String> countries; //制片国家/地区
    private String ratings_count;// 评分人数
    private String episodes_count; //总集数

    public RatingBean getRating() {
        return rating;
    }

    public void setRating(RatingBean rating) {
        this.rating = rating;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public ImagesBean getImages() {
        return images;
    }

    public void setImages(ImagesBean images) {
        this.images = images;
    }

    public List<String> getGenres() {
        return genres;
    }

    public void setGenres(List<String> genres) {
        this.genres = genres;
    }

    public List<String> getAka() {
        return aka;
    }

    public void setAka(List<String> aka) {
        this.aka = aka;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public List<PersonBean> getCasts() {
        return casts;
    }

    public void setCasts(List<PersonBean> casts) {
        this.casts = casts;
    }

    public List<PersonBean> getDirectors() {
        return directors;
    }

    public void setDirectors(List<PersonBean> directors) {
        this.directors = directors;
    }

    public String getSubtype() {
        return subtype;
    }

    public void setSubtype(String subtype) {
        this.subtype = subtype;
    }

    public List<String> getCountries() {
        return countries;
    }

    public void setCountries(List<String> countries) {
        this.countries = countries;
    }

    public String getRatings_count() {
        return ratings_count;
    }

    public void setRatings_count(String ratings_count) {
        this.ratings_count = ratings_count;
    }

    public String getEpisodes_count() {
        return episodes_count;
    }

    public void setEpisodes_count(String episodes_count) {
        this.episodes_count = episodes_count;
    }

    @Override
    public String toString() {
        return "AllBean{" +
                "rating=" + rating +
                ", title='" + title + '\'' +
                ", year='" + year + '\'' +
                ", images=" + images +
                ", genres=" + genres +
                ", aka=" + aka +
                ", summary='" + summary + '\'' +
                ", casts=" + casts +
                ", directors=" + directors +
                ", subtype='" + subtype + '\'' +
                ", countries=" + countries +
                ", ratings_count='" + ratings_count + '\'' +
                ", episodes_count='" + episodes_count + '\'' +
                '}';
    }
}
