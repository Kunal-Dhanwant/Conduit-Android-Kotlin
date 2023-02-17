package com.example.conduitrealworld.ui.globe

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.conduitrealworld.Utils.TokenManager
import com.example.conduitrealworld.databinding.FragmentGlobeBinding
import com.example.conduitrealworld.repository.ConduitApiRepository
import com.example.conduitrealworld.services.ConduitApi
import com.example.conduitrealworld.services.RetroFitHelper

class GlobeFragment : Fragment() {

    lateinit var  globeViewModel: GlobeViewModel
    private var _binding: FragmentGlobeBinding? = null
    private lateinit var feedAdaptar: ArticleFeedAdaptar
    lateinit var tokenManager: TokenManager

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    @SuppressLint("SuspiciousIndentation")
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {






        feedAdaptar= ArticleFeedAdaptar(::onheartClicked)
        tokenManager = TokenManager(requireContext())
        val conduitApi = RetroFitHelper.getInstance().create(ConduitApi::class.java)
        val repository = ConduitApiRepository(conduitApi)
         globeViewModel =
            ViewModelProvider(this,GlobeViewModelFactory(repository)).get(GlobeViewModel::class.java)

        _binding = FragmentGlobeBinding.inflate(inflater, container, false)
        val root: View = binding.root








//        globeViewModel.article.observe(viewLifecycleOwner, Observer {
//          feedAdaptar.submitList(it)
//        })
        if(tokenManager.getToken()!=null){
            globeViewModel.fetchglobalarticle_afterlogin()
        }else{
            globeViewModel.fetchglobearticle()
        }


        setdata()




        return root
    }
    private  fun  onheartClicked(is_fav:Boolean,slug:String):Boolean{

        if(is_fav){
            globeViewModel.unfavouritearticle(slug)
            Log.d("check********","unfavourited")
            return true;




        }else{
            globeViewModel.favouritearticle(slug)
            Log.d("check********","favourited")
            return  false


        }




    }


    private  fun setdata(){
        globeViewModel.article.observe({lifecycle}){
            feedAdaptar.submitList(it)
        }

        binding.globeFeedRv.layoutManager = LinearLayoutManager(context)
        binding.globeFeedRv.hasFixedSize()

        binding.globeFeedRv.adapter= feedAdaptar
    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}