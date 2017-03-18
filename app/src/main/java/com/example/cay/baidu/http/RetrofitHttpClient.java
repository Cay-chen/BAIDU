package com.example.cay.baidu.http;


import com.example.cay.baidu.bean.AllBean;
import com.example.cay.baidu.bean.ToadyBean;
import com.example.cay.baidu.bean.UpMovieBackResult;

import io.reactivex.Observable;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by Cay on 2017/3/9.
 */

public interface RetrofitHttpClient {
    /**
     * 豆瓣获取电影信息
     * @param id dd
     * @return dd
     */
    @GET("/v2/movie/subject/{id}")
    Observable<AllBean> getMovieDetail(@Path("id") String id);
    /**
     * 上传问题
     * @param name  问题
     * @return dd
     */
    @POST("/BaiDuDataUp/UpData")
    Observable<UpMovieBackResult> upMovieData(
            @Query("id")String id,
            @Query("name")String name,
            @Query("now_num")String now_num,
            @Query("total_num")String total_num,
            @Query("subtype")String subtype,
            @Query("ratings_count")String ratings_count,
            @Query("genres")String genres,
            @Query("countries")String countries,
            @Query("year")String year,
            @Query("director")String director,
            @Query("act")String act,
            @Query("code")String code,
            @Query("other_name")String other_name,
            @Query("baidu_url")String baidu_url,
            @Query("img_url")String img_url,
            @Query("log")String log);

    /**
     * 更新电视剧集数
     * @param id dd
     * @param num dd
     * @return dd
     */
    @GET("/BaiDuDataUp/UpdataData")
    Observable<UpMovieBackResult> updataNowNum(@Query("id") String id, @Query("num") String num);
    /**
     * 更新百度URL地址
     * @param id dd
     * @return dd
     */
    @GET("/BaiDuDataUp/UpdataBaiduUrl")
    Observable<UpMovieBackResult> updataBaiDuUrl(@Query("id") String id, @Query("baidu_url") String baidu_url);
    /**
     *
     * @param id dd
     * @return dd
     */
    @GET("/BaiDuDataUp/FindBauduUrl")
    Observable<UpMovieBackResult> getTypeData(@Query("id") String id, @Query("type") String type);

    @POST("/BaiDuDataUp/UpTodayData")
    Observable<UpMovieBackResult> upTodayUpdata(@Query("date") String date,@Query("movie") String movie,@Query("cha_tv") String cha_tv,@Query("rihan_tv") String rihan_tv,@Query("usa_tv") String usa_tv);


    @POST("/BaiDuDataUp/GetTodayDate")
    Observable<ToadyBean> getTodayUpdata();

}
