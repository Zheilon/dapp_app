package com.zhei.dapp.view.ui.mainscreen
import androidx.compose.animation.animateColor
import androidx.compose.animation.core.RepeatMode
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
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDownward
import androidx.compose.material.icons.filled.ArrowUpward
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHost
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.zhei.dapp.BINADEC
import com.zhei.dapp.MyFont
import com.zhei.dapp.SETS
import com.zhei.dapp.TABLES
import com.zhei.dapp.ui.theme.Purple80
import com.zhei.dapp.view.Screens
import com.zhei.dapp.view.viewmodels.MainScreenViewModel


/*Para configurar cuando los elementos apareceran
* hazlo con un LaunchEffect*/

@Preview
@Composable fun MainScreen (
    viewMain: MainScreenViewModel = viewModel(),
    navHost: NavHostController = rememberNavController()
) {

    LaunchedEffect(key1 = viewMain.screenAcivated) { viewMain.isActivated() }

    Box (
        modifier = Modifier
            .fillMaxSize()
            .background(InfiniteColorBackground())
    ) {

        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.SpaceBetween,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Spacer(modifier = Modifier.weight(1f))

            IsBoxCalculus(
                toCalculus = TABLES,
                viewMain = viewMain,
                navHost = navHost
            )

            Spacer(modifier = Modifier.weight(1f))

            IsBoxCalculus(
                toCalculus = SETS,
                viewMain = viewMain,
                navHost = navHost
            )

            Spacer(modifier = Modifier.weight(1f))

            IsBoxCalculus(
                toCalculus = BINADEC,
                viewMain = viewMain,
                navHost = navHost
            )

            Spacer(modifier = Modifier.weight(1f))
        }
    }
}


@Composable fun IsBoxCalculus (
    toCalculus: String,
    viewMain: MainScreenViewModel,
    navHost: NavHostController
) {

    val finalX = when (toCalculus) {
        TABLES -> viewMain.translationTableX
        SETS -> viewMain.translationSetX
        BINADEC -> viewMain.translationBinadec
        else -> 0f
    }

    val initialX = translationFunctionBoxes(
        toCalculus = toCalculus,
        viewMain = viewMain,
        finalX = finalX
    )

    Column (
        modifier = Modifier
            .wrapContentSize()
            .graphicsLayer {
                translationX = initialX
                scaleX = if (toCalculus == SETS) initialX else 1f
                scaleY = if (toCalculus == SETS) initialX else 1f
            }
            .pointerInput(Unit) {
                detectTapGestures {
                    viewMain.updateButtonPressed(!viewMain.buttonBoxPressed)
                    when (toCalculus) {
                        TABLES ->
                            navHost.navigate(Screens.TableScreen) {
                                launchSingleTop = true
                            }

                        SETS ->
                            navHost.navigate(Screens.SetsScreen) {
                                launchSingleTop = true
                            }

                        BINADEC ->
                            navHost.navigate(Screens.BinadecScreen) {
                                launchSingleTop = true
                            }
                    }
                }
            },
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        HeaderBoxesMain(
            toCalculus = toCalculus,
            viewMain = viewMain
        )

        Spacer(modifier = Modifier.height(10.dp))

        Surface (
            modifier = Modifier
                .size(160.dp),
            color = InfiniteColorBackgroundTitles(),
            shape = RoundedCornerShape(topStart = 30.dp, bottomEnd = 30.dp, bottomStart = 30.dp),
            shadowElevation = 5.dp,
        ) {

            IntoBoxSetsCustom(toCalculus)
        }
    }
}


@Composable fun NavigatorSelectionMainScreen (
    toCalculus: String,
    navHost: NavHostController
) {


}


@Composable fun translationFunctionBoxes(
    toCalculus: String,
    viewMain: MainScreenViewModel,
    finalX: Float
) : Float
{
    val screenActive by viewMain::screenAcivated

    return when (toCalculus) {
        TABLES -> animateFloatAsState(
            targetValue = if (screenActive) finalX else 800f,
            animationSpec = tween(650), label = "")

        SETS -> animateFloatAsState(
            targetValue = if (screenActive) finalX else 0f,
            animationSpec = tween(650), label = "")

        BINADEC -> animateFloatAsState(
            targetValue = if (screenActive) finalX else -800f,
            animationSpec = tween(650), label = "")

        else -> {
            animateFloatAsState(targetValue = 0f, label = "")
        }
    }.value
}


@Composable fun translationFunctionTitles(
    toCalculus: String,
    viewMain: MainScreenViewModel,
    finalX: Float
) : Float
{
    val screenActive by viewMain::screenAcivated

    return when (toCalculus) {
        TABLES -> animateFloatAsState(
            targetValue = if (screenActive) finalX else -1700f,
            animationSpec = tween(800), label = "")

        SETS -> animateFloatAsState(
            targetValue = if (screenActive) finalX else -1200f,
            animationSpec = tween(800), label = "")

        BINADEC -> animateFloatAsState(
            targetValue = if (screenActive) finalX else 1700f,
            animationSpec = tween(800), label = "")

        else -> {
            animateFloatAsState(targetValue = 0f, label = "")
        }
    }.value
}


