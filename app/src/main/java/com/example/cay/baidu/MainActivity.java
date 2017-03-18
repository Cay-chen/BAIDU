package com.example.cay.baidu;

import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Window;

import com.example.cay.baidu.adapter.MyFragmentPagerAdapter;
import com.example.cay.baidu.frament.ThreeFrament;
import com.example.cay.baidu.frament.UpDataFrament;
import com.example.cay.baidu.frament.UpFrament;
import com.example.cay.baidu.frament.UpTodayFrament;
import com.example.cay.baidu.util.MysqlUtil;

import java.util.ArrayList;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class MainActivity extends AppCompatActivity {
    private ViewPager mViewPager;
    private TabLayout mTabLayout;
    private ArrayList<String> mTitleList = new ArrayList<>(3);
    private ArrayList<Fragment> mFragments = new ArrayList<>(3);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main);
        getIp();
        mViewPager = (ViewPager) findViewById(R.id.vp_home);
        mTabLayout = (TabLayout) findViewById(R.id.tab_gank);
        initData();
        MyFragmentPagerAdapter myAdapter = new MyFragmentPagerAdapter(getSupportFragmentManager(), mFragments, mTitleList);
        mViewPager.setAdapter(myAdapter);
        mViewPager.setOffscreenPageLimit(3);
        myAdapter.notifyDataSetChanged();
        mTabLayout.setTabMode(TabLayout.MODE_FIXED);
        mTabLayout.setupWithViewPager(mViewPager);

    }
    private void initData() {
        mTitleList.add("上传");
        mTitleList.add("更新集数");
        mTitleList.add("更新链接");
        mTitleList.add("今日更新");
        mFragments.add(new UpFrament());
        mFragments.add(new UpDataFrament());
        mFragments.add(new ThreeFrament());
        mFragments.add(new UpTodayFrament());
    }

    public static void getIp() {
        Observable.create(new ObservableOnSubscribe<String>() {
            @Override
            public void subscribe(ObservableEmitter<String> e) throws Exception {
                e.onNext(MysqlUtil.getIp("SELECT * FROM ip"));
            }
        }).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<String>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(String value) {
                        MyApplin.UP_MOVIE_IP = value;
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }
}
