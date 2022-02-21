package com.funnit.pagetransition

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.funnit.pagetransition.ui.theme.PageTransitionTheme
import com.google.gson.Gson

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            PageTransitionTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    PageTransitions()
                }
            }
        }
    }
}

@Composable
fun PageTransitions() {

    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = stringResource(id = R.string.main_page)
    ) {
        composable("MainActivity") {
            MainPage(navController = navController)
        }
        composable("LittleMuse/{muse}",
            arguments = listOf(
                navArgument("muse") { type = NavType.StringType }
            )) {
            val json = it.arguments?.getString("muse")
            val muse = Gson().fromJson(json, Muse::class.java)
            LetInspire(navController = navController, muse)
        }
        composable("FoodRecipe") {
            RecipeScreen()
        }
    }
}

@Composable
fun MainPage(navController: NavController) {

    val counter = remember {
        mutableStateOf(0)
    }

    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceEvenly
    ) {
        Text(text = "Main Page", fontSize = 36.sp)

        Button(onClick = {

            val p = "Steve Jobs"
            val q =
                "Dünyayı değiştirecek insanlar, onu değiştirebileceklerini düşünecek kadar çılgın olanlardır."
            val theMuse = Muse(p, q)
            val theMuseString = Gson().toJson(theMuse)
            navController.navigate("LittleMuse/$theMuseString")
        }) {
            Text(text = stringResource(id = R.string.little_muse), fontSize = 16.sp)
        }

        Text(text = "${counter.value}", fontSize = 36.sp)

        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {

            Button(
                onClick = { counter.value = counter.value + 1 }, modifier = Modifier
                    .weight(0.5f)
                    .padding(8.dp), colors = ButtonDefaults.buttonColors(
                    backgroundColor = colorResource(id = R.color.dark_amber)
                )
            ) {
                Text(text = stringResource(id = R.string.increase), fontSize = 16.sp)
            }

            Button(
                onClick = { counter.value = counter.value - 1 }, modifier = Modifier
                    .weight(0.5f)
                    .padding(8.dp), colors = ButtonDefaults.buttonColors(
                    backgroundColor = colorResource(id = R.color.light_amber)
                )
            ) {
                Text(text = stringResource(id = R.string.decrease), fontSize = 16.sp)
            }
        }


    }

    LaunchedEffect(key1 = true) {
        Log.e("MainActivity", "LaunchEffect Run!!!")
    }


    SideEffect {
        Log.e("MainActivity", "SideEffect Run!!!")
    }

    DisposableEffect(Unit) {
        onDispose {
            Log.e("MainActivity", "DisposableEffect onDispose() Run!!!")
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    PageTransitionTheme {

    }
}