@Composable fun HeaderBoxesMain (
    toCalculus: String,
    viewMain: MainScreenViewModel
) {

    val finalX = when (toCalculus) {
        TABLES -> viewMain.translationTableTitleX
        SETS -> viewMain.translationSetsTitleY
        BINADEC -> viewMain.translationBinadecTitleX
        else -> 0f
    }

    val anime = translationFunctionTitles(
        toCalculus = toCalculus,
        viewMain = viewMain,
        finalX = finalX
    )

    Card(
        modifier = Modifier
            .wrapContentWidth()
            .height(25.dp)
            .graphicsLayer {
                translationX = anime
                translationY = if (toCalculus == SETS) anime else 1f
            },
        colors = CardDefaults.cardColors(InfiniteColorBackgroundTitles())
    ) {

        Column (
            modifier = Modifier.fillMaxHeight(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Text(
                text = when (toCalculus) {
                    TABLES -> "Tablas de Verdad"
                    SETS -> "Conjuntos"
                    BINADEC -> "Binario Decimal"
                    else -> "None"
                },
                modifier = Modifier.padding(start = 10.dp, end = 10.dp),
                fontSize = 17.sp,
                color = InfiniteColorBackground(),
                fontFamily = MyFont.soraSemibold
            )
        }
    }
}


@Composable fun IntoBoxSetsCustom (
    toCalculus: String
) {
    Column (
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        when (toCalculus) {

            TABLES ->
                CustomContentTables()

            SETS ->
                CustomContentSets()

            BINADEC ->
                CustomContentBinary()
        }
    }
}


@Composable fun CustomContentTables ()
{
    Text(
        text = "V or F ?",
        fontFamily = MyFont.soraSemibold,
        color = InfiniteColorBackground(),
        fontSize = 24.sp
    )
}


@Composable fun CustomContentSets ()
{
    Text(
        text = "{A - B} U E",
        fontFamily = MyFont.soraSemibold,
        color = InfiniteColorBackground(),
        fontSize = 24.sp
    )
}


@Composable fun CustomContentBinary ()
{
    Column (
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Text(
            text = "1010",
            fontFamily = MyFont.soraSemibold,
            color = InfiniteColorBackground(),
            fontSize = 24.sp
        )

        Row (
            modifier = Modifier.wrapContentWidth()
        ){

            Image(
                imageVector = Icons.Default.ArrowDownward,
                contentDescription = "hola",
                colorFilter = ColorFilter.tint(InfiniteColorBackground()),
                modifier = Modifier.size(30.dp)
            )

            Image(
                imageVector = Icons.Default.ArrowUpward,
                contentDescription = "hola",
                colorFilter = ColorFilter.tint(InfiniteColorBackground()),
                modifier = Modifier.size(30.dp)
            )
        }

        Text(
            text = "10",
            fontFamily = MyFont.soraSemibold,
            color = InfiniteColorBackground(),
            fontSize = 24.sp
        )
    }
}


@Composable fun InfiniteColorBackground () : Color
{
    val anime = rememberInfiniteTransition("")
    val color = anime.animateColor(
        initialValue = Color.White,
        targetValue = Color.Black,
        animationSpec = infiniteRepeatable(
            animation = tween(1800),
            repeatMode = RepeatMode.Reverse
        ), label = ""
    )
    return color.value
}

@Composable fun InfiniteColorBackgroundTitles () : Color
{
    val anime = rememberInfiniteTransition("")
    val color = anime.animateColor(
        initialValue = Color.Black,
        targetValue = Color.White,
        animationSpec = infiniteRepeatable(
            animation = tween(1800),
            repeatMode = RepeatMode.Reverse
        ), label = ""
    )
    return color.value
}


@Composable fun BoxCalculusOfSets ()
{
    Column (
        modifier = Modifier.wrapContentSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Card (
            modifier = Modifier
                .wrapContentWidth()
                .height(20.dp)
        ) {

            Column (
                modifier = Modifier.fillMaxHeight(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {

                Text(
                    text = "Caluladora: Conjuntos",
                    modifier = Modifier.padding(start = 10.dp, end = 10.dp),
                    fontSize = 16.sp
                )
            }
        }

        Spacer(modifier = Modifier.height(10.dp))

        Surface (
            modifier = Modifier
                .size(160.dp),
            color = Purple80,
            shape = RoundedCornerShape(topStart = 30.dp, bottomEnd = 30.dp),
            shadowElevation = 20.dp
        ) {

        }
    }
}

@Composable fun BoxTableCalculusBinary ()
{
    Column (
        modifier = Modifier.wrapContentSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Card (
            modifier = Modifier
                .wrapContentWidth()
                .height(20.dp)
        ) {

            Column (
                modifier = Modifier.fillMaxHeight(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {

                Text(
                    text = "Caluladora: Binario - Decimal",
                    modifier = Modifier.padding(start = 10.dp, end = 10.dp),
                    fontSize = 16.sp
                )
            }
        }

        Spacer(modifier = Modifier.height(10.dp))

        Surface (
            modifier = Modifier
                .size(160.dp),
            color = Purple80,
            shape = RoundedCornerShape(topStart = 30.dp, bottomEnd = 30.dp),
            shadowElevation = 20.dp
        ) {

        }
    }
}
