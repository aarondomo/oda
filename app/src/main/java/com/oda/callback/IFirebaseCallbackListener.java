package com.oda.callback;


public interface IFirebaseCallbackListener<T> {

    void childAdded(T child);

    void childChanged(T child);

    void childRemoved(T child);

    void onFailure(Throwable throwable);
}