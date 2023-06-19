package com.lch.test.designModel.observer;

public interface ObservableOnSubscribe<T> {
    void subscribe(Emitter<T> emitter);
}
