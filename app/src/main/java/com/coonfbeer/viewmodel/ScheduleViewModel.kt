package com.coonfbeer.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.coonfbeer.model.Conference
import com.coonfbeer.network.Callback
import com.coonfbeer.network.FirestoreServices
import java.lang.Exception

class ScheduleViewModel : ViewModel() {
    val firestoreServices = FirestoreServices()
    val listSchedule: MutableLiveData<List<Conference>> = MutableLiveData()
    val isLoading = MutableLiveData<Boolean>()

    fun refresh(){
        getScheduleFromFireBase()
    }

    private fun getScheduleFromFireBase() {
        firestoreServices.getSchedule(object : Callback<List<Conference>> {

            override fun onSuccess(result: List<Conference>?) {

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