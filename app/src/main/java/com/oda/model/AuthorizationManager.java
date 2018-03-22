package com.oda.model;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class AuthorizationManager {


    private FirebaseAuth firebaseAuth;

    public AuthorizationManager (){
        this.firebaseAuth = FirebaseAuth.getInstance();
    }

    public FirebaseAuth getFirebaseAuth(){
        return firebaseAuth;
    }

    public FirebaseUser getFireBaseUser(){
        return firebaseAuth.getCurrentUser();
    }


}
