package com.example.conduitrealworld

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.navigation.NavigationView
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import androidx.drawerlayout.widget.DrawerLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.example.conduitrealworld.Utils.NetworkResult
import com.example.conduitrealworld.Utils.TokenManager
import com.example.conduitrealworld.databinding.ActivityMainBinding
import com.example.conduitrealworld.modules.Response.UserResponse
import com.example.conduitrealworld.repository.UserRepository
import com.example.conduitrealworld.services.ConduitApi
import com.example.conduitrealworld.services.RetroFitHelper
import com.example.conduitrealworld.ui.auth.AuthViewModel
import com.example.conduitrealworld.ui.auth.AuthViewModelFactory
import org.w3c.dom.Text
import retrofit2.http.Header

class MainActivity : AppCompatActivity() {

    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var binding: ActivityMainBinding
    lateinit var  authViewModel: AuthViewModel


    lateinit var  tokenManager: TokenManager





    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        tokenManager = TokenManager(this)
        val conduitApi = RetroFitHelper.getInstance().create(ConduitApi::class.java)
        val repository = UserRepository(conduitApi)
        authViewModel = ViewModelProvider(this,AuthViewModelFactory(repository)).get(AuthViewModel::class.java)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.appBarMain.toolbar)

        binding.appBarMain.fab.setOnClickListener { view ->
            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show()
        }
        val drawerLayout: DrawerLayout = binding.drawerLayout
        val navView: NavigationView = binding.navView
        val navController = findNavController(R.id.nav_host_fragment_content_main)
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.

        val header : View = navView.getHeaderView(0)
        val profile_iv :ImageView= header.findViewById(R.id.imageView)
        val username_tv :TextView= header.findViewById(R.id.username_tv)
        val bio_tv :TextView= header.findViewById(R.id.bio_tv)








        appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.nav_globe, R.id.nav_feed, R.id.nav_signup_login
            ), drawerLayout
        )
        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)


//




        val token = tokenManager.getToken()
        updateMenu(token)
        navController.navigateUp()


        if(tokenManager.getToken()!=null){
            authViewModel.getcurrentuser()
            authViewModel.userResponseLivedata.observe(this, Observer {

                bio_tv.text = it?.data?.user?.bio
                username_tv.text= it?.data?.user?.username
                Glide.with(this).load(it?.data?.user?.image).centerCrop().into(profile_iv)



            })

        }


    }



    override fun onOptionsItemSelected(item: MenuItem): Boolean {


        when(item.itemId){
            R.id.action_settings->{
                tokenManager.removetoken()
                binding.navView.menu.clear()
                binding.navView.inflateMenu(R.menu.activity_main_drawer)
                recreate()

                return true
            }
        }
        return super.onOptionsItemSelected(item)

    }






    private fun updateMenu(token:String?) {
        when (token) {
            is String -> {
                binding.navView.menu.clear()
                binding.navView.inflateMenu(R.menu.menu_main_user)
            }
            else -> {
                binding.navView.menu.clear()
                binding.navView.inflateMenu(R.menu.activity_main_drawer)
            }
        }
    }






    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.main, menu)
        return true
    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.nav_host_fragment_content_main)
        return navController.navigateUp(appBarConfiguration) || super.onSupportNavigateUp()
    }


}