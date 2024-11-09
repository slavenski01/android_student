package com.slavenski.app.androidstudent.service

import android.app.Service
import android.content.Intent
import android.os.IBinder
import android.util.Log

class StudentService : Service() {
    private companion object {
        const val TAG = "StudentService"
    }

    override fun onBind(p0: Intent?): IBinder? = null

    override fun onCreate() {
        Log.e(TAG, "onCreate")
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        Log.e(TAG, "onStartCommand")
        return START_NOT_STICKY
    }

    override fun onDestroy() {
        Log.e(TAG, "onDestroy")
    }
}