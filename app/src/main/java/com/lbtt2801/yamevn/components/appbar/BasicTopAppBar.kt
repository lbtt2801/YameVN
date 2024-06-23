package com.lbtt2801.yamevn.components.appbar

import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Badge
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.lbtt2801.yamevn.R
import com.lbtt2801.yamevn.navigation.Screens

@Composable
fun BasicTopAppBar(
    navController: NavController,
    modifier: Modifier = Modifier,
    title: String = "",
    sizeCart: Int = 0,
    isShowLogo: Boolean? = false,
    @DrawableRes navIcon: Int? = null,
    @DrawableRes searchIcon: Int? = R.drawable.ic_search,
    @DrawableRes cartIcon: Int? = R.drawable.ic_cart,
    @DrawableRes profileIcon: Int? = R.drawable.ic_profile,
    onNavIconClicked: () -> Unit = {},
    onSearchIconClicked: () -> Unit = {},
    onCartIconClicked: () -> Unit = {},
    onProfileIconClicked: () -> Unit = {},
) {
    Box(
        modifier = modifier
            .background(Color.Black)
            .fillMaxWidth()
            .height(50.dp)
            .padding(horizontal = 12.dp)
    ) {
        if (isShowLogo == true) {
            Image(
                painter = painterResource(id = R.drawable.yame_logo),
                contentDescription = "logo",
                modifier = Modifier
                    .width(145.dp)
                    .height(50.dp)
                    .clickable { navController.popBackStack(Screens.Home.route, false) },
                contentScale = ContentScale.FillWidth
            )
        }
        navIcon?.let {
            Icon(
                painter = painterResource(it),
                contentDescription = "nav_icon",
                modifier = Modifier
                    .align(Alignment.CenterStart)
                    .clickable { onNavIconClicked() },
                tint = Color.White
            )
        }
        Text(
            text = title,
            modifier = Modifier.padding(start = 45.dp).align(Alignment.CenterStart).fillMaxWidth(0.8f),
            style = TextStyle(
                fontSize = 20.sp,
                color = Color.White,
            ),
            maxLines = 1,
            overflow = TextOverflow.Ellipsis
        )

        Row(
            modifier = Modifier.align(Alignment.CenterEnd),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            searchIcon?.let {
                Icon(
                    painter = painterResource(it),
                    contentDescription = "search icon",
                    modifier = Modifier
                        .size(32.dp)
                        .clickable { onSearchIconClicked() }
                        .padding(end = 10.dp),
                    tint = Color.White
                )
            }

            cartIcon?.let {
                Box {
                    Icon(
                        painter = painterResource(it),
                        contentDescription = "cart icon",
                        modifier = Modifier
                            .size(32.dp)
                            .clickable { onCartIconClicked() }
                            .padding(end = 10.dp),
                        tint = Color.White
                    )
                    if (sizeCart > 0) {
                        Badge(
                            modifier = Modifier
                                .align(Alignment.TopEnd)
                                .padding(end = 5.dp)
                        ) {
                            Text(
                                text = sizeCart.toString(),
                                fontSize = 12.sp,
                                fontWeight = FontWeight.Bold,
                            )
                        }
                    }
                }
            }

            profileIcon?.let {
                Icon(
                    painter = painterResource(it),
                    contentDescription = "profile icon",
                    modifier = Modifier
                        .size(32.dp)
                        .clickable { onProfileIconClicked() },
                    tint = Color.White
                )
            }
        }

    }
}