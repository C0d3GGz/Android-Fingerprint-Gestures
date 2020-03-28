package de.familiep.fingerprintgestures

import android.accessibilityservice.FingerprintGestureController.*
import android.content.IntentFilter
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private val fingerprintReceiver by lazy {
        FingerprintBroadcastReceiver(::receiverAction)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    override fun onResume() {
        super.onResume()
        registerReceiver(fingerprintReceiver, IntentFilter(FingerprintBroadcast.BROADCAST_ACTION))
    }

    override fun onPause() {
        unregisterReceiver(fingerprintReceiver)
        super.onPause()
    }

    private fun receiverAction(gesture: Int) {
        textView.text = when (gesture) {
            FINGERPRINT_GESTURE_SWIPE_LEFT -> "Left swipe!"
            FINGERPRINT_GESTURE_SWIPE_RIGHT -> "Right swipe!"
            else -> {
                textView.text
            }
        }
    }
}
