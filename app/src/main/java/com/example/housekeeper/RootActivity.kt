package com.example.housekeeper

import android.os.Bundle
import android.view.Menu
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.ui.graphics.Color
import androidx.navigation.compose.rememberNavController
import com.example.housekeeper.databinding.ActivityRootBinding
import com.example.housekeeper.navigation.AppNavGraph
import com.example.housekeeper.navigation.BottomNavBar

class RootActivity : AppCompatActivity() {

    private var _binding: ActivityRootBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        _binding = ActivityRootBinding.inflate(layoutInflater)
//        setContentView(binding.root)
//        val navHostFragment =
//            supportFragmentManager.findFragmentById(R.id.rootFragmentContainerView) as NavHostFragment
//        val navController = navHostFragment.navController
//        binding.bottomNavigationView.setupWithNavController(navController)
//        navController.addOnDestinationChangedListener { _, destination, _ ->
//            when (destination.id) {
//                R.id.calculatorFragment,R.id.categoryConstructorFragment-> {
//                    binding.bottomNavigationView.visibility = View.GONE
//                }
//
//                else -> {
//                    binding.bottomNavigationView.visibility = View.VISIBLE
//                }
//            }
//        }
        setContent {
            val navController = rememberNavController()

            Surface(color = Color.White) {
                Scaffold(
                    bottomBar = {
                        BottomNavBar(navController = navController)
                    }, content = { padding ->
                        AppNavGraph(navController = navController)
                    }
                )
            }
        }
    }


    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu)
        return true
    }

}