package com.funnit.pagetransition

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController

@Composable
fun LetInspire(navController: NavController, theMuse: Muse) {

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(text = stringResource(id = R.string.let_inspire))
                },
                backgroundColor = colorResource(id = R.color.deep_orange),
                contentColor = colorResource(id = R.color.text_icons)
            )

        },
        content = {
            Column(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.SpaceEvenly,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.image),
                        contentDescription = stringResource(id = R.string.let_inspire)
                    )
                    Text(
                        text = stringResource(id = R.string.let_inspire),
                        color = colorResource(id = R.color.primary_color),
                        fontWeight = FontWeight.Bold,
                        fontSize = 36.sp
                    )
                }
                Text(
                    text = theMuse.quote,
                    modifier = Modifier.padding(8.dp),
                    color = colorResource(
                        id = R.color.primary_text
                    ),
                    fontWeight = FontWeight.Bold,
                    fontSize = 32.sp,
                    textAlign = TextAlign.Center
                )

                Text(
                    text = theMuse.person,
                    modifier = Modifier.padding(8.dp),
                    color = colorResource(
                        id = R.color.dark_amber
                    ),
                    fontWeight = FontWeight.Medium,
                    fontSize = 16.sp,
                    textAlign = TextAlign.Center
                )

                Button(
                    onClick = {
                        navController.navigate("FoodRecipe") {
                            popUpTo("LittleMuse") {
                                inclusive = true
                            }
                        }
                    },
                    colors = ButtonDefaults.buttonColors(
                        backgroundColor = colorResource(id = R.color.amber),
                        contentColor = colorResource(id = R.color.secondary_text)
                    )
                ) {
                    Text(text = stringResource(id = R.string.let_inspire))
                }

                Button(
                    onClick = { navController.popBackStack() },
                    colors = ButtonDefaults.buttonColors(
                        backgroundColor = colorResource(id = R.color.dark_amber),
                        contentColor = colorResource(id = R.color.primary_color)
                    )
                ) {
                    Text(text = stringResource(id = R.string.main_page))
                }
            }

        }
    )

}