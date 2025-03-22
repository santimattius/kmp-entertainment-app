package com.santimattius.kmp.entertainment.feature.splash

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.AnimationVector1D
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.layout.ContentScale
import com.santimattius.kmp.entertainment.core.ui.components.NetworkImage
import com.santimattius.kmp.entertainment.core.ui.themes.AppTheme
import com.santimattius.kmp.entertainment.shared.generated.resources.Res
import com.santimattius.kmp.entertainment.shared.generated.resources.compose_multiplatform
import kotlinx.coroutines.delay
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.ui.tooling.preview.Preview
import org.jetbrains.compose.ui.tooling.preview.PreviewParameter

@Composable
fun SplashScreen(navigate: () -> Unit) {
    val scale = remember {
        Animatable(0f)
    }

    LaunchedEffect(key1 = true) {
        scale.animateTo(
            targetValue = 0.7f,
            animationSpec = spring(
                dampingRatio = Spring.DampingRatioHighBouncy,
                stiffness = 1000f
            )
        )
        delay(800L)
        navigate()
    }

    SplashComponent(modifier=Modifier.scale(scale.value))
}

@Composable
private fun SplashComponent(modifier: Modifier = Modifier) {
    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier.fillMaxSize()
    ) {
        Image(
            painter = painterResource(Res.drawable.compose_multiplatform),
            modifier = modifier,
            contentDescription = null
        )
        /*NetworkImage(
            imageUrl = "https://img.freepik.com/vector-gratis/produccion-peliculas-cine-composicion-transparente-realista-imagen-aislada-badajo-campos-vacios-ilustracion-vectorial_1284-66163.jpg?w=740&t=st=1694904624~exp=1694905224~hmac=936e49d4984b5c24111a43ff7835c3962d6cdf002d489f5ed196716577f7d2c3",
            contentDescription = "Logo",
            modifier = modifier,
            contentScale = ContentScale.Crop
        )*/

    }
}

@Preview
@Composable
fun SplashPreview() {
    AppTheme {
        SplashComponent()
    }
}