package com.bawei.week1.model;

import com.bawei.week1.contract.IMainContract;
import com.bawei.week1.model.bean.GsonBean;
import com.bawei.week1.utile.NetUtile;
import com.google.gson.Gson;

/**
 * Time:2019/12/29 0029下午 02:49201912
 * 邮箱:2094158527@qq.com
 * 作者:李庆瑶
 * 类功能:
 */
public class MainModel implements IMainContract.IModel {
    @Override
    public void onMainData(IModelCallback iModelCallback) {
        NetUtile.getInstance().getJsonGet("http://blog.zhaoliang5156.cn/api/shop/fulishe1.json", new NetUtile.MyCallback() {
            @Override
            public void onGetJson(String json) {
                GsonBean gsonBean = new Gson().fromJson(json, GsonBean.class);
                iModelCallback.onMainSueccess(gsonBean);
            }

            @Override
            public void onError(Throwable throwable) {
                iModelCallback.onMainFailure(throwable);

            }
        });
    }
}
