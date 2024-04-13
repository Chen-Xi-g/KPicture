package com.griffin.example

import android.app.Application
import com.griffin.example.engine.GlideEngine
import com.griffin.kp.KPicture

class App : Application() {

    override fun onCreate() {
        super.onCreate()
        KPicture.apply {
            initialize(this@App)
            imageEngine = GlideEngine()
        }
    }

}