package com.oda.model;


import android.net.Uri;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.util.UUID;

import durdinapps.rxfirebase2.RxFirebaseStorage;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.observers.DisposableSingleObserver;
import io.reactivex.schedulers.Schedulers;

public class FirebaseStorageInteractor {
    private FirebaseStorage storage;
    private StorageReference storageReference;
    private FirebaseAuth firebaseAuth;

    private String storageReferenceString;

    public FirebaseStorageInteractor() {
        this.storage = FirebaseStorage.getInstance();
        this.storageReference = storage.getReference();
        firebaseAuth = FirebaseAuth.getInstance();
    }

    public void uploadComplaintImage(Uri uri) {
        storageReference = storageReference.child("images/complaints/"+ firebaseAuth.getCurrentUser().getUid()).child(UUID.randomUUID().toString());
        RxFirebaseStorage.putFile(storageReference, uri)
                .observeOn(Schedulers.io())
        .subscribeOn(AndroidSchedulers.mainThread())
        .subscribe(new DisposableSingleObserver<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                setStorageReferenceString(storageReferenceString);
            }

            @Override
            public void onError(Throwable e) {
                setStorageReferenceString("");
            }
        });
    }

    public String getStorageReferenceString() {
        return storageReferenceString;
    }

    private void setStorageReferenceString(String storageReferenceString) {
        this.storageReferenceString = storageReferenceString;
    }
}
