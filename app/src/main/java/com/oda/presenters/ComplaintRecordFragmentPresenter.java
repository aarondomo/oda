package com.oda.presenters;

import android.util.Log;

import com.oda.model.Complaint;


import java.util.List;

import io.reactivex.Maybe;
import io.reactivex.MaybeObserver;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class ComplaintRecordFragmentPresenter extends BasePresenter {


    public interface View extends BaseView {
        void displayComplaints(List<Complaint> complaints);
    }

    private View view;

    public void attachView(View view){
        this.view = view;
    }

    public void dettachView(){
        this.view = null;
    }

    public ComplaintRecordFragmentPresenter() {

    }


    public void retrieveComplaints(){

        MaybeObserver<List<Complaint>> observer = new MaybeObserver<List<Complaint>>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onSuccess(List<Complaint> complaints) {
                view.displayComplaints(complaints);
            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onComplete() {

            }
        };


        Maybe<List<Complaint>> listMaybe = firebaseDbInteracter.retrieveComplaints();
        listMaybe.observeOn(Schedulers.io())
                .subscribeOn(AndroidSchedulers.mainThread())
                .subscribe(observer);

    }


}
