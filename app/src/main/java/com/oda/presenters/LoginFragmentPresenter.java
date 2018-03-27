package com.oda.presenters;

import com.google.firebase.auth.AuthResult;

import durdinapps.rxfirebase2.RxFirebaseAuth;
import io.reactivex.Maybe;
import io.reactivex.MaybeObserver;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class LoginFragmentPresenter extends BasePresenter {

    public interface View extends BaseView {

        void goToHomeScreen();

    }

    private View view;

    public LoginFragmentPresenter() {
    }

    public void attachView(View view){
        this.view = view;
    }

    public void dettachView(){
        this.view = null;
    }

    public void loginToAccount(String email, String password){

        Maybe<AuthResult> maybeObservable = RxFirebaseAuth.signInWithEmailAndPassword(authorizationManager.getFirebaseAuth(), email, password);

        MaybeObserver<AuthResult> maybeObserver = new MaybeObserver<AuthResult>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onSuccess(AuthResult authResult) {
                view.goToHomeScreen();
            }

            @Override
            public void onError(Throwable e) {
                view.displayMessage("Algo anda mal", e.getMessage());
            }

            @Override
            public void onComplete() {

            }
        };

        maybeObservable.observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(maybeObserver);
    }



}
