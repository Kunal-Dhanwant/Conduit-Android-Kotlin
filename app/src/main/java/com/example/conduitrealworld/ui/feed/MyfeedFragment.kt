package com.example.conduitrealworld.ui.feed

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.get
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.conduitrealworld.databinding.FragmentMyfeedBinding
import com.example.conduitrealworld.repository.ConduitApiRepository
import com.example.conduitrealworld.services.ConduitApi
import com.example.conduitrealworld.services.ConduitAuthApi
import com.example.conduitrealworld.services.RetroFitHelper
import com.example.conduitrealworld.ui.globe.ArticleFeedAdaptar
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MyfeedFragment : Fragment() {

    private var _binding: FragmentMyfeedBinding? = null
    lateinit var  myfeedViewModel: MyfeedViewModel
    private   var feedAdaptar= ArticleFeedAdaptar()


    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

       val conduitApi = RetroFitHelper.getInstance().create(ConduitApi::class.java)
        val repository = ConduitApiRepository(conduitApi)


        myfeedViewModel = ViewModelProvider(this,MyViewModelFactory(repository)).get(MyfeedViewModel::class.java)




        _binding = FragmentMyfeedBinding.inflate(inflater, container, false)
        val root: View = binding.root

        myfeedViewModel.feed.observe({lifecycle}){
            feedAdaptar.submitList(it)
        }
        binding.globeFeedRv.layoutManager = LinearLayoutManager(context)
        binding.globeFeedRv.hasFixedSize()
        binding.globeFeedRv.adapter= feedAdaptar




        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}