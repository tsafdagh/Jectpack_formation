package com.tsafack.jetpackformation.view


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.tsafack.jetpackformation.R
import com.tsafack.jetpackformation.databinding.FragmentDetailBinding
import com.tsafack.jetpackformation.viewmodel.DetailViewModel

class DetailFragment : Fragment() {

    private lateinit var viewModel: DetailViewModel
    private var dogUid = 0

    private lateinit var dataBinding: FragmentDetailBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        //return inflater.inflate(R.layout.fragment_detail, container, false)
        dataBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_detail, container, false)
        return dataBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        arguments?.let {
            dogUid = DetailFragmentArgs.fromBundle(it).doUid
        }

        viewModel = ViewModelProviders.of(this).get(DetailViewModel::class.java)
        viewModel.fetch(dogUid)


        obserVeViewModel()
    }

    private fun obserVeViewModel() {
        viewModel.dogLivedata.observe(this, Observer { dog ->
            dog?.let {

                dataBinding.dog = dog
                /* dogName.text = dog.dogBreed
                 dogTemperament.text = dog.temperament
                 dogLifespan.text = dog.lifeSoan
                 dogPurpose.text = dog.bredFor

                 context?.let {
                     dog_image_view.loaImage(dog.imageurl, getProgressDrawable(it))
                 }*/
            }
        })
    }
}
