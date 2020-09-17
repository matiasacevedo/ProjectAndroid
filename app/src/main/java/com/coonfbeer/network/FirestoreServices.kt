package com.coonfbeer.network

import com.coonfbeer.model.Conference
import com.coonfbeer.model.Speaker
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.FirebaseFirestoreSettings

const val SPEAKERS_COLLECTION_NAME = "speakers"
const val CONFERENCE_COLLECTION_NAME = "conferences"


class FirestoreServices {
    val firebaseFirestore = FirebaseFirestore.getInstance()
    val setting = FirebaseFirestoreSettings.Builder().setPersistenceEnabled(true).build()

    init {
        firebaseFirestore.firestoreSettings = setting
    }

    fun getSpeakers(callback: Callback<List<Speaker>>){
        firebaseFirestore.collection(SPEAKERS_COLLECTION_NAME)
            .orderBy("category")
            .get()
            .addOnSuccessListener { result ->
                for (doc in result) {
                    //obtengo una lista con el resultado de firestore
                    val list = result.toObjects(Speaker:: class.java) //todas las listas de las colecciones
                    callback.onSuccess(list)
                    break
                }
            }
    }

    fun getSchedule(callback: Callback<List<Conference>>){
        firebaseFirestore.collection(CONFERENCE_COLLECTION_NAME)
            .orderBy("category")
            .get()
            .addOnSuccessListener { result ->
                for (doc in result) {
                    val list = result.toObjects(Conference:: class.java)
                    callback.onSuccess(list)
                    break
                }
            }
    }
}