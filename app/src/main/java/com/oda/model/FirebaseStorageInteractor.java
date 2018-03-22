package com.oda.model;


import android.net.Uri;
import android.widget.Toast;

import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.util.UUID;

import durdinapps.rxfirebase2.RxFirebaseStorage;
import io.reactivex.Single;
import io.reactivex.SingleObserver;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class FirebaseStorageInteractor {
    FirebaseStorage storage;
    StorageReference storageReference;

    public FirebaseStorageInteractor() {
        this.storage = FirebaseStorage.getInstance();
        this.storageReference = storage.getReference();
    }

    public Single<UploadTask.TaskSnapshot> uploadComplaintImage(Uri uri){
        storageReference = storageReference.child("images/complaints"+ UUID.randomUUID().toString());
        return RxFirebaseStorage.putFile(storageReference, uri);
    }

    public String uploadComplaintImage(Uri uri, boolean flag){
        storageReference = storageReference.child("images/complaints"+ UUID.randomUUID().toString());
        Single<UploadTask.TaskSnapshot> putFileSingle = RxFirebaseStorage.putFile(storageReference, uri);

        SingleObserver<UploadTask.TaskSnapshot> singleObserver = new SingleObserver<UploadTask.TaskSnapshot>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {

            }

            @Override
            public void onError(Throwable e) {

            }
        };


        putFileSingle.observeOn(Schedulers.io())
                .subscribeOn(AndroidSchedulers.mainThread())
                .subscribe(singleObserver);
        return storageReference.toString();
    }



}
