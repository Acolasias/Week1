package com.bawei.week1.contract;

import com.bawei.week1.model.bean.GsonBean;

/**
 * Time:2019/12/29 0029下午 02:47201912
 * 邮箱:2094158527@qq.com
 * 作者:李庆瑶
 * 类功能:
 */
public interface IMainContract {

    interface IView{
        void onMainSueccess(GsonBean bean);
        void onMainFailure(Throwable throwable);
    }

    interface IPresenter{
        void onMainData();
    }

    interface IModel{
        void onMainData(IModelCallback iModelCallback);
        interface IModelCallback{
            void onMainSueccess(GsonBean bean);
            void onMainFailure(Throwable throwable);
        }
    }
}
