package de.familiep.fingerprintgestures

import android.accessibilityservice.AccessibilityService
import android.accessibilityservice.FingerprintGestureController
import android.accessibilityservice.FingerprintGestureController.*
import android.content.Intent
import android.view.accessibility.AccessibilityEvent
import de.familiep.fingerprintgestures.NotificationBarState.*

class GestureService : AccessibilityService() {

    private var notificationBarState = CLOSED

    override fun onServiceConnected() {

        val gestureController = fingerprintGestureController
        val gestureCallback = object : FingerprintGestureController.FingerprintGestureCallback() {

            override fun onGestureDetected(gesture: Int) {
                when(gesture){
                    FINGERPRINT_GESTURE_SWIPE_DOWN -> openNotificationBar()
                    FINGERPRINT_GESTURE_SWIPE_UP -> closeNotificationBar()
                    FINGERPRINT_GESTURE_SWIPE_LEFT, FINGERPRINT_GESTURE_SWIPE_RIGHT -> broadcastGesture(gesture)
                }
            }
        }

        gestureController.registerFingerprintGestureCallback(gestureCallback, null)

        super.onServiceConnected()
    }

    private fun broadcastGesture(gesture: Int) {
        Intent().also {
            it.action = FingerprintBroadcast.BROADCAST_ACTION
            it.putExtra(FingerprintBroadcast.SWIPE_GESTURE_DATA, gesture)
            sendBroadcast(it)
        }
    }

    private fun openNotificationBar(){
        performGlobalAction(GLOBAL_ACTION_NOTIFICATIONS)
        notificationBarState = NOTIFICATIONS_OPEN
    }

    private fun closeNotificationBar(){
        sendBroadcast(Intent(Intent.ACTION_CLOSE_SYSTEM_DIALOGS))
    }

    override fun onInterrupt() = Unit
    override fun onAccessibilityEvent(event: AccessibilityEvent?) = Unit
}

private enum class NotificationBarState {
    CLOSED, NOTIFICATIONS_OPEN, QUICK_SETTINGS_OPEN
}