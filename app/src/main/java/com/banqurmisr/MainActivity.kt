package com.banqurmisr

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.banquemisr.challenge05.ui.theme.BankMisrTheme
import com.banqurmisr.chanllenge5.domain.models.MovieTypes
import com.banqurmisr.chanllenge5.domain.models.NavigationItem
import com.banqurmisr.chanllenge5.presentaion.details.DetailScreen
import com.banqurmisr.chanllenge5.presentaion.home.HomeScreen
import com.banqurmisr.chanllenge5.presentaion.home.HomeViewModel
import com.banqurmisr.chanllenge5.presentaion.home.MoviesList
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            BankMisrTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {

                    val navController = rememberNavController()
                    MyApp {

                        NavHost(
                            navController = navController,
                            startDestination = NavigationItem.Home.route
                        )
                        {

                            composable(NavigationItem.Home.route){

                                HomeScreen(navController)
                            }
                            composable("detail/{movieId}"){backStackEntry ->
                                val movieId = backStackEntry.arguments?.getString("movieId")
                                requireNotNull(movieId) { "Movie parameter wasn't found. Please make sure it's set!" }
                                DetailScreen(navController, movieId)
                            }


                        }

                    }
                }
            }
        }
    }
}


@Composable
fun MyApp(content: @Composable () -> Unit) {
    content()

}