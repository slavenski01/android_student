package com.slavenski.app.androidstudent

import android.app.Application
import android.content.Context
import android.content.Intent
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import com.slavenski.app.androidstudent.service.StudentService

class MainViewModel(application: Application) : AndroidViewModel(application) {

    private companion object {
        const val TAG = "MainViewModel"
    }

    fun onStartService() {
        val context: Context = getApplication()
        context.startService(Intent(context, StudentService::class.java))
    }

    fun onStopService() {
        val context: Context = getApplication()
        context.stopService(Intent(context, StudentService::class.java))
    }

    override fun onCleared() {
        val context: Context = getApplication()
        context.stopService(Intent(context, StudentService::class.java))
        Log.e(TAG, "onCleared")
    }
}