package ie.setu.wildswimming.ui.swimspot

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.firebase.auth.FirebaseUser
import ie.setu.wildswimming.firebase.FirebaseDBManager
//import ie.setu.wildswimming.models.SwimspotManager
import ie.setu.wildswimming.models.SwimspotModel

class SwimspotViewModel : ViewModel() {

    private val status = MutableLiveData<Boolean>()

    val observableStatus: LiveData<Boolean>
        get() = status

    fun addSwimspot(firebaseUser: MutableLiveData<FirebaseUser>,
                    swimspot: SwimspotModel) {
        status.value = try {
            FirebaseDBManager.create(firebaseUser,swimspot)
            true
        } catch (e: IllegalArgumentException) {
            false
        }
    }
}