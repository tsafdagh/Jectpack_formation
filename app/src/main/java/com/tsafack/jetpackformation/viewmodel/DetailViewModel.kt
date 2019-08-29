package com.tsafack.jetpackformation.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.tsafack.jetpackformation.model.DogBreed

class DetailViewModel: ViewModel() {
    val dogLivedata = MutableLiveData<DogBreed>()

    fun fetch(){
        val dog = DogBreed("1", "Cordi", "15 years", "breedgroup", "bredFor","temperament", "url")
        dogLivedata.value = dog

    }
}