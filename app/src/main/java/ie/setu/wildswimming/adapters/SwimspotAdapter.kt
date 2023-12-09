package ie.setu.swimspot.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
//import com.squareup.picasso.Picasso
import ie.setu.wildswimming.databinding.CardSwimspotBinding
import ie.setu.wildswimming.models.SwimspotModel

//interface SwimspotListener {
    //fun onSwimspotClick(swimspot: SwimspotModel, position : Int)
//}

class SwimspotAdapter constructor(private var swimspots: List<SwimspotModel>)
    : RecyclerView.Adapter<SwimspotAdapter.MainHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainHolder {
        val binding = CardSwimspotBinding
            .inflate(LayoutInflater.from(parent.context), parent, false)

        return MainHolder(binding)
    }

    override fun onBindViewHolder(holder: MainHolder, position: Int) {
        val swimspot = swimspots[holder.adapterPosition]
        holder.bind(swimspot)
    }

    override fun getItemCount(): Int = swimspots.size

    inner class MainHolder(val binding : CardSwimspotBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(swimspot: SwimspotModel) {
            binding.name.text = swimspot.name
            binding.county.text = swimspot.county
            binding.categorey.text = swimspot.categorey
            //Picasso.get().load(swimspot.photo).resize(200,200).into(binding.imageIcon)
            //binding.root.setOnClickListener { listener.onSwimspotClick(swimspot,adapterPosition)}
        }
    }

}