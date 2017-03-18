package com.example.cay.baidu.frament;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

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

public class ThreeFrament extends Fragment {
    private EditText mMovie_id;
    private EditText mNowBum;
    private Button mFind;
    private Button mSubmit;
    private String id;
    private TextView mNameText;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.frament_updata, container, false);
        ((TextView) view.findViewById(R.id.tv_type_name)).setText("当前网址：");
        mNameText = (TextView)view.findViewById(R.id.tv_updata_name);
        mMovie_id = (EditText) view.findViewById(R.id.et_updata_now_num_movie_id);
        mNowBum = (EditText) view.findViewById(R.id.et_updata_now_num);
        mFind = (Button) view.findViewById(R.id.btn_updata_now_num_find);
        mSubmit = (Button) view.findViewById(R.id.btn_updata_now_num_submit);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mFind.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mNameText.setText("");
                id=null;
                mNowBum.setText("");
                id = mMovie_id.getText().toString().trim();
                if (id.isEmpty()) {
                    Toast.makeText(getContext(), "请输入ID", Toast.LENGTH_LONG).show();
                    return;
                }
                HttpUtils.getInstance().upMovieDetails().getTypeData(id, "name")
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(new Observer<UpMovieBackResult>() {
                            @Override
                            public void onSubscribe(Disposable d) {

                            }

                            @Override
                            public void onNext(UpMovieBackResult value) {
                                mNameText.setText(value.getResMsg());
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
        mSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View v) {
                if (mNowBum.getText().toString().trim().isEmpty()) {
                    Toast.makeText(getContext(), "请输入电视剧集数", Toast.LENGTH_LONG).show();
                    return;
                }
                HttpUtils.getInstance().upMovieDetails().updataBaiDuUrl(id,mNowBum.getText().toString().trim())
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(new Observer<UpMovieBackResult>() {
                            @Override
                            public void onSubscribe(Disposable d) {

                            }

                            @Override
                            public void onNext(UpMovieBackResult value) {
                                if (value.getResMsg().equals("1")) {
                                    Toast.makeText(getContext(), "更新成功", Toast.LENGTH_LONG).show();
                                    mNameText.setText("");
                                    id=null;
                                    mMovie_id.setText("");
                                    mNowBum.setText("");
                                } else {
                                    Toast.makeText(getContext(), "更新失败", Toast.LENGTH_LONG).show();
                                }
                            }

                            @Override
                            public void onError(Throwable e) {
                                Toast.makeText(getContext(), "更新失败", Toast.LENGTH_LONG).show();
                            }

                            @Override
                            public void onComplete() {

                            }
                        });
            }
        });
    }
}
