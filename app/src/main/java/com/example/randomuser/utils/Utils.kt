package com.example.randomuser.utils

import android.content.Context
import android.content.Intent
import android.net.Uri
import androidx.core.content.ContextCompat

class Utils {
    companion object {
        fun sendIntent(action: String, uri: Uri, context: Context) {
            val intent = Intent(action, uri)
            ContextCompat.startActivity(context, intent, null)
        }

        fun sendPhoneCallIntent(context: Context, phone: String) {
            sendIntent(Intent.ACTION_DIAL, Uri.parse("tel: $phone"), context)
        }

        fun sendEmailIntent(context: Context, email: String) {
            sendIntent(Intent.ACTION_SENDTO, Uri.parse("mailto: $email"), context)
        }

        fun sendLocationIntent(context: Context, latitude: String, longitude: String) {
            sendIntent(
                Intent.ACTION_VIEW,
                Uri.parse("https://maps.google.com/maps/search/$latitude, $longitude"),
                context
            )
        }
    }
}