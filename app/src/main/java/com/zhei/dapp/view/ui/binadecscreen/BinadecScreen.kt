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
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowUpward
import androidx.compose.material.icons.filled.AssignmentReturned
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.scale
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
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.zhei.dapp.BINARY
import com.zhei.dapp.COMPLEMENT_NUMBERS_2
import com.zhei.dapp.DECIMAL
import com.zhei.dapp.HEX
import com.zhei.dapp.MyFont
import com.zhei.dapp.OCTAL
import com.zhei.dapp.R
import com.zhei.dapp.data.models.BinadecsEntity
import com.zhei.dapp.ui.theme.BLACK_TWO
import com.zhei.dapp.view.Screens
import com.zhei.dapp.view.ui.setsscreen.BackgroundImageSetsScreen
import com.zhei.dapp.view.viewmodels.BinadecScreenViewModel


/*Cambio de enfoque total, si es que voy a agregar
* octal, hex, complemento 2*/


@Preview
@Composable fun BinadecScreen (
    navHost: NavHostController = rememberNavController(),
    viewBinadec: BinadecScreenViewModel = viewModel()
) {
    viewBinadec.executeTransform()

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(BLACK_TWO)
    ) {

        ImageBackgroundBinadecScreen()

        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Top
        ) {

            HeaderTitleConverterBinadec(
                navHost = navHost,
                viewBinadec = viewBinadec
            )

            Spacer(modifier = Modifier.height(20.dp))

            TextFieldArea(viewBinadec = viewBinadec)

            Spacer(modifier = Modifier.weight(1f))

            Box (
                modifier = Modifier
                    .wrapContentSize()
                    .alpha(0.8f)
                    .background(Color.Black, RoundedCornerShape(topStart = 20.dp, topEnd = 20.dp))
            ) {

                CustomeKeyBoardForBinadec(viewBinadec = viewBinadec)
            }
        }
    }
}


@Composable fun ImageBackgroundBinadecScreen ()
{
    Image(
        painter = painterResource(id = R.drawable.imgdapp1),
        contentDescription = "snake",
        modifier = Modifier
            .fillMaxSize()
            .alpha(0.09f)
    )
}


@Composable fun TitlePoweredBy (
    navHost: NavHostController,
    viewBinadec: BinadecScreenViewModel
) {
    Row (
        modifier = Modifier
            .fillMaxWidth()
            .height(65.dp)
            .padding(start = 15.dp, top = 35.dp, end = 15.dp),
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

        Spacer(modifier = Modifier.weight(1f))

        ButtonForShowSumAndRest(
            navHost = navHost,
            viewBinadec = viewBinadec
        )
    }
}


@Composable fun ShowAreaForAnnuncementTransformNumb(
    viewBinadec: BinadecScreenViewModel
) {
    Row (
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 15.dp)
            .heightIn(min = 5.dp, max = 200.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Start
    ) {
        Text(
            text = "Valor a transformar: ${viewBinadec.text.value}",
            color = Color.White,
            fontSize = 10.sp,
            fontFamily = MyFont.robotoRegular,
            lineHeight = 18.sp

        )
    }
}


@Composable fun ButtonForShowSumAndRest(
    navHost: NavHostController,
    viewBinadec: BinadecScreenViewModel
) {
    if (viewBinadec.text.value.isNotEmpty()) {
        Image(
            imageVector = Icons.Default.AssignmentReturned,
            contentDescription = "return",
            colorFilter = ColorFilter.tint(Color.White),
            modifier = Modifier.size(30.dp)
                .clickable { navHost.navigate(Screens.TransformsScreen) }
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
    navHost: NavHostController,
    viewBinadec: BinadecScreenViewModel
) {

    Surface (
        modifier = Modifier
            .fillMaxWidth()
            .height(400.dp),
        color = Color.Black,
        shape = RoundedCornerShape(bottomStart = 40.dp, bottomEnd = 40.dp),
        shadowElevation = 10.dp
    ) {

        Column (
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            TitlePoweredBy(
                navHost = navHost,
                viewBinadec = viewBinadec
            )

            LazyColumnForShowTransforms(viewBinadec = viewBinadec)
        }
    }
}


@Composable fun LazyColumnForShowTransforms(viewBinadec: BinadecScreenViewModel)
{
    val originList by viewBinadec.listTransforms.collectAsState()

    if (viewBinadec.text.value.isNotEmpty()) {
        LazyColumn (
            modifier = Modifier.fillMaxWidth(),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.Start
        ) {

            items(originList, key = {it.type}) { item ->
                CustomeVisualizerLazy(item = item)
            }
        }
    }
}


@Composable fun CustomeVisualizerLazy(item: BinadecsEntity)
{
    Row (
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 15.dp)
            .heightIn(20.dp, max = 200.dp),
        horizontalArrangement = Arrangement.Start,
        verticalAlignment = Alignment.CenterVertically
    ) {

        Text(
            text = item.type.item,
            fontSize = 15.sp,
            fontFamily = MyFont.soraSemibold,
            color = Color.White,
            modifier = Modifier.padding(end = 10.dp)
        )

        Text(
            text = item.resultOf,
            fontSize = 15.sp,
            fontFamily = MyFont.robotoRegular,
            color = Color.White,
            letterSpacing = 5.sp,
            lineHeight = 15.sp,
            modifier = Modifier.padding(end = 10.dp)
        )
    }

    Spacer(modifier = Modifier.height(5.dp))
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
                unfocusedTextColor = Color.White,
                disabledIndicatorColor = Color.Transparent,
                disabledTextColor = Color.White,
                disabledContainerColor = Color.Transparent
            ),
            enabled = false,
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