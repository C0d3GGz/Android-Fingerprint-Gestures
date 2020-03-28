Use left and right fingerprint swipe, while preserving the system defaults (Android 10, Pixel 3):

- Swipe up closes notification bar if opened
- Swipe down opens notification bar
- Another swipe down opens the quick settings

The app does not mimic the vibration pattern and opening the quick settings will not work if the notification bar wasn't opened through swiping on the fingerprint reader.

Use the broadcast receiver to get the left and right swipes as shown in MainActivity.kt.


