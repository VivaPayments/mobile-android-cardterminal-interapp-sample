# InterAppSample
Inter-App communication sample for Viva Wallet Card Terminal Android app.

The Viva Wallet card terminal app for Android can receive requests for initiating transactions from third-party apps installed on the same Android mobile device. Communication with the card terminal app is done by using Android intents. The client app creates an intent with parameters and starts the card terminal app’s pay activity. When the card terminal app’s pay activity finishes it returns the result to the caller app.

[See documentation](https://developer.vivawallet.com/pos-integration/)
