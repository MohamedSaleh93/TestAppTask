package com.saleh.testapp

import android.app.Application
import com.parse.Parse

/**
 * Created by mohamedsaleh on 10/21/18.
 */
class MainProgram: Application() {

    override fun onCreate() {
        super.onCreate()
        Parse.initialize(Parse.Configuration.Builder(this)
                .applicationId(getString(R.string.app_id))
                .clientKey(getString(R.string.client_id))
                .server(getString(R.string.data_base_server_url))
                .build()
        )
    }
}