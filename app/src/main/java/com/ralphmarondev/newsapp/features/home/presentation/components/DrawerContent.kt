package com.ralphmarondev.newsapp.features.home.presentation.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Close
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Info
import androidx.compose.material.icons.outlined.Settings
import androidx.compose.material.icons.outlined.Share
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.NavigationDrawerItemDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberAsyncImagePainter
import com.ralphmarondev.newsapp.R

@Composable
fun DrawerContent(
    closeDrawer: () -> Unit
) {
    val drawerItems = listOf(
        NavigationModel(
            icon = Icons.Outlined.Home,
            label = "Home",
            onClick = {}
        ),
        NavigationModel(
            icon = Icons.Outlined.Settings,
            label = "Settings",
            onClick = {}
        ),
        NavigationModel(
            icon = Icons.Outlined.Share,
            label = "Share",
            onClick = {}
        ),
        NavigationModel(
            icon = Icons.Outlined.Info,
            label = "About",
            onClick = {}
        )
    )

    ModalDrawerSheet(
        modifier = Modifier
            .statusBarsPadding()
            .systemBarsPadding()
            .padding(end = 48.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxHeight()
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp)
                    .background(MaterialTheme.colorScheme.primaryContainer)
                    .padding(16.dp),
                verticalArrangement = Arrangement.Bottom
            ) {
                Image(
                    painter = rememberAsyncImagePainter(R.drawable.cute_me),
                    contentDescription = "",
                    modifier = Modifier
                        .size(100.dp)
                        .clip(CircleShape),
                    contentScale = ContentScale.Crop
                )

                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = "Ralph Maron Eda",
                    fontFamily = FontFamily.Monospace,
                    fontSize = 20.sp,
                    fontWeight = FontWeight.W500,
                    modifier = Modifier
                        .padding(vertical = 4.dp),
                    color = MaterialTheme.colorScheme.primary
                )
                Text(
                    text = "edaralphmaron@gmail.com",
                    fontFamily = FontFamily.Monospace,
                    fontWeight = FontWeight.W400,
                    color = MaterialTheme.colorScheme.secondary
                )
            }

            Spacer(modifier = Modifier.height(4.dp))
            drawerItems.forEachIndexed { _, item ->
                NavigationDrawerItem(
                    label = {
                        Text(
                            text = item.label,
                            fontFamily = FontFamily.Monospace
                        )
                    },
                    selected = false,
                    onClick = item.onClick,
                    icon = {
                        Icon(
                            imageVector = item.icon,
                            contentDescription = item.label
                        )
                    },
                    modifier = Modifier
                        .padding(horizontal = 8.dp, vertical = 4.dp),
                    colors = NavigationDrawerItemDefaults.colors(
                        unselectedTextColor = MaterialTheme.colorScheme.onTertiaryContainer,
                        unselectedIconColor = MaterialTheme.colorScheme.onTertiaryContainer
                    )
                )
            }

            Spacer(modifier = Modifier.weight(1f))
            HorizontalDivider(modifier = Modifier.padding(horizontal = 4.dp, vertical = 8.dp))
            NavigationDrawerItem(
                label = {
                    Text(
                        text = "Close Drawer",
                        fontFamily = FontFamily.Monospace
                    )
                },
                onClick = closeDrawer,
                selected = false,
                icon = {
                    Icon(
                        imageVector = Icons.Outlined.Close,
                        contentDescription = "Close"
                    )
                },
                modifier = Modifier
                    .padding(8.dp),
                colors = NavigationDrawerItemDefaults.colors(
                    unselectedContainerColor = MaterialTheme.colorScheme.tertiaryContainer,
                    unselectedTextColor = MaterialTheme.colorScheme.onTertiaryContainer,
                    unselectedIconColor = MaterialTheme.colorScheme.onTertiaryContainer
                )
            )
            Spacer(modifier = Modifier.height(16.dp))
        }
    }
}

private data class NavigationModel(
    val icon: ImageVector,
    val label: String,
    val onClick: () -> Unit
)