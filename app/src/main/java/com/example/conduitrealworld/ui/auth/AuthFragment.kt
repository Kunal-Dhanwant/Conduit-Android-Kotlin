package com.example.conduitrealworld.ui.auth

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.ui.setupWithNavController
import com.example.conduitrealworld.R
import com.example.conduitrealworld.databinding.FragmentAuthBinding

class AuthFragment : Fragment() {

    private var _binding: FragmentAuthBinding? = null
    private var navController: NavController? = null


    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {


        _binding = FragmentAuthBinding.inflate(inflater, container, false)
        val root: View = binding.root



        navController = binding?.let { Navigation.findNavController(it.root.findViewById(R.id.fragmentContainerView)) }
        navController?.let { binding.bottomNavigationView.setupWithNavController(it) }






        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}