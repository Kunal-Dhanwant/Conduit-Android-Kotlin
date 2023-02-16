package com.example.conduitrealworld.ui.globe

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.conduitrealworld.databinding.FragmentGlobeBinding
import com.example.conduitrealworld.repository.ConduitApiRepository
import com.example.conduitrealworld.services.ConduitApi
import com.example.conduitrealworld.services.RetroFitHelper

class GlobeFragment : Fragment() {

    lateinit var  globeViewModel: GlobeViewModel
    private var _binding: FragmentGlobeBinding? = null
    private   var feedAdaptar= ArticleFeedAdaptar()

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {






        val conduitApi = RetroFitHelper.getInstance().create(ConduitApi::class.java)
        val repository = ConduitApiRepository(conduitApi)
         globeViewModel =
            ViewModelProvider(this,GlobeViewModelFactory(repository)).get(GlobeViewModel::class.java)

        _binding = FragmentGlobeBinding.inflate(inflater, container, false)
        val root: View = binding.root








//        globeViewModel.article.observe(viewLifecycleOwner, Observer {
//          feedAdaptar.submitList(it)
//        })
        globeViewModel.article.observe({lifecycle}){
            feedAdaptar.submitList(it)
        }

      binding.globeFeedRv.layoutManager = LinearLayoutManager(context)

        binding.globeFeedRv.adapter= feedAdaptar



        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}