package ie.setu.wildswimming.ui.detail

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import ie.setu.wildswimming.databinding.FragmentSwimspotDetailBinding
import ie.setu.wildswimming.ui.auth.LoggedInViewModel
import ie.setu.wildswimming.ui.swimspotlist.SwimspotListViewModel
import timber.log.Timber

class SwimspotDetailFragment : Fragment() {

    private lateinit var detailViewModel: SwimspotDetailViewModel
    private val args by navArgs<SwimspotDetailFragmentArgs>()
    private var _fragBinding: FragmentSwimspotDetailBinding? = null
    private val fragBinding get() = _fragBinding!!
    private val loggedInViewModel : LoggedInViewModel by activityViewModels()
    private val swimspotListViewModel : SwimspotListViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _fragBinding = FragmentSwimspotDetailBinding.inflate(inflater, container, false)
        val root = fragBinding.root

        detailViewModel = ViewModelProvider(this).get(SwimspotDetailViewModel::class.java)
        detailViewModel.observableSwimspot.observe(viewLifecycleOwner, Observer { render() })

        fragBinding.editSwimspotButton.setOnClickListener {
            detailViewModel.updateSwimspot(loggedInViewModel.liveFirebaseUser.value?.uid!!,
                args.swimspotid, fragBinding.swimspotvm?.observableSwimspot!!.value!!)
            findNavController().navigateUp()
        }

        fragBinding.deleteSwimspotButton.setOnClickListener {
            swimspotListViewModel.delete(loggedInViewModel.liveFirebaseUser.value?.email!!,
                detailViewModel.observableSwimspot.value?.uid!!)
            findNavController().navigateUp()
        }

        return root
    }

    private fun render() {

        fragBinding.editName.setText("name")
        fragBinding.editCounty.setText("county")
        fragBinding.editCategorey.setText("categorey")
        fragBinding.swimspotvm = detailViewModel
    }

    override fun onResume() {
        super.onResume()
        detailViewModel.getSwimspot(loggedInViewModel.liveFirebaseUser.value?.uid!!,
            args.swimspotid)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _fragBinding = null
    }
}