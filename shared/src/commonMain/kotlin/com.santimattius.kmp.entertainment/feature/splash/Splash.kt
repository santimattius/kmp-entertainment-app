package com.santimattius.kmp.entertainment.feature.splash

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import coil3.ColorImage
import coil3.annotation.ExperimentalCoilApi
import coil3.compose.AsyncImagePreviewHandler
import coil3.compose.LocalAsyncImagePreviewHandler
import com.santimattius.kmp.entertainment.core.ui.themes.AppTheme
import com.santimattius.kmp.entertainment.shared.generated.resources.Res
import com.santimattius.kmp.entertainment.shared.generated.resources.watching_a_movie
import kotlinx.coroutines.delay
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.ui.tooling.preview.Preview

private const val SPLASH_IMAGE =
    "https://img.freepik.com/vector-gratis/produccion-peliculas-cine-composicion-transparente-realista-imagen-aislada-badajo-campos-vacios-ilustracion-vectorial_1284-66163.jpg?w=740&t=st=1694904624~exp=1694905224~hmac=936e49d4984b5c24111a43ff7835c3962d6cdf002d489f5ed196716577f7d2c3"

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

    SplashComponent(modifier = Modifier.scale(scale.value))
}

@Composable
private fun SplashComponent(modifier: Modifier = Modifier) {
    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier.fillMaxSize()
    ) {
        Image(
            painter = painterResource(Res.drawable.watching_a_movie),
            modifier = modifier,
            contentDescription = null
        )
        //TODO: test coil preview
        /*NetworkImage(
            image = SPLASH_IMAGE,
            contentDescription = "Logo",
            modifier = modifier,
            contentScale = ContentScale.Crop
        )*/
    }
}

@OptIn(ExperimentalCoilApi::class)
@Preview
@Composable
fun SplashPreview() {
    AppTheme {
        //TODO: test coil preview
        val previewHandler = AsyncImagePreviewHandler {
            ColorImage(Color.Red.toArgb())
        }
        CompositionLocalProvider(LocalAsyncImagePreviewHandler provides previewHandler) {
            SplashComponent()
        }
    }
}