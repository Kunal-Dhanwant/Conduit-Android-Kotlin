package com.example.conduitrealworld.Utils

import android.content.Context
import androidx.lifecycle.MutableLiveData
import com.example.conduitrealworld.Utils.Constant.PREF_TOKEN_FILE
import com.example.conduitrealworld.Utils.Constant.PREF_TOKEN_KEY
import com.example.conduitrealworld.ui.auth.SignupFragment

class TokenManager(context: Context) {



    private  var pref = context.getSharedPreferences(PREF_TOKEN_FILE,Context.MODE_PRIVATE)




    fun saveToken(token:String){
        val editor = pref.edit()
        editor.putString(PREF_TOKEN_KEY,token)

        editor.apply()
    }

    fun getToken(): String? {
        return  pref.getString(PREF_TOKEN_KEY,null)
    }
    fun removetoken(){
        val ediitor= pref.edit()
        ediitor.remove(PREF_TOKEN_KEY)
        ediitor.apply()
    }

}