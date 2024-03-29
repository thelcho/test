package com.lch.test.designModel.observer;

public class ObservableCreate<T> extends Observable{
    ObservableOnSubscribe<T> source;

    public ObservableCreate(ObservableOnSubscribe<T> source) {
        this.source = source;
    }

    @Override
    protected void subscribeActual(Observer observer) {
        observer.onSubscribe();
        CreateEmitter createEmitter = new CreateEmitter(observer);
        source.subscribe(createEmitter);
    }

    /**
     * 发消息的一种默认实现类
     */

    static final class CreateEmitter<T> implements Emitter<T>{
        Observer<T> observer;
        public CreateEmitter(Observer<T> observer) {
            this.observer=observer;
        }

        @Override
        public void onNext(T t) {
            observer.onNext(t);
        }

        @Override
        public void onError(Throwable e) {
            observer.onError(e);
        }

        @Override
        public void onComplete() {
            observer.onComplete();
        }
    }
}
