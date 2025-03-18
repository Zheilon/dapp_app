package com.zhei.dapp.view.ui.binadecscreen
import android.util.Log
import androidx.compose.animation.animateColor
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowUpward
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.zhei.dapp.BINARY
import com.zhei.dapp.DECIMAL
import com.zhei.dapp.MyFont
import com.zhei.dapp.R
import com.zhei.dapp.view.ui.setsscreen.BackgroundImageSetsScreen
import com.zhei.dapp.view.viewmodels.BinadecScreenViewModel


@Preview
@Composable fun BinadecScreen (
    viewBinadec: BinadecScreenViewModel = viewModel()
) {
    viewBinadec.getResultTransform()

    Log.e("truest", viewBinadec.onPressChangerArrows.toString())

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {

        ImageBackgroundBinadecScreen()

        Column(
            modifier = Modifier.fillMaxSize()
        ) {

            HeaderTitleConverterBinadec(viewBinadec = viewBinadec)

            Spacer(modifier = Modifier.height(20.dp))

            TextFieldArea(viewBinadec = viewBinadec)

            Spacer(modifier = Modifier.height(20.dp))

            Surface (
                modifier = Modifier
                    .fillMaxSize(),
                color = Color.White,
                shape = RoundedCornerShape(topStart = 250.dp),
                shadowElevation = 15.dp
            ) {

                BackgroundImageSetsScreen()

                SelectionAreaToConvertBinadec(viewBinadec = viewBinadec)
            }

        }
    }
}


@Composable fun ImageBackgroundBinadecScreen ()
{
    Image(
        painter = painterResource(id = R.drawable.dapp2),
        contentDescription = "snake",
        modifier = Modifier.fillMaxSize()
    )
}


@Composable fun TitlePoweredBy ()
{
    Row (
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 15.dp, top = 35.dp),
        horizontalArrangement = Arrangement.Start,
        verticalAlignment = Alignment.CenterVertically
    ) {

        Text(
            text = "Powered By Zhei.",
            color = renderColorInfinityTransition(
                initalV = Color.White,
                targetV = Color.Black,
                tween = 3000
            ),
            fontFamily = MyFont.robotoRegular,
            fontSize = 10.sp
        )
    }
}


@Composable fun renderFloatInfinityTransition (initalV: Float, targetV: Float, tween: Int) : Float
{
    return rememberInfiniteTransition("").animateFloat(
        initialValue = initalV,
        targetValue = targetV,
        animationSpec = infiniteRepeatable(
            animation = tween(tween),
            repeatMode = RepeatMode.Reverse
        ),
        label = ""
    ).value
}


@Composable fun renderColorInfinityTransition (initalV: Color, targetV: Color, tween: Int) : Color
{
    return rememberInfiniteTransition("").animateColor(
        initialValue = initalV,
        targetValue = targetV,
        animationSpec = infiniteRepeatable(
            animation = tween(tween), 
            repeatMode = RepeatMode.Restart
        ), label = ""
    ).value
}


@Composable fun HeaderTitleConverterBinadec (
    viewBinadec: BinadecScreenViewModel
) {
    val onPressArrows by viewBinadec::onPressChangerArrows

    Surface (
        modifier = Modifier
            .fillMaxWidth()
            .height(400.dp),
        color = Color.Black,
        shape = RoundedCornerShape(bottomStart = 0.dp, bottomEnd = 250.dp),
        shadowElevation = 10.dp
    ) {

        Column (
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.SpaceBetween,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            TitlePoweredBy()

            Spacer(modifier = Modifier.weight(1f))

            Text(
                text = if (onPressArrows) viewBinadec.binary.value else viewBinadec.decimal.value,
                fontSize = 25.sp,
                fontFamily = MyFont.soraSemibold, 
                color = Color.White,
                letterSpacing = 5.sp
            )

            Spacer(modifier = Modifier.weight(1f))
        }
    }
}


