package com.oda.presenters;

import android.util.Log;

import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseUser;
import com.oda.utils.DataValidationUtils;

import durdinapps.rxfirebase2.RxFirebaseAuth;
import io.reactivex.Maybe;
import io.reactivex.MaybeObserver;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

public class CreateAccountFragmentPresenter extends BasePresenter {

    public interface View extends BaseView {

        void showProgressDialog();
        void dismissProgressDialog();
        void goToHomeScreen();

    }

    private View view;

    public void attachView(View view){
        this.view = view;
    }

    public void dettachView(){
        this.view = null;
    }


    public void createAccount(String email, String password){

        if(!DataValidationUtils.isValidEmail(email)){
            view.displayMessage("Email Invalido", "Por favor ingresa una direccion de email valida");
            return;
        }
        if(!DataValidationUtils.isValidPassword(password)){
            view.displayMessage("Password Invalido", "Contraseña invalida, la contraseña debe tener al menos 8 caracteres, y 1 digito");
            return;
        }

        Maybe<AuthResult> maybeObservable = RxFirebaseAuth.createUserWithEmailAndPassword(authorizationManager.getFirebaseAuth(), email, password);

        MaybeObserver<FirebaseUser> maybeObserver = new MaybeObserver<FirebaseUser>() {
            @Override
            public void onSubscribe(Disposable d) {
                view.showProgressDialog();
            }

            @Override
            public void onSuccess(FirebaseUser firebaseUser) {
                view.dismissProgressDialog();
                view.goToHomeScreen();
            }

            @Override
            public void onError(Throwable e) {
                view.dismissProgressDialog();
                view.displayMessage("Algo anda mal",e.getMessage());
            }

            @Override
            public void onComplete() {

            }
        };

        maybeObservable.observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .map(new Function<AuthResult, FirebaseUser>() {
                    @Override
                    public FirebaseUser apply(AuthResult authResult) throws Exception {
                        return authResult.getUser();
                    }
                })
                .subscribe(maybeObserver);
    }

}
