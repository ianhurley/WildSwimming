package ie.setu.wildswimming.ui.swimspot

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.MenuHost
import androidx.core.view.MenuProvider
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.NavigationUI
import ie.setu.wildswimming.R
import ie.setu.wildswimming.databinding.FragmentSwimspotBinding
//import ie.setu.wildswimming.main.WildSwimmingApp
import ie.setu.wildswimming.models.SwimspotModel
import ie.setu.wildswimming.ui.auth.LoggedInViewModel
import ie.setu.wildswimming.ui.swimspotlist.SwimspotListViewModel
import timber.log.Timber

class SwimspotFragment : Fragment() {


    //lateinit var app: WildSwimmingApp
    private var _fragBinding: FragmentSwimspotBinding? = null
    private val fragBinding get() = _fragBinding!!
    private lateinit var swimspotViewModel: SwimspotViewModel
    private val swimspotListViewModel: SwimspotListViewModel by activityViewModels()
    private val loggedInViewModel : LoggedInViewModel by activityViewModels()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _fragBinding = FragmentSwimspotBinding.inflate(inflater, container, false)
        val root = fragBinding.root
        //activity?.title = getString(R.string.action_swimspot)
        setupMenu()

        swimspotViewModel = ViewModelProvider(this).get(SwimspotViewModel::class.java)
        swimspotViewModel.observableStatus.observe(viewLifecycleOwner, Observer { status ->
            status?.let { render(status) }
        })

        fragBinding.name.setText("")
        fragBinding.county.setText("")
        fragBinding.categorey.setText("")

        setButtonListener(fragBinding)
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
}
