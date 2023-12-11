package ie.setu.wildswimming.ui.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import ie.setu.wildswimming.models.SwimspotManager
import ie.setu.wildswimming.models.SwimspotModel

class SwimspotDetailViewModel : ViewModel() {
    private val swimspot = MutableLiveData<SwimspotModel>()

    val observableSwimspot: LiveData<SwimspotModel>
        get() = swimspot

    fun getSwimspot(id: Long) {
        swimspot.value = SwimspotManager.findById(id)
    }
}