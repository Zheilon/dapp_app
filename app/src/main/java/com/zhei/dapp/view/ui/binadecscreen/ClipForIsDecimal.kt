package com.zhei.dapp.view.ui.binadecscreen
import android.util.Log
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.absoluteOffset
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.layout.layout
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.platform.LocalView
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.zhei.dapp.MyFont
import com.zhei.dapp.ui.theme.GreenFive
import com.zhei.dapp.ui.theme.RedFour
import com.zhei.dapp.view.viewmodels.BinadecScreenViewModel

/*
* Como logre el desplasamiento de este clip, bueno...
* primero, tome el ancho total del row que era de 75.dp
* luego esos 75.dp los transforme a Px() para que puedise
* haber un desplazamiento, y usarlo en .graphicsLayer {},
* despues de de esto al desplasamiento actual, le reste
* el ancho de clip que es de 30.dp, entonces a esos
* 75.dp - 30.dp = 45.dp para luego ser transformados a px()
* with(LocalDensity.current) { 45.dp.toPx() }*/

private val clipSize = 30.dp
private val backWidth = 75.dp
private val accurracy_translation = backWidth - clipSize


@Preview
@Composable fun ClipForIsDecimal (
    viewBinadec: BinadecScreenViewModel = viewModel()
) {
    Column(
        modifier = Modifier.wrapContentSize(), 
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.Start
    ) {

        Text(
            text = "Is decimal?", 
            fontSize = 11.sp,
            fontFamily = MyFont.robotoRegular,
            color = Color.White
        )

        Spacer(modifier = Modifier.height(2.dp))

        ClipOnn(viewBinadec = viewBinadec)
    }
}


@Composable
fun ClipOnn(viewBinadec: BinadecScreenViewModel)
{
    val isDecimal by viewBinadec::isDecimal
    val trX = translationClip(isDecimal = isDecimal)
    val colorClip = if (isDecimal) GreenFive else RedFour

    Log.e("Is Decimal", isDecimal.toString())

    Box (
        modifier = Modifier.height(30.dp).width(backWidth)
            .background(Color.White, RoundedCornerShape(8.dp))
            .pointerInput(Unit) {
                detectTapGestures {
                    viewBinadec.updateIsDecimal()
                }
            }
    ) {

        Surface(
            modifier = Modifier
                .fillMaxHeight()
                .width(clipSize)
                .graphicsLayer { translationX = trX },
            color = colorClip,
            shape = RoundedCornerShape(8.dp)
        ) {}
    }
}


@Composable fun translationClip (isDecimal: Boolean) : Float
{
    return animateFloatAsState(
        targetValue = if (isDecimal)
            with(LocalDensity.current) { accurracy_translation.toPx() } else 0f,
        animationSpec = tween(400), label = "").value
}
