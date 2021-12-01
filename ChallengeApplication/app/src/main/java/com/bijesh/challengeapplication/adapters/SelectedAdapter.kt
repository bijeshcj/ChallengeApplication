package com.bijesh.challengeapplication.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.bijesh.challengeapplication.R
import com.bijesh.challengeapplication.adapters.listeners.RecyclerItemSelectedListener
import com.bijesh.challengeapplication.models.DogBreeds
import com.bijesh.challengeapplication.models.desmodels.SelectedBreedItem
import com.bumptech.glide.Glide


class SelectedAdapter(): RecyclerView.Adapter<SelectedViewHolder>() {


    var selectedDogBreed = mutableListOf<SelectedBreedItem>()



    fun setSelectedDogBreedList(selectedDogBreed: List<SelectedBreedItem>){
        this.selectedDogBreed = selectedDogBreed.toMutableList()
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SelectedViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.recyclerview_item,parent,false)


        return SelectedViewHolder(view)
    }

    override fun onBindViewHolder(holder: SelectedViewHolder, position: Int) {
        val dogBreed = selectedDogBreed[position]
        Glide.with(holder.itemView.context).load(dogBreed.url).fitCenter().centerCrop().into(holder.imageView1)

    }

    override fun getItemCount(): Int {
        return selectedDogBreed.size
    }
}

class SelectedViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    val imageView1 : ImageView = itemView.findViewById(R.id.imageView)

}
