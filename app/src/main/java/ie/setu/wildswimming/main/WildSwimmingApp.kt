package ie.setu.wildswimming.main

import android.app.Application
import ie.setu.wildswimming.models.SwimspotMemStore
import ie.setu.wildswimming.models.SwimspotStore
import timber.log.Timber

class WildSwimmingApp: Application() {

    lateinit var swimspotsStore: SwimspotStore

    override fun onCreate() {
        super.onCreate()
        Timber.plant(Timber.DebugTree())
        swimspotsStore = SwimspotMemStore()
        Timber.i("Starting WildSwimming Application")
    }
}