package com.oda.model;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.List;

import durdinapps.rxfirebase2.DataSnapshotMapper;
import durdinapps.rxfirebase2.RxFirebaseDatabase;
import io.reactivex.Maybe;

public class FirebaseDbInteracter {

    FirebaseDatabase firebaseDatabase ;
    DatabaseReference databaseReference;
    FirebaseAuth firebaseAuth;


    public FirebaseDbInteracter() {
        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = FirebaseDatabase.getInstance().getReference();
    }


    public void addComplaint(Complaint complaint) {
        firebaseAuth = FirebaseAuth.getInstance();
        databaseReference.child("user_complaints").child(firebaseAuth.getCurrentUser().getUid()).push().setValue(complaint);
    }

    public Maybe<List<Complaint>> retrieveComplaints(){
        firebaseAuth = FirebaseAuth.getInstance();
        DatabaseReference complaintsQuery = databaseReference.child("user_complaints").child(firebaseAuth.getCurrentUser().getUid());
        return RxFirebaseDatabase.observeSingleValueEvent(complaintsQuery, DataSnapshotMapper.listOf(Complaint.class));
    }



}
