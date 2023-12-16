package ie.setu.wildswimming.models

import androidx.lifecycle.MutableLiveData
import com.google.firebase.auth.FirebaseUser

interface SwimspotStore {
    fun findAll(swimspotsList:
                MutableLiveData<List<SwimspotModel>>)
    fun findAll(userid:String,
                swimspotsList:
                MutableLiveData<List<SwimspotModel>>)
    fun findById(userid:String, swimspotid: String,
                 swimspot: MutableLiveData<SwimspotModel>)
    fun create(firebaseUser: MutableLiveData<FirebaseUser>, swimspot: SwimspotModel)
    fun delete(userid:String, swimspotid: String)
    fun update(userid:String, swimspotid: String, swimspot: SwimspotModel)
}