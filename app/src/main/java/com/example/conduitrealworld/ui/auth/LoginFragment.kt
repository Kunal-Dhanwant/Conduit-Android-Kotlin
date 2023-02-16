package com.example.conduitrealworld.ui.auth

import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import android.util.Patterns
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.conduitrealworld.MainActivity
import com.example.conduitrealworld.R
import com.example.conduitrealworld.Utils.NetworkResult
import com.example.conduitrealworld.Utils.TokenManager
import com.example.conduitrealworld.databinding.FragmentLoginBinding
import com.example.conduitrealworld.databinding.FragmentSignupBinding
import com.example.conduitrealworld.modules.entites.LoginData
import com.example.conduitrealworld.modules.entites.SignupData
import com.example.conduitrealworld.repository.UserRepository
import com.example.conduitrealworld.services.ConduitApi
import com.example.conduitrealworld.services.RetroFitHelper


class LoginFragment : Fragment() {

    private var _binding: FragmentLoginBinding? = null
    lateinit var authViewModel: AuthViewModel
    lateinit var tokenManager: TokenManager
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        tokenManager = TokenManager(requireContext())
        val conduitApi = RetroFitHelper.getInstance().create(ConduitApi::class.java)
        val userRepository = UserRepository(conduitApi)

        authViewModel = ViewModelProvider(
            this,
            AuthViewModelFactory(userRepository)
        ).get(AuthViewModel::class.java)
        _binding = FragmentLoginBinding.inflate(inflater, container, false)





        return _binding?.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        _binding?.apply {
            btnLogin.setOnClickListener {
                val result = getuserInput()
                if(result.first==false){
                    _binding?.txtError?.text = result.second
                }else{
                    val email = _binding?.txtEmail?.text.toString()
                    val password = _binding?.txtPassword?.text.toString()

                    authViewModel.loginuser(LoginData(email, password))

                }


            }
        }

        authViewModel.userResponseLivedata.observe(viewLifecycleOwner, Observer {
            _binding?.apply {
                idPBLoading.isVisible = false
            }
            when (it) {
                is NetworkResult.Success -> {
                    val token = tokenManager.saveToken(it.data?.user?.token.toString())
                    Toast.makeText(activity, "Text!", Toast.LENGTH_SHORT).show();
                    val intent = Intent(this@LoginFragment.requireContext(), MainActivity::class.java)
                    startActivity(intent)

                }
                is NetworkResult.Error -> {

                    _binding?.apply {
                        txtError.text = it.message
                    }
                }

                is NetworkResult.Loading -> {

                    _binding?.apply {
                        idPBLoading.isVisible = true
                    }

                }
            }
        })
    }


    fun getuserInput(): Pair<Boolean, String> {
        val email = _binding?.txtEmail?.text.toString()
        val password = _binding?.txtPassword?.text.toString()


        return validCredentials(password, email)

    }

    fun validCredentials(password: String, email: String): Pair<Boolean, String> {

        var result = Pair(true, "")
        if (TextUtils.isEmpty(password) || TextUtils.isEmpty(email)) {
            result = Pair(false, "Please provide valid credentials")
        } else if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            result = Pair(false, "Please Enter Valid Email")
        } else if (password.length <= 5) {
            result = Pair(false, "Password length should be greater than 5")
        }

        return result


    }
}