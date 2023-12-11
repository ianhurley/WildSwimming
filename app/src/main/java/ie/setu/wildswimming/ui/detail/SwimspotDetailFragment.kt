package ie.setu.wildswimming.ui.detail

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.navigation.fragment.navArgs
import ie.setu.wildswimming.R
import ie.setu.wildswimming.databinding.FragmentSwimspotDetailBinding

class SwimspotDetailFragment : Fragment() {

    private lateinit var detailViewModel: SwimspotDetailViewModel
    private val args by navArgs<SwimspotDetailFragmentArgs>()
    private var _fragBinding: FragmentSwimspotDetailBinding? = null
    private val fragBinding get() = _fragBinding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _fragBinding = FragmentSwimspotDetailBinding.inflate(inflater, container, false)
        val root = fragBinding.root

        detailViewModel = ViewModelProvider(this).get(SwimspotDetailViewModel::class.java)
        detailViewModel.observableSwimspot.observe(viewLifecycleOwner, Observer { render() })
        return root
    }

    private fun render() {

        fragBinding.editName.setText("name")
        fragBinding.editCounty.setText("county")
        fragBinding.swimspotvm = detailViewModel
    }

    override fun onResume() {
        super.onResume()
        detailViewModel.getSwimspot(args.swimspotid)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _fragBinding = null
    }
}