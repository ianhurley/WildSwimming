package ie.setu.wildswimming.ui.swimspotlist

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.firebase.auth.FirebaseUser
import ie.setu.wildswimming.firebase.FirebaseDBManager
//import ie.setu.wildswimming.models.SwimspotManager
import ie.setu.wildswimming.models.SwimspotModel
import timber.log.Timber

class SwimspotListViewModel : ViewModel() {

    private val swimspotsList = MutableLiveData<List<SwimspotModel>>()

    val observableSwimspotsList: LiveData<List<SwimspotModel>>
        get() = swimspotsList

    var liveFirebaseUser = MutableLiveData<FirebaseUser>()

    init {
        load()
    }

    fun load() {
        try {
            FirebaseDBManager.findAll(liveFirebaseUser.value?.uid!!,swimspotsList)
            Timber.i("Load Success : ${swimspotsList.value.toString()}")
        }
        catch (e: Exception) {
            Timber.i("Load Error : $e.message")
        }
    }

    fun delete(userid: String, id: String) {
        try {
            FirebaseDBManager.delete(userid,id)
            Timber.i("Delete Success")
        }
        catch (e: Exception) {
            Timber.i("Delete Error : $e.message")
        }
    }
}