package de.familiep.fingerprintgestures

import android.accessibilityservice.AccessibilityService
import android.accessibilityservice.FingerprintGestureController
import android.util.Log
import android.view.accessibility.AccessibilityEvent

class MyAccessibilityService : AccessibilityService(){

    override fun onServiceConnected() {

        val gestureController = fingerprintGestureController
        val gestureCallback = object : FingerprintGestureController.FingerprintGestureCallback() {
            override fun onGestureDetectionAvailabilityChanged(available: Boolean) {
                Log.d("debugg", "gesture available: $available")
            }

            override fun onGestureDetected(gesture: Int) {
                Log.d("debugg", "callback onGesture: $gesture")
            }
        }
        gestureController.registerFingerprintGestureCallback(gestureCallback, null)

        super.onServiceConnected()
    }

    override fun onInterrupt() = Unit
    override fun onAccessibilityEvent(event: AccessibilityEvent?) = Unit
}