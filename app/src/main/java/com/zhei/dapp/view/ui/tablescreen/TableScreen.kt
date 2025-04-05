package com.zhei.dapp.view.ui.tablescreen
import android.annotation.SuppressLint
import android.app.Activity
import android.content.pm.ActivityInfo
import android.util.Log
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AddCircle
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.DeleteSweep
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalInspectionMode
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.zhei.dapp.BICONDICIONAL
import com.zhei.dapp.CONDICIONAL
import com.zhei.dapp.CONJUNCION
import com.zhei.dapp.CORCHETES
import com.zhei.dapp.DISYUNCION
import com.zhei.dapp.LLAVES
import com.zhei.dapp.MyFont
import com.zhei.dapp.NEGACION
import com.zhei.dapp.PARENTESIS
import com.zhei.dapp.R
import com.zhei.dapp.data.models.TablesEntity
import com.zhei.dapp.ui.theme.GreenEight
import com.zhei.dapp.ui.theme.GreenFive
import com.zhei.dapp.ui.theme.GreenFour
import com.zhei.dapp.ui.theme.GreenOne
import com.zhei.dapp.ui.theme.GreenSeven
import com.zhei.dapp.ui.theme.GreenThree
import com.zhei.dapp.ui.theme.RedFive
import com.zhei.dapp.ui.theme.RedOne
import com.zhei.dapp.ui.theme.YellowHead
import com.zhei.dapp.ui.theme.YellowSeven
import com.zhei.dapp.view.ui.binadecscreen.ImageBackgroundBinadecScreen
import com.zhei.dapp.view.viewmodels.TablesViewModel
import kotlin.math.max


