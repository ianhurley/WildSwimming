package ie.setu.wildswimming.ui.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import ie.setu.wildswimming.firebase.FirebaseDBManager
import ie.setu.wildswimming.models.SwimspotModel
import timber.log.Timber

class SwimspotDetailViewModel : ViewModel() {
    private val swimspot = MutableLiveData<SwimspotModel>()

    val observableSwimspot: LiveData<SwimspotModel>
        get() = swimspot
        //set(value) {swimspot.value = value.value}

    fun getSwimspot(userid: String, id: String) {
        try {
            FirebaseDBManager.findById(userid, id, swimspot)
            Timber.i(
                "Detail getSwimspot() Success : ${
                    swimspot.value.toString()
                }"
            )
        } catch (e: Exception) {
            Timber.i("Detail getSwimspot() Error : $e.message")
        }
    }

    fun updateSwimspot(userid:String, id: String,swimspot: SwimspotModel) {
        try {
            FirebaseDBManager.update(userid, id, swimspot)
            Timber.i("Detail update() Success : $swimspot")
        }
        catch (e: Exception) {
            Timber.i("Detail update() Error : $e.message")
        }
    }
}