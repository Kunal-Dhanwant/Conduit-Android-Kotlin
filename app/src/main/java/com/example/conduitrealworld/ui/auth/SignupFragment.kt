package com.example.conduitrealworld.ui.auth

import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import android.util.Patterns
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.conduitrealworld.MainActivity
import com.example.conduitrealworld.Utils.NetworkResult
import com.example.conduitrealworld.Utils.TokenManager
import com.example.conduitrealworld.databinding.FragmentSignupBinding
import com.example.conduitrealworld.modules.entites.SignupData
import com.example.conduitrealworld.repository.UserRepository
import com.example.conduitrealworld.services.ConduitApi
import com.example.conduitrealworld.services.RetroFitHelper


class SignupFragment : Fragment() {

    private  var _binding :FragmentSignupBinding? = null
    lateinit var  authViewModel: AuthViewModel

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
        authViewModel =ViewModelProvider(this,AuthViewModelFactory(userRepository)).get(AuthViewModel::class.java)

        _binding = FragmentSignupBinding.inflate(inflater, container, false)


        if (tokenManager.getToken()!=null){
            Toast.makeText(activity,tokenManager.getToken(),Toast.LENGTH_SHORT).show();
        }



        return  _binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        _binding?.apply {
            btnSignUp.setOnClickListener {

                val result = getuserInput()
                if(result.first==false){
                    _binding?.txtError?.text = result.second
                }else{
                    val email = _binding?.txtEmail?.text.toString()
                    val password = _binding?.txtPassword?.text.toString()
                    val username = _binding?.txtUsername?.text.toString()

                    authViewModel.signupuser(SignupData(email,password,username))
                }

            }
        }



        authViewModel.userResponseLivedata.observe(viewLifecycleOwner, Observer {
            _binding?.apply {
                idPBLoading.isVisible = false
            }
            when(it){
                is NetworkResult.Success->{
                    val token = tokenManager.saveToken(it.data?.user?.token.toString())







                    val intent = Intent(this@SignupFragment.requireContext(), MainActivity::class.java)
                    startActivity(intent)




                    Toast.makeText(activity,"Text!",Toast.LENGTH_SHORT).show()


                }
                is NetworkResult.Error->{

                    _binding?.apply {
                        txtError.text = it.message
                    }
                }

                is NetworkResult.Loading->{

                    _binding?.apply {
                        idPBLoading.isVisible = true
                    }

                }
            }
        })
    }


    fun getuserInput():Pair<Boolean,String>{
        val email = _binding?.txtEmail?.text.toString()
        val password = _binding?.txtPassword?.text.toString()
        val username = _binding?.txtUsername?.text.toString()

        return validCredentials(username,password,email)

    }

    fun validCredentials(username:String,password:String,email: String):Pair<Boolean,String>{

        var result = Pair(true,"")
        if(TextUtils.isEmpty(username)||TextUtils.isEmpty(password)||TextUtils.isEmpty(email)){
            result = Pair(false,"Please provide valid credentials")
        }else if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            result= Pair(false,"Please Enter Valid Email")
        }else if(password.length<=5){
            result = Pair(false,"Password length should be greater than 5")
        }

        return  result

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding=null

    }


}