@SuppressLint("ContextCastToActivity")
@Preview
@Composable fun TableScreen (
    viewTables: TablesViewModel = viewModel()
) {
    val onPresGenerate by viewTables::onPressGenerate

    val activity = LocalContext.current as? Activity

    if (onPresGenerate && activity != null && !LocalInspectionMode.current) {
        activity.requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE
    } else
        activity?.requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT

    Box (
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {

        ImageFromBackgroundTables()

        AddVariablesAreaLeft(viewTables = viewTables)

        ColumnForShowTheListResultsMain(viewTables = viewTables)

        BottomForCalculate(viewTables = viewTables)

        Column (
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {}
    }
}


@Composable fun ImageFromBackgroundTables ()
{
    Column (
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Image(
            painter = painterResource(id = R.drawable.imgdapp1),
            contentDescription = "",
            modifier = Modifier
                .fillMaxSize()
                .scale(1.5f)
                .alpha(0.04f)
        )
    }
}


@Composable fun BottomForCalculate (
    viewTables: TablesViewModel
) {
    Column (
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Bottom
    ) {

        Surface (
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight(),
            color = Color.Black,
            shadowElevation = 10.dp,
            shape = RoundedCornerShape(10.dp)
        ) {

            Column (
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(20.dp),
                verticalArrangement = Arrangement.Top,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {

                HeaderToolsArea(viewTables = viewTables)

                Spacer(modifier = Modifier.height(10.dp))

                Text(
                    text = "Operadores",
                    color = YellowSeven,
                    fontSize = 15.sp,
                    fontFamily = MyFont.robotoRegular
                )

                Spacer(modifier = Modifier.height(5.dp))

                AreaForSelectAction(viewTables = viewTables)

                Spacer(modifier = Modifier.height(10.dp))

                Text(
                    text = "Separadores",
                    color = YellowSeven,
                    fontSize = 15.sp,
                    fontFamily = MyFont.robotoRegular
                )

                Spacer(modifier = Modifier.height(5.dp))

                SeparatorAreaBottomTable(viewTables = viewTables)
            }
        }
    }
}


@Composable fun HeaderToolsArea (
    viewTables: TablesViewModel
) {
    Row (
        modifier = Modifier
            .fillMaxWidth()
            .height(45.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {

        ImageToDeleteTextField(viewTables = viewTables)

        Spacer(modifier = Modifier.weight(1f))

        Row (
            modifier = Modifier
                .width(230.dp)
                .fillMaxHeight()
                .background(Color.White, RoundedCornerShape(10.dp)),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {

            Text(
                text = viewTables.fieldText.value,
                modifier = Modifier.fillMaxWidth(),
                textAlign = TextAlign.Center,
                color = Color.Black,
                fontFamily = MyFont.robotoRegular,
                fontSize = 17.sp
            )
        }


        Spacer(modifier = Modifier.weight(1f))

        ImageToGenerateTextField(viewTables = viewTables)
    }
}


@Composable fun ImageToDeleteTextField (
    viewTables: TablesViewModel
) {
    Column (
        modifier = Modifier
            .size(45.dp)
            .background(Color.Black, RoundedCornerShape(10.dp))
            .border(1.dp, YellowHead, RoundedCornerShape(10.dp))
            .pointerInput(Unit) {
                detectTapGestures {
                    viewTables.deleteFieldVar()
                }
            },
        verticalArrangement = Arrangement.Center, 
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Image(
            imageVector = Icons.Default.DeleteSweep,
            contentDescription = "delete",
            colorFilter = ColorFilter.tint(YellowHead)
        )
    }
}


@SuppressLint("ContextCastToActivity")
@Composable fun ImageToGenerateTextField (
    viewTables: TablesViewModel
) {
    val onPress by viewTables::onPressGenerate

    Column (
        modifier = Modifier
            .size(45.dp)
            .background(Color.Black, RoundedCornerShape(10.dp))
            .border(1.dp, YellowHead, RoundedCornerShape(10.dp))
            .pointerInput(Unit) {
                detectTapGestures {
                    viewTables.generate()
                    viewTables.updateOnPressGenerate(!onPress)
                }
            },
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Image(
            imageVector = Icons.Default.CheckCircle,
            contentDescription = "delete",
            colorFilter = ColorFilter.tint(YellowHead)
        )
    }
}


@Composable fun AreaForSelectAction (
    viewTables: TablesViewModel
) {
    Row (
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center
    ) {

        ButtonForExecuteActionBottom(viewTables = viewTables, action = NEGACION)

        Spacer(modifier = Modifier.width(5.dp))

        ButtonForExecuteActionBottom(viewTables = viewTables, action = CONJUNCION)

        Spacer(modifier = Modifier.width(5.dp))

        ButtonForExecuteActionBottom(viewTables = viewTables, action = DISYUNCION)

        Spacer(modifier = Modifier.width(5.dp))

        ButtonForExecuteActionBottom(viewTables = viewTables, action = CONDICIONAL)

        Spacer(modifier = Modifier.width(5.dp))

        ButtonForExecuteActionBottom(viewTables = viewTables, action = BICONDICIONAL)
    }
}


@Composable fun ButtonForExecuteActionBottom (
    viewTables: TablesViewModel,
    action: String
) {
    Surface (
        modifier = Modifier
            .height(30.dp)
            .width(50.dp)
            .pointerInput(Unit) {
                detectTapGestures {
                    when (action) {
                        NEGACION -> viewTables.addNegation()
                        CONJUNCION -> viewTables.addConjunction()
                        DISYUNCION -> viewTables.addDisjunction()
                        CONDICIONAL -> viewTables.addConditional()
                        BICONDICIONAL -> viewTables.addBiconditional()
                        LLAVES -> viewTables.addLlavesWith()
                        CORCHETES -> viewTables.addCorChetesWith()
                        PARENTESIS -> viewTables.addParentesisWith()
                    }
                }
            },
        color = YellowHead,
        shape = RoundedCornerShape(10.dp)
    ) {

        Column (
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Text(
                text = if (action == BICONDICIONAL) "<-->" else action,
                color = Color.Black,
                fontFamily = MyFont.robotoRegular,
                fontSize = 18.sp,
                modifier = Modifier.fillMaxHeight(),
                textAlign = TextAlign.Center
            )
        }
    }
}

@Composable fun SeparatorAreaBottomTable (
    viewTables: TablesViewModel
) {
    Row (
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center
    ) {

        ButtonForExecuteActionBottom(viewTables = viewTables, action = LLAVES)

        Spacer(modifier = Modifier.width(5.dp))

        ButtonForExecuteActionBottom(viewTables = viewTables, action = CORCHETES)

        Spacer(modifier = Modifier.width(5.dp))

        ButtonForExecuteActionBottom(viewTables = viewTables, action = PARENTESIS)
    }
}


@Composable fun AddVariablesAreaLeft (
    viewTables: TablesViewModel
) {
    Column (
        modifier = Modifier
            .fillMaxSize()
            .padding(top = 50.dp),
        horizontalAlignment = Alignment.Start,
        verticalArrangement = Arrangement.Center
    ) {

        ColumnForAddVariables(viewTables = viewTables)
    }
}


@Composable fun ColumnForAddVariables (
    viewTables: TablesViewModel
) {
    Column (
        modifier = Modifier
            .background(YellowHead, RoundedCornerShape(topEnd = 20.dp))
            .width(75.dp)
            .fillMaxHeight(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        ButtonForAddVars(viewTables = viewTables)

        Spacer(modifier = Modifier.height(10.dp))

        LazyColumnForAddV(viewTables = viewTables)
    }
}


@Composable fun ButtonForAddVars (
    viewTables: TablesViewModel
) {
    Surface (
        modifier = Modifier
            .height(70.dp)
            .width(50.dp)
            .padding(top = 10.dp)
            .pointerInput(Unit) {
                detectTapGestures {
                    viewTables.generateVar()
                }
            },
        color = Color.White,
        shape = RoundedCornerShape(topEnd = 20.dp)
    ) {

        Column (
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Image(
                imageVector = Icons.Default.AddCircle,
                contentDescription = "add",
                colorFilter = ColorFilter.tint(Color.Black)
            )
        }
    }
}


@Composable fun LazyColumnForAddV (
    viewTables: TablesViewModel
) {
    val l = listOf(
        TablesEntity("A", listOf(1, 0, 1)),
        TablesEntity("B", listOf(7, 0, 3)),
        TablesEntity("E", listOf(7, 6, 3))
    )

    val listChars by viewTables.listOfVars.collectAsState()

    LazyColumn (
        modifier = Modifier
            .wrapContentHeight()
            .fillMaxWidth(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        items(listChars, key = { it } ) { item ->
            Log.e("size", listChars.size.toString())
            CustomWidgetToAddV(
                viewTables = viewTables,
                item = item)
        }
    }
}


@Composable fun CustomWidgetToAddV (
    viewTables: TablesViewModel,
    item: String
) {
    Surface (
        modifier = Modifier
            .height(70.dp)
            .width(50.dp)
            .padding(top = 10.dp)
            .pointerInput(Unit) {
                detectTapGestures {
                    viewTables.addVar(item)
                }
            },
        color = Color.Black,
        shape = RoundedCornerShape(topEnd = 20.dp),
        shadowElevation = 10.dp
    ) {

        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Text(
                text = item,
                fontSize = 22.sp,
                color = Color.White
            )
        }
    }
}


@Composable fun ColumnForShowTheListResultsMain (
    viewTables: TablesViewModel
) {
    Column (
        modifier = Modifier
            .height(627.dp)
            .padding(start = 90.dp, top = 50.dp, end = 16.5.dp),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        HeaderToShowExpressionAndIndex(viewTables = viewTables)

        Spacer(modifier = Modifier.height(5.dp))

        LazyRowForListResult(viewTables = viewTables)
    }
}


@Composable fun LazyRowForListResult (
    viewTables: TablesViewModel
) {
    val listOrigin by viewTables.listResults.collectAsState()

    val test = listOf(
        TablesEntity("A", listOf(1, 0, 1)),
        TablesEntity("B", listOf(7, 0, 3)),
        TablesEntity("E", listOf(7, 6, 3))
    )

    LazyRow (
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(5.dp)
    ) {

        itemsIndexed(listOrigin, key = { index, item -> "${item.variable}_$index" })
        { index, item ->
            PilarShowExpressionLazyColumn(
                viewTables = viewTables,
                index = index,
                itemTables = item
            )
        }
    }
}


@Composable fun PilarShowExpressionLazyColumn (
    viewTables: TablesViewModel, 
    index: Int,
    itemTables: TablesEntity
) {
    Column (
        modifier = Modifier
            .wrapContentHeight()
            .width(50.dp),
        verticalArrangement = Arrangement.Center,
    ) {

        Box (
            modifier = Modifier
                .height(30.dp)
                .background(YellowHead, RoundedCornerShape(topStart = 35.dp, topEnd = 35.dp))
        ) {

            Column (
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {

                Text(
                    text = "${index + 1}",
                    color = Color.Black,
                    fontFamily = MyFont.robotoRegular,
                    fontSize = 16.sp
                )
            }
        }

        LazyColumn (
            modifier = Modifier.wrapContentHeight()
        ) {

            items(itemTables.listVar) { result ->

                Spacer(modifier = Modifier.height(5.dp))

                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(30.dp)
                        .border(0.5.dp, YellowHead)
                ) {

                    Column (
                        modifier = Modifier.fillMaxSize(),
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {

                        Text(
                            text = "$result",
                            color = Color.Black,
                            fontFamily = MyFont.robotoRegular,
                            fontSize = 16.sp
                        )
                    }
                }
            }
        }
    }

}


@Composable
fun HeaderToShowExpressionAndIndex(
    viewTables: TablesViewModel
) {
    val listOrigin by viewTables.listResults.collectAsState()

    val test = listOf(
        TablesEntity("A", listOf(1, 0, 1)),
        TablesEntity("B", listOf(7, 0, 3)),
        TablesEntity("E", listOf(7, 6, 3))
    )

    LazyColumn (
        Modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(5.dp),
    ) {

        itemsIndexed(listOrigin, key = { index, item -> "${item}_$index" }
        ) { index, item ->
            CustomBoxForShowElementsVars(
                list = listOrigin,
                item = item,
                index = index
            )
        }
    }
}


@Composable fun CustomBoxForShowElementsVars (
    list: List<TablesEntity>,
    index: Int,
    item: TablesEntity
) {
    Row (
        modifier = Modifier
            .fillMaxWidth()
            .height(25.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Start
    ) {

        Surface(
            modifier = Modifier.size(25.dp),
            color = YellowHead,
            shape = RoundedCornerShape(25.dp)
        ) {

            Column(
                modifier = Modifier.wrapContentSize(), verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {

                Text(
                    text = "${index + 1}",
                    color = Color.Black,
                    fontFamily = MyFont.robotoRegular,
                    fontSize = 16.sp
                )
            }
        }

        Column(
            modifier = Modifier.fillMaxHeight(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Text(
                text = ". ",
                color = Color.Black,
                fontFamily = MyFont.robotoRegular,
                fontSize = 16.sp
            )
        }

        Spacer(modifier = Modifier.width(5.dp))

        Text(
            text = item.variable,
            color = Color.Black,
            fontFamily = MyFont.robotoRegular,
            fontSize = 16.sp
        )
    }
}



