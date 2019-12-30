package com.bawei.week1.presenter;

import com.bawei.week1.base.BasePresenter;
import com.bawei.week1.contract.IMainContract;
import com.bawei.week1.model.MainModel;
import com.bawei.week1.model.bean.GsonBean;

/**
 * Time:2019/12/29 0029下午 02:49201912
 * 邮箱:2094158527@qq.com
 * 作者:李庆瑶
 * 类功能:
 */
public  class MainPresenter extends BasePresenter<IMainContract.IView> implements IMainContract.IPresenter {

    private MainModel mainModel;

    @Override
    protected void initModel() {
        mainModel = new MainModel();
    }

    @Override
    public void onMainData() {

        mainModel.onMainData(new IMainContract.IModel.IModelCallback() {
            @Override
            public void onMainSueccess(GsonBean bean) {
                view.onMainSueccess(bean);
            }

            @Override
            public void onMainFailure(Throwable throwable) {

                view.onMainFailure(throwable);
            }
        });
    }
}
