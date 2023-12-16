package ie.setu.swimspot.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
//import com.squareup.picasso.Picasso
import ie.setu.wildswimming.databinding.CardSwimspotBinding
import ie.setu.wildswimming.models.SwimspotModel
import timber.log.Timber

interface SwimspotClickListener {
    fun onSwimspotClick(swimspot: SwimspotModel)
}

class SwimspotAdapter constructor(private var swimspots: ArrayList<SwimspotModel>, private val listener: SwimspotClickListener)
    : RecyclerView.Adapter<SwimspotAdapter.MainHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainHolder {
        val binding = CardSwimspotBinding
            .inflate(LayoutInflater.from(parent.context), parent, false)

        return MainHolder(binding)
    }

    override fun onBindViewHolder(holder: MainHolder, position: Int) {
        val swimspot = swimspots[holder.adapterPosition]
        holder.bind(swimspot, listener)
    }

    override fun getItemCount(): Int = swimspots.size

    inner class MainHolder(val binding : CardSwimspotBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(swimspot: SwimspotModel, listener: SwimspotClickListener) {
            binding.root.tag = swimspot
            binding.swimspot = swimspot
            binding.root.setOnClickListener { listener.onSwimspotClick(swimspot) }
            binding.executePendingBindings()
            //binding.name.text = swimspot.name
            //binding.county.text = swimspot.county
            //binding.categorey.text = swimspot.categorey
            //Picasso.get().load(swimspot.photo).resize(200,200).into(binding.imageIcon)
            //binding.root.setOnClickListener { listener.onSwimspotClick(swimspot,adapterPosition)}
        }
    }

    fun removeAt(position: Int) {
        swimspots.removeAt(position)
        notifyItemRemoved(position)
    }

}