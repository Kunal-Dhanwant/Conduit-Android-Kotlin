package com.example.conduitrealworld.ui.auth

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter

class ViewPagerAdaptar(fm: FragmentManager) : FragmentPagerAdapter(fm) {
    override fun getCount(): Int {
       return 2
    }

    override fun getItem(position: Int): Fragment {

        when(position){
            0 ->{
                return SignupFragment()
            }
            1-> {
                return LoginFragment()
            }

            else-> {
                return SignupFragment()
            }
        }
    }

    override fun getPageTitle(position: Int): CharSequence? {
        when(position) {
            0 -> {
                return "SignUp"
            }
            1 -> {
                return "LogIn"

        }
        return super.getPageTitle(position)->null
    }
}
}