package com.oda.model;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.List;

import durdinapps.rxfirebase2.DataSnapshotMapper;
import durdinapps.rxfirebase2.RxFirebaseDatabase;
import io.reactivex.Maybe;

public class FirebaseDbInteracter {

    FirebaseDatabase firebaseDatabase ;
    DatabaseReference databaseReference;


    public FirebaseDbInteracter() {
        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = FirebaseDatabase.getInstance().getReference();
    }


    public void addComplaint(Complaint complaint) {
        databaseReference.child("user_complaints").push().setValue(complaint);
    }

    public Maybe<List<Complaint>> retrieveComplaints(){
        DatabaseReference complaintsQuery = databaseReference.child("user_complaints");
        return RxFirebaseDatabase.observeSingleValueEvent(complaintsQuery, DataSnapshotMapper.listOf(Complaint.class));
    }



}
