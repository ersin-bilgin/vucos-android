package io.vucos.mobile.util

import android.content.Context
import android.os.Build
import android.provider.Settings

class DeviceInfoInformations(private val context: Context) {

    fun getDeviceBrand(): String = Build.BRAND

    fun getDeviceModel(): String = Build.MODEL

    fun getDeviceName(): String = Build.DEVICE

    fun getOsSystem(): String = "Android ${Build.VERSION.RELEASE}"

    fun getDeviceId(): String = Settings.Secure.getString(context.contentResolver, Settings.Secure.ANDROID_ID)

    // Tüm bilgileri bir Map olarak döndüren bir fonksiyon
    fun getAllDeviceInfo(): Map<String, String> = mapOf(
        "Brand" to getDeviceBrand(),
        "Model" to getDeviceModel(),
        "Name" to getDeviceName(),
        "OS" to getOsSystem(),
        "DeviceID" to getDeviceId()
    )
}