@Composable fun SelectionAreaToConvertBinadec (
    viewBinadec: BinadecScreenViewModel
) {
    Column (
        modifier = Modifier
            .fillMaxSize()
            .imePadding()
            .padding(bottom = 20.dp),
        verticalArrangement = Arrangement.Bottom,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Row (
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
                .padding(start = 30.dp, end = 30.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {

            CardForSelectConvertion(
                toCalculus = BINARY,
                viewBinadec = viewBinadec
            )

            ImageToTurnActions(
                viewBinadec = viewBinadec
            )

            CardForSelectConvertion(
                toCalculus = DECIMAL,
                viewBinadec = viewBinadec
            )
        }
    }
}


@Composable fun CardForSelectConvertion (
    toCalculus: String,
    viewBinadec: BinadecScreenViewModel
) {
    val translation = when (toCalculus) {
        DECIMAL -> -610f
        BINARY -> 610f
        else -> 0f
    }

    val animeTranslation = cardsAnimeFunctionTranslation(
        toCalculus = toCalculus,
        viewBinadec = viewBinadec,
        translation = translation
    )

    Card(
        modifier = Modifier
            .height(50.dp)
            .width(110.dp)
            .graphicsLayer {
                translationX = animeTranslation
            },
        colors = CardDefaults.cardColors(Color.Black)
    ) {

        Column (
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Text(
                text = toCalculus,
                fontSize = 17.sp,
                fontFamily = MyFont.soraSemibold,
                color = Color.White
            )
        }
    }
}


@Composable fun cardsAnimeFunctionTranslation (
    toCalculus: String,
    viewBinadec: BinadecScreenViewModel,
    translation: Float
) : Float
{
    val onPressArrows by viewBinadec::onPressChangerArrows

    return when (toCalculus) {
        DECIMAL -> animateFloatAsState(
            targetValue = if (onPressArrows) translation else 0f,
            animationSpec = tween(500), label = "")

        BINARY -> animateFloatAsState(
            targetValue = if (onPressArrows) translation else 0f,
            animationSpec = tween(500), label = "")

        else -> animateFloatAsState(targetValue = 0f, label = "")
    }.value
}


@Composable fun TextFieldArea (
    viewBinadec: BinadecScreenViewModel
) {
    Row (
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 30.dp, end = 30.dp)
            .background(Color.Black, RoundedCornerShape(20.dp)),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center
    ) {

        TextField(
            value = viewBinadec.text.value,
            onValueChange = { viewBinadec.updateText(it) },
            modifier = Modifier.fillMaxWidth(),
            colors = TextFieldDefaults.colors(
                focusedContainerColor = Color.Transparent,
                unfocusedContainerColor = Color.Transparent,
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent,
                focusedTextColor = Color.White,
                unfocusedTextColor = Color.White
            ),
            textStyle = TextStyle(
                fontSize = 17.sp,
                fontFamily = MyFont.robotoRegular,
                textAlign = TextAlign.Center
            ),
            minLines = 1,
            maxLines = 5,
            keyboardOptions = KeyboardOptions(
                imeAction = ImeAction.Done,
                autoCorrectEnabled = false,
                keyboardType = KeyboardType.Number
            )
        )
    }
}


@Composable fun ImageToTurnActions (
    viewBinadec: BinadecScreenViewModel
) {
    val onPressArrows by viewBinadec::onPressChangerArrows

    val animeRotate by animateFloatAsState(
        targetValue = if (onPressArrows) 360f else 0f,
        animationSpec = tween(400), label = "")

    Column (
        modifier = Modifier
            .height(60.dp)
            .width(50.dp)
            .graphicsLayer {
                rotationZ = animeRotate
            }
            .pointerInput(Unit) {
                detectTapGestures {
                    viewBinadec.updateChangerArrows(!onPressArrows)
                }
            }
            .background(Color.Black, RoundedCornerShape(10.dp)),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Image(
            imageVector = Icons.Default.ArrowUpward,
            contentDescription = "",
            modifier = Modifier
                .graphicsLayer {
                    rotationZ = 90f
                },
            colorFilter = ColorFilter.tint(Color.White)
        )

        Image(
            imageVector = Icons.Default.ArrowUpward,
            contentDescription = "",
            modifier = Modifier
                .graphicsLayer {
                    rotationZ = -90f
                },
            colorFilter = ColorFilter.tint(Color.White)
        )
    }
}







