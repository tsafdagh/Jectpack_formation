package com.tsafack.jetpackformation.viewmodel

import android.app.Application
import androidx.lifecycle.MutableLiveData
import com.tsafack.jetpackformation.model.DogBreed
import com.tsafack.jetpackformation.model.DogDatabase
import kotlinx.coroutines.launch

class DetailViewModel(application: Application) : BaseViewModel(application) {
    val dogLivedata = MutableLiveData<DogBreed>()

    fun fetch(uuid: Int) {

        launch {
            val dog = DogDatabase(getApplication()).dogDao().getDog(uuid)
            dogLivedata.value = dog
        }
    }
}