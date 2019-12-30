package com.bawei.week1.view.activity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;

import com.bawei.week1.R;
import com.bawei.week1.base.BaseActivity;
import com.bawei.week1.base.BasePresenter;
import com.bawei.week1.model.bean.Bean;
import com.uuzuche.lib_zxing.activity.CodeUtils;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class SecondActivity extends BaseActivity {


    @BindView(R.id.button1)
    TextView button1;
    @BindView(R.id.button2)
    Button button2;
    @BindView(R.id.img)
    ImageView img;
    @BindView(R.id.eventbus)
    Button eventbus;
    @BindView(R.id.botton3)
    Button botton3;

    @Override
    protected void onStart() {
        super.onStart();
        EventBus.getDefault().register(this);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);
        CodeUtils.init(this);
        img.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                CodeUtils.analyzeByImageView(img, new CodeUtils.AnalyzeCallback() {
                    @Override
                    public void onAnalyzeSuccess(Bitmap mBitmap, String result) {
                        Toast.makeText(SecondActivity.this, "扫描成功", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onAnalyzeFailed() {

                        Toast.makeText(SecondActivity.this, "扫描失败", Toast.LENGTH_SHORT).show();
                    }
                });
                return true;
            }
        });
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initView() {

    }

    @Override
    protected BasePresenter preoviderPresenter() {
        return null;
    }

    @Override
    protected int LayoutId() {
        return R.layout.activity_second;
    }


    @Subscribe
    public void onGetString(String string) {
        Toast.makeText(this, "请求" + string, Toast.LENGTH_SHORT).show();
    }
    @Subscribe
    public void onGetBean(Bean bean){
        Toast.makeText(this, bean.getName()+"***"+bean.getSex(), Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onStop() {
        super.onStop();
        EventBus.getDefault().unregister(this);
    }

    @OnClick({R.id.button1, R.id.button2, R.id.eventbus, R.id.botton3})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.button1:
                String s = button1.getText().toString();
                Bitmap image = CodeUtils.createImage(s, 400, 400, BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher_round));
                img.setImageBitmap(image);
                break;
            case R.id.button2:
                CodeUtils.analyzeByPhotos(this);
                break;
            case R.id.eventbus:
                EventBus.getDefault().post("成功");
                break;
            case R.id.botton3:
                EventBus.getDefault().post(new Bean("132","456"));
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        CodeUtils.onActivityResult(this, requestCode, resultCode, data, new CodeUtils.AnalyzeCallback() {
            @Override
            public void onAnalyzeSuccess(Bitmap mBitmap, String result) {
                Toast.makeText(SecondActivity.this, "扫码成功"+result, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onAnalyzeFailed() {
                Toast.makeText(SecondActivity.this, "扫码失败", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
