package com.tsafack.jetpackformation.view


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders

import com.tsafack.jetpackformation.R
import com.tsafack.jetpackformation.viewmodel.DetailViewModel
import kotlinx.android.synthetic.main.fragment_detail.*

class DetailFragment : Fragment() {

    private lateinit var viewModel:DetailViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_detail, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProviders.of(this).get(DetailViewModel::class.java)
        viewModel.fetch()

        /*arguments?.let {
            dogUuid = DetailFragmentArgs.fromBundle(it).dogUuid
        }*/

        obserVeViewModel()
    }

    private fun obserVeViewModel() {
        viewModel.dogLivedata.observe(this, Observer {dog->
            dog?.let {
                dogName.text = dog.dogBreed
                dogTemperament.text = dog.temperament
                dogLifespan.text = dog.lifeSoan
                dogPurpose.text = dog.bredFor
            }
        })
    }
}
