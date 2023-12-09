package ie.setu.wildswimming

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import ie.setu.swimspot.adapters.SwimspotAdapter
import ie.setu.wildswimming.databinding.FragmentSwimspotListBinding
import ie.setu.wildswimming.main.WildSwimmingApp


class SwimspotListFragment : Fragment() {

    lateinit var app: WildSwimmingApp
    private var _fragBinding: FragmentSwimspotListBinding? = null
    private val fragBinding get() = _fragBinding!!


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        app = activity?.application as WildSwimmingApp
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _fragBinding = FragmentSwimspotListBinding.inflate(inflater, container, false)
        val root = fragBinding.root
        activity?.title = getString(R.string.action_swimspotlist)

        fragBinding.recyclerView.setLayoutManager(LinearLayoutManager(activity))
        fragBinding.recyclerView.adapter = SwimspotAdapter(app.swimspotsStore.findAll())

        return root
    }

    companion object {
        @JvmStatic
        fun newInstance() =
            SwimspotListFragment().apply {
                arguments = Bundle().apply {}
            }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _fragBinding = null
    }
}