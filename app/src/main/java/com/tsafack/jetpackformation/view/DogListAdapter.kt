package com.tsafack.jetpackformation.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.tsafack.jetpackformation.R
import com.tsafack.jetpackformation.databinding.ItemDogBinding
import com.tsafack.jetpackformation.model.DogBreed
import com.tsafack.jetpackformation.util.getProgressDrawable
import com.tsafack.jetpackformation.util.loaImage
import kotlinx.android.synthetic.main.item_dog.view.*
import java.util.zip.Inflater

class DogListAdapter(val dogList: ArrayList<DogBreed>) : RecyclerView.Adapter<DogListAdapter.DogViewHolder>(), DogClickListener {

    fun updateDogList(newdogList: List<DogBreed>) {
        dogList.clear()
        dogList.addAll(newdogList)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DogViewHolder {
        val inflater = LayoutInflater.from(parent.context)

        //val view = inflater.inflate(R.layout.item_dog, parent, false)
        val view = DataBindingUtil.inflate<ItemDogBinding>(inflater, R.layout.item_dog, parent,false)
        return DogViewHolder(view)
    }

    override fun getItemCount() = dogList.size

    override fun onBindViewHolder(holder: DogViewHolder, position: Int) {

        holder.view.dog = dogList[position]
        holder.view.listener = this
 /*       holder.view.dog_name_item.text = dogList[position].dogBreed
        holder.view.dog_title_item.text = dogList[position].lifeSoan
        holder.view.setOnClickListener {

            val action = ListFragmentDirections.actionListFragmentToDetailFragment()
            action.doUid = dogList[position].uuid
            Navigation.findNavController(it).navigate(action)
        }
        holder.view.imageView.loaImage(dogList[position].imageurl, getProgressDrawable(holder.view.imageView.context))*/
    }

    override fun onDogClicked(v: View) {
        val uuid = v.dogId.text.toString().toInt()

        val action = ListFragmentDirections.actionListFragmentToDetailFragment()
        action.doUid = uuid
        Navigation.findNavController(v).navigate(action)
    }

    class DogViewHolder(var view: ItemDogBinding) : RecyclerView.ViewHolder(view.root)
}