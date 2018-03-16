package com.zero.base;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;

/**
 * Created by zk
 */

public abstract class BasePresenter {

    protected CompositeDisposable mCompositeDisposable = new CompositeDisposable();

    public void unSubscribe() {
        mCompositeDisposable.clear();
    }


    public void register(Disposable disposable) {
        mCompositeDisposable.add(disposable);
    }
}
