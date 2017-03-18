package com.example.cay.baidu.frament;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.cay.baidu.MainActivity;
import com.example.cay.baidu.MyApplin;
import com.example.cay.baidu.R;
import com.example.cay.baidu.bean.AllBean;
import com.example.cay.baidu.bean.UpMovieBackResult;
import com.example.cay.baidu.http.HttpUtils;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by Cay on 2017/3/9.
 */

public class UpFrament extends Fragment {
    private EditText mId; //电影ID
    private EditText mNowNum; //当前集数
    private EditText mBaiDuUrl;//百度URL
    private TextView mName;//电影名字
    private TextView mAct;//主演
    private TextView mDct;//导演
    private TextView mCode;//评分
    private TextView mCodeNum;//评分人数
    private Button mFind;//查询按钮
    private Button mSubimt;//提交
    private ImageView mImageView;//图片
    private String id;
    private AllBean mAllBean;
    private String acts = null;
    private String drc = null;
    private String geng = null;
    private String akas = null;
    private String countries = null;
    private String subtype = "2";
    private String nowNum = "1";
    private String totalNum = "1";

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.frament_up, container, false);
        mName = (TextView) view.findViewById(R.id.tv_name);
        mId = (EditText) view.findViewById(R.id.et_movie_id);
        mNowNum = (EditText) view.findViewById(R.id.et_now_num);
        mBaiDuUrl = (EditText) view.findViewById(R.id.et_baidu_uri);
        mAct = (TextView) view.findViewById(R.id.tv_casts);
        mDct = (TextView) view.findViewById(R.id.tv_directors);
        mCode = (TextView) view.findViewById(R.id.tv_code);
        mCodeNum = (TextView) view.findViewById(R.id.tv_ratings_count);
        mFind = (Button) view.findViewById(R.id.btn_find);
        mSubimt = (Button) view.findViewById(R.id.btn_submit);
        mImageView = (ImageView) view.findViewById(R.id.iv_img);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mFind.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clearViewDataF();
                id = mId.getText().toString().trim();
                if (id.isEmpty()) {
                    Toast.makeText(getContext(), "请输入ID号", Toast.LENGTH_LONG).show();
                    return;
                }
                HttpUtils.getInstance().getDouBanMovieUtil().getMovieDetail(id)
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(new Observer<AllBean>() {
                            @Override
                            public void onSubscribe(Disposable d) {

                            }

                            @Override
                            public void onNext(AllBean bean) {
                                mAllBean = bean;
                                setView(mAllBean);

                            }

                            @Override
                            public void onError(Throwable e) {

                            }

                            @Override
                            public void onComplete() {

                            }
                        });

            }
        });
        mSubimt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View v) {
                if (MyApplin.UP_MOVIE_IP == null) {
                    MainActivity.getIp();
                    Toast.makeText(getContext(), "请稍等10秒上传", Toast.LENGTH_LONG).show();
                    return;
                }
                if (subtype.equals("1")) {
                    nowNum = mNowNum.getText().toString().trim();
                    totalNum = mAllBean.getEpisodes_count();
                    if (mNowNum.getText().toString().isEmpty()) {
                        Toast.makeText(getContext(), "请输入当前集数", Toast.LENGTH_LONG).show();
                        return;
                    }
                }
                if (mBaiDuUrl.getText().toString().isEmpty()) {
                    Toast.makeText(getContext(), "请输入百度地址", Toast.LENGTH_LONG).show();
                    return;
                }
                if (mAllBean == null) {
                    Toast.makeText(getContext(), "请重新查询一次", Toast.LENGTH_LONG).show();
                    return;
                }
                HttpUtils.getInstance().upMovieDetails().upMovieData(id, mAllBean.getTitle(), nowNum, totalNum, subtype, mAllBean.getRatings_count(), geng, countries, mAllBean.getYear(), drc, acts, mAllBean.getRating().getAverage() + "", akas, mBaiDuUrl.getText().toString().trim(), mAllBean.getImages().getLarge(), mAllBean.getSummary())
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(new Observer<UpMovieBackResult>() {
                            @Override
                            public void onSubscribe(Disposable d) {

                            }

                            @Override
                            public void onNext(UpMovieBackResult value) {
                                Log.i("Cay", "onNext: " + value);
                                if (value.getResMsg().equals("1")) {
                                    Toast.makeText(getContext(), "上传成功", Toast.LENGTH_LONG).show();
                                    clearViewData();
                                } else if (value.getResMsg().equals("2")) {
                                    Toast.makeText(getContext(), "该影片已上传，请勿重复上传", Toast.LENGTH_LONG).show();
                                } else {
                                    Toast.makeText(getContext(), "上传失败", Toast.LENGTH_LONG).show();
                                }
                            }

                            @Override
                            public void onError(Throwable e) {

                            }

                            @Override
                            public void onComplete() {

                            }
                        });


            }
        });
    }

    private void setView(AllBean bean) {
        if (bean.getSubtype().trim().equals("tv")) {
            subtype = "1";
            mNowNum.setVisibility(View.VISIBLE);
        }
        for (int i = 0; i < bean.getCasts().size(); i++) {
            if (acts == null) {
                acts = bean.getCasts().get(i).getName();
            } else {
                acts = (acts + "/" + bean.getCasts().get(i).getName());
            }
        }
        for (int i = 0; i < bean.getDirectors().size(); i++) {
            if (drc == null) {
                drc = bean.getDirectors().get(i).getName();
            } else {
                drc = (drc + "/" + bean.getDirectors().get(i).getName());
            }
        }
        for (int i = 0; i < bean.getGenres().size(); i++) {
            if (geng == null) {
                geng = bean.getGenres().get(i);
            } else {
                geng = (geng + "/" + bean.getGenres().get(i));
            }
        }
        for (int i = 0; i < bean.getAka().size(); i++) {
            if (akas == null) {
                akas = bean.getAka().get(i);
            } else {
                akas = (akas + "/" + bean.getAka().get(i));
            }
        }
        for (int i = 0; i < bean.getCountries().size(); i++) {
            if (countries == null) {
                countries = bean.getCountries().get(i);
            } else {
                countries = (countries + "/" + bean.getCountries().get(i));
            }
        }
        mName.setText(bean.getTitle());
        mAct.setText(acts);
        mDct.setText(drc);
        mCode.setText(bean.getRating().getAverage() + "");
        mCodeNum.setText(bean.getRatings_count());
        Glide.with(this).load(bean.getImages().getLarge()).into(mImageView);
    }

    private void clearViewData() {
        id = null;
        acts = null;
        drc = null;
        geng = null;
        akas = null;
        countries = null;
        subtype = "2";
        nowNum = "1";
        totalNum = "1";
        mAllBean = null;
        mName.setText("");
        mAct.setText("");
        mDct.setText("");
        mCode.setText("");
        mCodeNum.setText("");
        mId.setText("");
        mBaiDuUrl.setText("");
        mNowNum.setText("");
        Glide.with(this).load("").into(mImageView);
        mNowNum.setVisibility(View.GONE);

    }
    private void clearViewDataF() {
        id = null;
        acts = null;
        drc = null;
        geng = null;
        akas = null;
        countries = null;
        subtype = "2";
        nowNum = "1";
        totalNum = "1";
        mAllBean = null;
        mName.setText("");
        mAct.setText("");
        mDct.setText("");
        mCode.setText("");
        mCodeNum.setText("");
        mBaiDuUrl.setText("");
        mNowNum.setText("");
        Glide.with(this).load("").into(mImageView);
        mNowNum.setVisibility(View.GONE);
    }
}
