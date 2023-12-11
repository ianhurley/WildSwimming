package ie.setu.wildswimming.ui.swimspotlist

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.core.view.MenuHost
import androidx.core.view.MenuProvider
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.NavigationUI
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.floatingactionbutton.FloatingActionButton
import ie.setu.swimspot.adapters.SwimspotAdapter
import ie.setu.swimspot.adapters.SwimspotClickListener
import ie.setu.wildswimming.R
import ie.setu.wildswimming.databinding.FragmentSwimspotListBinding
import ie.setu.wildswimming.main.WildSwimmingApp
import ie.setu.wildswimming.models.SwimspotModel


class SwimspotListFragment : Fragment(), SwimspotClickListener {

    lateinit var app: WildSwimmingApp
    private var _fragBinding: FragmentSwimspotListBinding? = null
    private val fragBinding get() = _fragBinding!!
    private lateinit var swimspotListViewModel: SwimspotListViewModel


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //app = activity?.application as WildSwimmingApp
        setHasOptionsMenu(true)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _fragBinding = FragmentSwimspotListBinding.inflate(inflater, container, false)
        val root = fragBinding.root
        //activity?.title = getString(R.string.action_swimspotlist)
        setupMenu()
        fragBinding.recyclerView.setLayoutManager(LinearLayoutManager(activity))

        swimspotListViewModel = ViewModelProvider(this).get(SwimspotListViewModel::class.java)
        swimspotListViewModel.observableSwimspotsList.observe(viewLifecycleOwner, Observer {
                swimspots ->
            swimspots?.let { render(swimspots) }
        })

        val fab: FloatingActionButton = fragBinding.fab
        fab.setOnClickListener {
            val action = SwimspotListFragmentDirections.actionSwimspotListFragmentToSwimspotFragment()
            findNavController().navigate(action)
        }

        return root
    }

    private fun setupMenu() {
        (requireActivity() as MenuHost).addMenuProvider(object : MenuProvider {
            override fun onPrepareMenu(menu: Menu) {
                // Handle for example visibility of menu items
            }

            override fun onCreateMenu(menu: Menu, menuInflater: MenuInflater) {
                menuInflater.inflate(R.menu.menu_swimspotlist, menu)
            }
            override fun onMenuItemSelected(menuItem: MenuItem): Boolean {
                // Validate and handle the selected menu item
                return NavigationUI.onNavDestinationSelected(menuItem,
                    requireView().findNavController())
            }     }, viewLifecycleOwner, Lifecycle.State.RESUMED)
    }

    private fun render(swimspotsList: List<SwimspotModel>) {
        fragBinding.recyclerView.adapter = SwimspotAdapter(swimspotsList,this)
        if (swimspotsList.isEmpty()) {
            fragBinding.recyclerView.visibility = View.GONE
            fragBinding.swimspotsNotFound.visibility = View.VISIBLE
        } else {
            fragBinding.recyclerView.visibility = View.VISIBLE
            fragBinding.swimspotsNotFound.visibility = View.GONE
        }
    }

    override fun onResume() {
        super.onResume()
        swimspotListViewModel.load()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _fragBinding = null
    }

    override fun onSwimspotClick(swimspot: SwimspotModel) {
        val action = SwimspotListFragmentDirections.actionSwimspotListFragmentToSwimspotDetailFragment(swimspot.id)
        findNavController().navigate(action)
    }
}