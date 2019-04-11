package de.familiep.fingerprintgestures

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent

class FingerprintBroadcastReceiver(private val callback: (gesture: Int) -> Unit) : BroadcastReceiver(){

    override fun onReceive(context: Context?, intent: Intent?) {
        intent?.extras?.getInt(FingerprintBroadcast.SWIPE_GESTURE_DATA)?.let {
            callback(it)
        }
    }
    
}