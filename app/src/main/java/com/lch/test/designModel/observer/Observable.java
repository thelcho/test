package com.lch.test.designModel.observer;

/**
 * 具体被观察者 把发送消息的事交给程序员去做
 */
public abstract class Observable implements ObserverSource{

    @Override
    public void subscribeObserver(Observer observer) {
        subscribeActual(observer);
    }

    protected abstract void subscribeActual(Observer observer);

    public static<T> Observable create(ObservableOnSubscribe<T> source){
        return new ObservableCreate<>(source);
    }
}
