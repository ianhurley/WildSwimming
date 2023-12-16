package ie.setu.wildswimming.firebase

import androidx.lifecycle.MutableLiveData
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import ie.setu.wildswimming.models.SwimspotModel
import ie.setu.wildswimming.models.SwimspotStore
import timber.log.Timber

object FirebaseDBManager : SwimspotStore {

    var database: DatabaseReference = FirebaseDatabase.getInstance().reference

    override fun findAll(swimspotsList: MutableLiveData<List<SwimspotModel>>) {
        TODO("Not yet implemented")
    }

    override fun findAll(userid: String, swimspotsList: MutableLiveData<List<SwimspotModel>>) {
        database.child("user-swimspots").child(userid)
            .addValueEventListener(object : ValueEventListener {
                override fun onCancelled(error: DatabaseError) {
                    Timber.i("Firebase Swimspot error : ${error.message}")
                }

                override fun onDataChange(snapshot: DataSnapshot) {
                    val localList = ArrayList<SwimspotModel>()
                    val children = snapshot.children
                    children.forEach {
                        val swimspot = it.getValue(SwimspotModel::class.java)
                        localList.add(swimspot!!)
                    }
                    database.child("user-swimspots").child(userid)
                        .removeEventListener(this)

                    swimspotsList.value = localList
                }
            })
    }

    override fun findById(userid: String, swimspotid: String, swimspot: MutableLiveData<SwimspotModel>) {
        database.child("user-swimspots").child(userid)
            .child(swimspotid).get().addOnSuccessListener {
                swimspot.value = it.getValue(SwimspotModel::class.java)
                Timber.i("firebase Got value ${it.value}")
            }.addOnFailureListener{
                Timber.e("firebase Error getting data $it")
            }
    }

    override fun create(firebaseUser: MutableLiveData<FirebaseUser>, swimspot: SwimspotModel) {
        Timber.i("Firebase DB Reference : $database")

        val uid = firebaseUser.value!!.uid
        val key = database.child("swimspots").push().key
        if (key == null) {
            Timber.i("Firebase Error : Key Empty")
            return
        }
        swimspot.uid = key
        val swimspotValues = swimspot.toMap()

        val childAdd = HashMap<String, Any>()
        childAdd["/swimspots/$key"] = swimspotValues
        childAdd["/user-swimspots/$uid/$key"] = swimspotValues

        database.updateChildren(childAdd)
    }

    override fun delete(userid: String, swimspotid: String) {
        val childDelete : MutableMap<String, Any?> = HashMap()
        childDelete["/swimspots/$swimspotid"] = null
        childDelete["/user-swimspots/$userid/$swimspotid"] = null

        database.updateChildren(childDelete)
    }

    override fun update(userid: String, swimspotid: String, swimspot: SwimspotModel) {
        val swimspotValues = swimspot.toMap()

        val childUpdate : MutableMap<String, Any?> = HashMap()
        childUpdate["swimspots/$swimspotid"] = swimspotValues
        childUpdate["user-swimspots/$userid/$swimspotid"] = swimspotValues

        database.updateChildren(childUpdate)
    }
}