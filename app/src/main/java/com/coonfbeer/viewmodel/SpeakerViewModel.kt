package com.coonfbeer.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.coonfbeer.model.Speaker
import com.coonfbeer.network.Callback
import com.coonfbeer.network.FirestoreServices
import java.lang.Exception

class SpeakerViewModel : ViewModel() {
    val firestoreServices = FirestoreServices()
    val listSpeaker: MutableLiveData<List<Speaker>> = MutableLiveData()
    val isLoading = MutableLiveData<Boolean>()

    fun refresh(){
        getSpeakerFromFireBase()
    }

    private fun getSpeakerFromFireBase() {
        firestoreServices.getSpeakers(object : Callback<List<Speaker>> {

            override fun onSuccess(result: List<Speaker>?) {
                TODO("Not yet implemented")
            }

            override fun onFail(exception: Exception) {
                TODO("Not yet implemented")
            }
        })
    }

    fun processFinished() {
        isLoading.value = true
    }
}