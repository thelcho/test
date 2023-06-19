package com.lch.test.designModel.observer;

/**
 * 抽象的被观察者，A接口
 */
public interface ObserverSource<T> {
    void subscribeObserver(Observer<T> observer);
}
