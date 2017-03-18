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
import com.example.cay.baidu.bean.ToadyBean;
import com.example.cay.baidu.bean.UpMovieBackResult;
import com.example.cay.baidu.http.HttpUtils;

import org.w3c.dom.Text;

import java.util.Calendar;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by Cay on 2017/3/9.
 */

public class UpTodayFrament extends Fragment {
    private static final String TAG = "Cay";
    private EditText movieEditText;
    private EditText chaEditText;
    private EditText rihanEditText;
    private EditText usaEditText;
    private Button mButton;
    private TextView mLoad;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_up_today, container, false);
        movieEditText = (EditText) view.findViewById(R.id.et_movie);
        chaEditText = (EditText) view.findViewById(R.id.et_cha);
        rihanEditText = (EditText) view.findViewById(R.id.et_rihan);
        usaEditText = (EditText) view.findViewById(R.id.et_usa);
        mButton = (Button) view.findViewById(R.id.btn_aa);
        mLoad = (TextView) view.findViewById(R.id.tv_load);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        mLoad.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                HttpUtils.getInstance().upMovieDetails().getTodayUpdata().subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(new Observer<ToadyBean>() {
                            @Override
                            public void onSubscribe(Disposable d) {
                                Log.i(TAG, "onSubscribe: ");
                            }

                            @Override
                            public void onNext(ToadyBean value) {
                                movieEditText.setText(value.getMovie());
                                chaEditText.setText(value.getCha_tv());
                                rihanEditText.setText(value.getRihan_tv());
                                usaEditText.setText(value.getUsa_tv());
                                mLoad.setVisibility(View.GONE);
                            }

                            @Override
                            public void onError(Throwable e) {
                                Toast.makeText(getContext(), "出现错误", Toast.LENGTH_LONG).show();
                            }

                            @Override
                            public void onComplete() {
                                Log.i(TAG, "onComplete: ");
                            }
                        });
            }
        });
        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 取得系统日期:
                Calendar c = Calendar.getInstance();
                final int month = c.get(Calendar.MONTH) + 1;
                final int day = c.get(Calendar.DAY_OF_MONTH);
                String date = month + "月" + day + "日";
                String movie = movieEditText.getText().toString().trim();
                String chaTv = chaEditText.getText().toString().trim();
                final String rihanTv = rihanEditText.getText().toString().trim();
                String usaTv = usaEditText.getText().toString().trim();
                if (movie.equals("") && chaTv.equals("") && rihanTv.equals("") && usaTv.equals("")) {
                    Toast.makeText(getContext(), "请填写更新内容", Toast.LENGTH_LONG).show();
                } else {
                    HttpUtils.getInstance().upMovieDetails().upTodayUpdata(date, movie, chaTv, rihanTv, usaTv)
                            .subscribeOn(Schedulers.io())
                            .observeOn(AndroidSchedulers.mainThread())
                            .subscribe(new Observer<UpMovieBackResult>() {
                                @Override
                                public void onSubscribe(Disposable d) {

                                }

                                @Override
                                public void onNext(UpMovieBackResult value) {
                                    if (value.getResMsg().equals("1")) {
                                        Toast.makeText(getContext(), "上传成功", Toast.LENGTH_LONG).show();
                                        movieEditText.setText("");
                                        chaEditText.setText("");
                                        rihanEditText.setText("");
                                        usaEditText.setText("");
                                    } else {
                                        Toast.makeText(getContext(), "上传失败", Toast.LENGTH_LONG).show();
                                    }
                                }

                                @Override
                                public void onError(Throwable e) {
                                    Toast.makeText(getContext(), "上传失败,请检查网络", Toast.LENGTH_LONG).show();
                                }

                                @Override
                                public void onComplete() {

                                }
                            });
                }

            }
        });
    }

}
