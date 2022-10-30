package com.test.common.framework

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import com.test.common.di.EMovieModule
import dagger.Component

@RequiresApi(Build.VERSION_CODES.S)
class NetworkMonitor : BaseComponent {

    var isConnected: Boolean = true

    override fun inject(injectContext: Context) {
        val connectivityManager =
            injectContext.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val capabilities =
            connectivityManager.getNetworkCapabilities(connectivityManager.activeNetwork)
        if (capabilities != null) {
            if (capabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR)) {
                Log.i("Internet", "NetworkCapabilities.TRANSPORT_CELLULAR")
                isConnected = true
            } else if (capabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI)) {
                Log.i("Internet", "NetworkCapabilities.TRANSPORT_WIFI")
                isConnected = true
            } else if (capabilities.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET)) {
                Log.i("Internet", "NetworkCapabilities.TRANSPORT_ETHERNET")
                isConnected = true
            }
        }
    }
}

@Component(modules = [EMovieModule::class])
interface BaseComponent {
    fun inject(injectContext: Context)
}