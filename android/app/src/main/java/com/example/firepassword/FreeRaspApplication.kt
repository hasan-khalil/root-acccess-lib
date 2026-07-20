package com.example.firepassword

import android.app.Activity
import android.app.Application
import android.os.Bundle
import android.util.Log
import com.aheaditec.talsec_security.security.api.ScreenProtector
import com.aheaditec.talsec_security.security.api.SuspiciousAppInfo
import com.aheaditec.talsec_security.security.api.Talsec
import com.aheaditec.talsec_security.security.api.TalsecConfig
import com.aheaditec.talsec_security.security.api.TalsecMode
import com.aheaditec.talsec_security.security.api.ThreatListener

class FreeRaspApplication : Application() {
    override fun onCreate() {
        super.onCreate()

        val config = TalsecConfig.Builder(
            EXPECTED_PACKAGE_NAME,
            EXPECTED_SIGNING_CERTIFICATE_HASH_BASE64
        )
            .watcherMail(WATCHER_MAIL)
            .supportedAlternativeStores(SUPPORTED_ALTERNATIVE_STORES)
            .prod(IS_PROD)
            .killOnBypass(KILL_ON_BYPASS)
            .build()

        config.killOnBypass
        config.supportedAlternativeStores!!.forEach { tt->
            Log.d("FreeRaspApplication","${tt.toString()}")
        }


        registerActivityLifecycleCallbacks(object : ActivityLifecycleCallbacks {
            override fun onActivityCreated(activity: Activity, bundle: Bundle?) {
                Talsec.blockScreenCapture(activity, false)
            }

            override fun onActivityStarted(activity: Activity) {}


            override fun onActivityStopped(activity: Activity) {}

            override fun onActivitySaveInstanceState(activity: Activity, bundle: Bundle) {}

            override fun onActivityDestroyed(activity: Activity) {}

            override fun onActivityPaused(p0: Activity) {
                ScreenProtector.INSTANCE.unregisterScreenCallbacks(p0)
            }

            override fun onActivityResumed(p0: Activity) {
                ScreenProtector.INSTANCE.registerScreenCallbacks(p0)
            }
        })

        Talsec.start(this, config, TalsecMode.BACKGROUND)
    }
    private companion object {
        private const val EXPECTED_PACKAGE_NAME = "com.aheaditec.talsec.demoapp" // Don't use Context.getPackageName!
        private val EXPECTED_SIGNING_CERTIFICATE_HASH_BASE64 = arrayOf(
            "mVr/qQLO8DKTwqlL+B1qigl9NoBnbiUs8b4c2Ewcz0k="
        ) // Replace with your release (!) signing certificate hashes
        private const val WATCHER_MAIL = "your_email_address@example.com"
        private val SUPPORTED_ALTERNATIVE_STORES = arrayOf(
            "com.sec.android.app.samsungapps"
            // add other stores, such as the Samsung Galaxy Store
        )
        private val IS_PROD = true
        private val KILL_ON_BYPASS = true
    }
}

