package com.oda.callback;

public interface ICallbackListener<T> {
    void onSuccess(T data);

    void onFailure(Throwable t);
}
