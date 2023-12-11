package ie.setu.wildswimming.ui.swimspotlist

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import ie.setu.wildswimming.models.SwimspotManager
import ie.setu.wildswimming.models.SwimspotModel

class SwimspotListViewModel : ViewModel() {

    private val swimspotsList = MutableLiveData<List<SwimspotModel>>()

    val observableSwimspotsList: LiveData<List<SwimspotModel>>
        get() = swimspotsList

    init {
        load()
    }

    fun load() {
        swimspotsList.value = SwimspotManager.findAll()
    }
}