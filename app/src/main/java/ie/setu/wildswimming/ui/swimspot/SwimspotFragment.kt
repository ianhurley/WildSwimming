package ie.setu.wildswimming.ui.swimspot

import android.app.Activity.RESULT_CANCELED
import android.app.Activity.RESULT_OK
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.view.MenuHost
import androidx.core.view.MenuProvider
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.NavigationUI
import com.squareup.picasso.Picasso
import ie.setu.wildswimming.R
import ie.setu.wildswimming.databinding.FragmentSwimspotBinding
//import ie.setu.wildswimming.main.WildSwimmingApp
import ie.setu.wildswimming.models.SwimspotModel
import ie.setu.wildswimming.ui.auth.LoggedInViewModel
import ie.setu.wildswimming.ui.swimspotlist.SwimspotListViewModel
import ie.setu.wildswimming.utils.showImagePicker
import timber.log.Timber

class SwimspotFragment : Fragment() {


    //lateinit var app: WildSwimmingApp
    private var _fragBinding: FragmentSwimspotBinding? = null
    private val fragBinding get() = _fragBinding!!
    private lateinit var swimspotViewModel: SwimspotViewModel
    private val swimspotListViewModel: SwimspotListViewModel by activityViewModels()
    private val loggedInViewModel : LoggedInViewModel by activityViewModels()
    private lateinit var imageIntentLauncher : ActivityResultLauncher<Intent>


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _fragBinding = FragmentSwimspotBinding.inflate(inflater, container, false)
        val root = fragBinding.root

        setupMenu()

        swimspotViewModel = ViewModelProvider(this).get(SwimspotViewModel::class.java)
        swimspotViewModel.observableStatus.observe(viewLifecycleOwner, Observer { status ->
            status?.let { render(status) }
        })

        fragBinding.name.setText("")
        fragBinding.county.setText("")
        fragBinding.categorey.setText("")

        setButtonListener(fragBinding)


        fragBinding.choosePhoto.setOnClickListener {
            showImagePicker(imageIntentLauncher, requireContext())
            Timber.i("add photo")
        }

        /*fragBinding.swimspotLocation.setOnClickListener {
            val location = Location(53.4494762, -7.5029786, 6f)
            if (swimspot.zoom != 0f) {
                location.lat =  swimspot.lat
                location.lng = swimspot.lng
                location.zoom = swimspot.zoom
            }
            val launcherIntent = Intent(this, MapActivity::class.java)
                .putExtra("location", location)
            mapIntentLauncher.launch(launcherIntent)
        }*/

        registerImagePickerCallback()
        //registerMapCallback()

        return root;
    }

    private fun setupMenu() {
        (requireActivity() as MenuHost).addMenuProvider(object : MenuProvider {
            override fun onPrepareMenu(menu: Menu) {
                // Handle for example visibility of menu items
            }

            override fun onCreateMenu(menu: Menu, menuInflater: MenuInflater) {
                menuInflater.inflate(R.menu.menu_swimspot, menu)
            }

            override fun onMenuItemSelected(menuItem: MenuItem): Boolean {
                // Validate and handle the selected menu item
                return NavigationUI.onNavDestinationSelected(
                    menuItem,
                    requireView().findNavController()
                )
            }
        }, viewLifecycleOwner, Lifecycle.State.RESUMED)
    }

    private fun render(status: Boolean) {
        when (status) {
            true -> {
                view?.let {

                    findNavController().popBackStack()
                }
            }

            false -> Toast.makeText(context, getString(R.string.swimspotError), Toast.LENGTH_LONG)
                .show()
        }
    }

    fun setButtonListener(layout: FragmentSwimspotBinding) {
        layout.buttonAddSwimspot.setOnClickListener {

            val name = layout.name.text.toString()
            val county = layout.county.text.toString()
            val categorey = layout.categorey.text.toString()

            Timber.i("Name: $name")
            Timber.i("County: $county")
            Timber.i("Categorey: $categorey")


            swimspotViewModel.addSwimspot(loggedInViewModel.liveFirebaseUser,SwimspotModel(name = name, county = county, categorey = categorey,email = loggedInViewModel.liveFirebaseUser.value?.email!!))

        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _fragBinding = null
    }

    override fun onResume() {
        super.onResume()

    }

    private fun registerImagePickerCallback() {
        imageIntentLauncher =
            registerForActivityResult(ActivityResultContracts.StartActivityForResult())
            { result ->
                when(result.resultCode){
                    RESULT_OK -> {
                        if (result.data != null) {
                            Timber.i("Got Result ${result.data!!.data}")

                            val image = result.data!!.data!!
                            //contentResolver.takePersistableUriPermission(image,
                                //Intent.FLAG_GRANT_READ_URI_PERMISSION)
                            //swimspot.photo = image

                            Picasso.get()
                                .load(image)
                                .into(fragBinding.swimspotPhoto)
                            //fragBinding.choosePhoto.setText(R.string.change_swimspot_photo)
                        }
                    }
                    RESULT_CANCELED -> { } else -> { }
                }
            }
    }

    /*private fun registerMapCallback() {
        mapIntentLauncher =
            registerForActivityResult(ActivityResultContracts.StartActivityForResult())
            { result ->
                when (result.resultCode) {
                    RESULT_OK -> {
                        if (result.data != null) {
                            Timber.i("Got Location ${result.data.toString()}")
                            val location = result.data!!.extras?.getParcelable<Location>("location")!!
                            Timber.i("Location == $location")
                            swimspot.lat = location.lat
                            swimspot.lng = location.lng
                            swimspot.zoom = location.zoom
                        } // end of if
                    }
                    RESULT_CANCELED -> { } else -> { }
                }
            }
    }*/
}
