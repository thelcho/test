package com.lch.test.designModel.observer;

/**
 * B接口，抽象观察者
 * @param <T>
 */
public interface Observer<T> {
    void onNext(T t);
    void onSubscribe();
    void onError(Throwable e);
    void onComplete();
}
