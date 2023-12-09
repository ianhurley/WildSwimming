package ie.setu.wildswimming.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.navigation.ui.NavigationUI
import ie.setu.wildswimming.R
import ie.setu.wildswimming.databinding.FragmentSwimspotBinding
import ie.setu.wildswimming.main.WildSwimmingApp
import ie.setu.wildswimming.models.SwimspotModel

class SwimspotFragment : Fragment() {

    //private lateinit var swimspotLayout: ActivitySwimspotBinding
    lateinit var app: WildSwimmingApp
    private var _fragBinding: FragmentSwimspotBinding? = null
    private val fragBinding get() = _fragBinding!!


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        app = activity?.application as WildSwimmingApp
        setHasOptionsMenu(true)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _fragBinding = FragmentSwimspotBinding.inflate(inflater, container, false)
        val root = fragBinding.root
        activity?.title = getString(R.string.action_swimspot)

        fragBinding.name.setText("")
        fragBinding.county.setText("")
        fragBinding.categorey.setText("")

        setButtonListener(fragBinding)
        return root;
    }

    fun setButtonListener(layout: FragmentSwimspotBinding) {
        layout.buttonAddSwimspot.setOnClickListener {

            val name = layout.name.text.toString()
            val county = layout.county.text.toString()
            val categorey = layout.categorey.text.toString()

            app.swimspotsStore.create(SwimspotModel (name = name, county = county, categorey = categorey))

            //startActivity(Intent(context, SwimspotList::class.java))

        }
    }

    companion object {

        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            SwimspotFragment().apply {
                arguments = Bundle().apply {

                }
            }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _fragBinding = null
    }

    override fun onResume() {
        super.onResume()
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu_swimspot, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return NavigationUI.onNavDestinationSelected(item,
            requireView().findNavController()) || super.onOptionsItemSelected(item)
    }
}