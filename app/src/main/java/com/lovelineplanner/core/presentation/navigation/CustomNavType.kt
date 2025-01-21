package com.lovelineplanner.core.presentation.navigation

import android.net.Uri
import android.os.Bundle
import androidx.navigation.NavType
import com.lovelineplanner.features.guests.presentation.guests_overview.components.GuestItem
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

object CustomNavType {

    val GuestItem = object : NavType<GuestItem>(
        isNullableAllowed = false
    ) {
        override fun get(bundle: Bundle, key: String): GuestItem? {
            return Json.decodeFromString(bundle.getString(key) ?: return null)
        }

        override fun parseValue(value: String): GuestItem {
            return Json.decodeFromString(Uri.decode(value))
        }

        override fun serializeAsValue(value: GuestItem): String {
            return Uri.encode(Json.encodeToString(value))
        }

        override fun put(bundle: Bundle, key: String, value: GuestItem) {
            bundle.putString(key, Json.encodeToString(value))
        }
    }
}