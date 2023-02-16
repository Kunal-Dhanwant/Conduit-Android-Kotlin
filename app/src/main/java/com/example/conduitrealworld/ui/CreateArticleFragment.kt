package com.example.conduitrealworld.ui

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.get
import com.example.conduitrealworld.MainActivity
import com.example.conduitrealworld.R
import com.example.conduitrealworld.Utils.NetworkResult
import com.example.conduitrealworld.databinding.FragmentCreateArticleBinding
import com.example.conduitrealworld.databinding.FragmentLoginBinding
import com.example.conduitrealworld.modules.entites.ArticleData
import com.example.conduitrealworld.repository.ConduitApiRepository
import com.example.conduitrealworld.services.ConduitApi
import com.example.conduitrealworld.services.RetroFitHelper


class CreateArticleFragment : Fragment() {

    lateinit var createArticleViewModel: CreateArticleViewModel
    private var _binding: FragmentCreateArticleBinding? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val conduitApi = RetroFitHelper.getInstance().create(ConduitApi::class.java)
        val conduitApiRepository =ConduitApiRepository(conduitApi)
        createArticleViewModel = ViewModelProvider(this,CreateArticleViewModelFactory(conduitApiRepository)).get(CreateArticleViewModel::class.java)
       _binding = FragmentCreateArticleBinding.inflate(inflater,container,false)



        return  _binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        _binding?.apply {

            btnpublishArticle.setOnClickListener{

                val articledata = ArticleData(articleBody.text.toString(),articleDiscription.text.toString(), title = articleTitle.text.toString())

                createArticleViewModel.createarticle(articledata)
            }
        }


        createArticleViewModel.artciclelivedata.observe(viewLifecycleOwner, Observer {
            _binding?.apply {
                idPBLoading.isVisible = false
            }

            when (it) {
                is NetworkResult.Success -> {

                    Toast.makeText(activity, "Published succesfully!", Toast.LENGTH_SHORT).show();

                    val intent = Intent(this@CreateArticleFragment.requireContext(), MainActivity::class.java)
                    startActivity(intent)

                }
                is NetworkResult.Error -> {

                    Toast.makeText(activity, it.message, Toast.LENGTH_SHORT).show();
                }

                is NetworkResult.Loading -> {

                    _binding?.apply {
                        idPBLoading.isVisible = true
                    }

                }
            }

        })

        // next open article
    }


}