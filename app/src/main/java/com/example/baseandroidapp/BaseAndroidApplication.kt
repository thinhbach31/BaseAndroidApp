package com.example.baseandroidapp

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

/**
 * Application entry point. The [HiltAndroidApp] annotation triggers code
 * generation for the Dagger/Hilt dependency-injection graph.
 */
@HiltAndroidApp
class BaseAndroidApplication : Application()
