package ie.setu.wildswimming.models

import android.net.Uri
import android.os.Parcelable
import com.google.firebase.database.Exclude
import kotlinx.parcelize.Parcelize
@Parcelize
data class SwimspotModel(
    var uid: String? = "",
    var name: String = "",
    var county: String = "",
    var categorey: String = "",
    var email: String? = "joe@bloggs.com")
    : Parcelable
{
    @Exclude
    fun toMap(): Map<String, Any?> {
        return mapOf(
            "uid" to uid,
            "name" to name,
            "county" to county,
            "categorey" to categorey,
            "email" to email
        )
    }
}

                         //var photo: Uri = Uri.EMPTY,
                         //var lat : Double = 0.0,
                         //var lng: Double = 0.0,
                         //var zoom: Float = 0f)


//@Parcelize
//data class Location(var lat: Double = 0.0,
                    //var lng: Double = 0.0,
                    //var zoom: Float = 0f) : Parcelable
