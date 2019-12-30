package com.bawei.week1.view.activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bawei.week1.R;
import com.bawei.week1.base.BaseActivity;
import com.bawei.week1.contract.IMainContract;
import com.bawei.week1.model.bean.GsonBean;
import com.bawei.week1.presenter.MainPresenter;
import com.bawei.week1.view.adapter.MyAdapter;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends BaseActivity<MainPresenter> implements IMainContract.IView {

    @BindView(R.id.rv)
    RecyclerView rv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);
    }

    @Override
    protected void initData() {
        mPresenter.onMainData();
    }

    @Override
    protected void initView() {

    }

    @Override
    protected MainPresenter preoviderPresenter() {
        return new MainPresenter();
    }

    @Override
    protected int LayoutId() {
        return R.layout.activity_main;
    }

    @Override
    public void onMainSueccess(GsonBean bean) {
        List<GsonBean.DataBean> data = bean.getData();
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 2);
        rv.setLayoutManager(gridLayoutManager);
        MyAdapter myAdapter = new MyAdapter(data);
        myAdapter.setOnItemClickListener(new MyAdapter.onItemClickListener() {
            @Override
            public void onIemClick(int position) {
                startActivity(new Intent(MainActivity.this,SecondActivity.class));
                Toast.makeText(MainActivity.this, "点击条目"+position, Toast.LENGTH_SHORT).show();
            }
        });
        rv.setAdapter(myAdapter);
    }

    @Override
    public void onMainFailure(Throwable throwable) {
        Toast.makeText(this, "请求失败", Toast.LENGTH_SHORT).show();
        Log.i("xxx",throwable.getMessage());
    }
}
