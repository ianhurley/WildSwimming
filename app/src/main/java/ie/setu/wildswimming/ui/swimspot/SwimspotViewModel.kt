package ie.setu.wildswimming.ui.swimspot

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import ie.setu.wildswimming.models.SwimspotManager
import ie.setu.wildswimming.models.SwimspotModel

class SwimspotViewModel : ViewModel() {

    private val status = MutableLiveData<Boolean>()

    val observableStatus: LiveData<Boolean>
        get() = status

    fun addSwimspot(swimspot: SwimspotModel) {
        status.value = try {
            SwimspotManager.create(swimspot)
            true
        } catch (e: IllegalArgumentException) {
            false
        }
    }
}