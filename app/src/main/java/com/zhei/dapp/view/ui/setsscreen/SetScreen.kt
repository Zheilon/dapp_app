package com.zhei.dapp.view.ui.setsscreen
import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
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
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AddBox
import androidx.compose.material.icons.filled.DeleteSweep
import androidx.compose.material.icons.filled.DoneAll
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.zhei.dapp.COMPLEMENT
import com.zhei.dapp.DIFERENCIA
import com.zhei.dapp.INTERCCION
import com.zhei.dapp.MyFont
import com.zhei.dapp.R
import com.zhei.dapp.SUB_SET
import com.zhei.dapp.UNION
import com.zhei.dapp.data.models.SetsEntity
import com.zhei.dapp.data.repository.SetsRepository
import com.zhei.dapp.ui.theme.YellowHead
import com.zhei.dapp.ui.theme.YellowSeven
import com.zhei.dapp.view.ui.binadecscreen.renderColorInfinityTransition
import com.zhei.dapp.view.viewmodels.SetsScreenViewModel


@Preview
@Composable fun SetsScreen (
    viewSets: SetsScreenViewModel = viewModel()
) {
    val listsets by viewSets.listSets.collectAsState()

    LaunchedEffect(key1 = listsets) {
        viewSets.updateUniversalSet()
        Log.e("Execute", "Desde LaunchEffect")
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {

        BackgroundImageSetsScreen()

        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            HeaderAndSomeInto(viewSets = viewSets)

            Spacer(modifier = Modifier.height(20.dp))

            ShowResponseSet(viewSets = viewSets)
        }

        BottomFormula(viewSets = viewSets)
    }
}


@Composable fun HeaderAndSomeInto (
    viewSets: SetsScreenViewModel
) {
    Surface (
        modifier = Modifier
            .fillMaxWidth()
            .heightIn(min = 0.dp, max = 530.dp)
            .wrapContentHeight(),/*It Was 400.dp*/
        shape = RoundedCornerShape(bottomStart = 30.dp, bottomEnd = 30.dp),
        color = Color.Black
    ) {

        Column(
            modifier = Modifier
                .wrapContentHeight()
                .padding(bottom = 40.dp),
        ) {

            HeaderRow(viewSets = viewSets)

            LazyColumnForSetsAdded(viewSets = viewSets)
        }
    }
}


@Composable fun HeaderRow (
    viewSets: SetsScreenViewModel
) {
    Row (
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 40.dp, start = 30.dp, end = 30.dp),
        horizontalArrangement = Arrangement.Start,
        verticalAlignment = Alignment.CenterVertically
    ) {

        Text(
            text = "Agrega conjuntos!",
            color = Color.White,
            fontFamily = MyFont.soraSemibold,
            fontSize = 18.sp
        )

        Spacer(modifier = Modifier.weight(1f))

        ImageToDeleteSets(viewSets = viewSets)

        Spacer(modifier = Modifier.width(10.dp))

        ImageToAddSets(viewSets = viewSets)
    }
}


@Composable fun BackgroundImageSetsScreen ()
{
    Column (
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Image(
            painter = painterResource(id = R.drawable.imgdapp1),
            contentDescription = "", 
            modifier = Modifier.fillMaxSize()
                .scale(1.5f)
                .alpha(0.04f)
        )
    }

}


@Composable fun ImageToDeleteSets (
    viewSets: SetsScreenViewModel
) {
    val listSets by viewSets.listSets.collectAsState()

    Column (
        modifier = Modifier
            .size(45.dp)
            .pointerInput(Unit) {
                detectTapGestures {
                    viewSets.delete()
                    viewSets.updateUniversalSet()
                }
            },
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Image(
            imageVector = Icons.Default.DeleteSweep,
            contentDescription = "add",
            colorFilter = ColorFilter.tint(Color.White),
            modifier = Modifier.fillMaxSize()
        )
    }
}


@Composable fun ImageToAddSets (
    viewSets: SetsScreenViewModel
) {
    Column (
        modifier = Modifier
            .size(45.dp)
            .pointerInput(Unit) {
                detectTapGestures {
                    viewSets.create()
                    viewSets.updateUniversalSet()
                }
            },
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Image(
            imageVector = Icons.Default.AddBox,
            contentDescription = "add",
            colorFilter = ColorFilter.tint(Color.White),
            modifier = Modifier.fillMaxSize()
        )
    }
}


@Composable fun ShowResponseSet (
    viewSets: SetsScreenViewModel
) {
    val resultSet by viewSets.resultSet.collectAsState()
    val universal by viewSets.universalSet.collectAsState()

    /*va un if!*/

    Row (
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .padding(start = 20.dp, end = 20.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Start
    ) {

        Column (
            modifier = Modifier.fillMaxWidth(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.Start
        ) {

            Text(
                text = "U = $universal",
                fontFamily = MyFont.soraSemibold,
                fontSize = 21.sp,
                color = Color.Black
            )

            if (resultSet.resultSet.isNotEmpty()) {
                Text(
                    text = "${resultSet.expression} = ${resultSet.resultSet}",
                    fontFamily = MyFont.garamondMedium,
                    fontSize = 21.sp,
                    color = Color.Black
                )
            }
        }
    }
}


@Composable fun LazyColumnForSetsAdded (
    viewSets: SetsScreenViewModel
) {
    val origin by viewSets.listSets.collectAsState()

    val lsit = listOf(SetsEntity("A", setOf()))

    Log.e("List", origin.toString())

    LazyColumn (
        modifier = Modifier
            .wrapContentHeight()
    ) {

        items(origin, key = { it.keyName }) {
            SetAdderList(
                viewSets = viewSets,
                item = it
            )
        }
    }
}


@Composable fun SetAdderList (
    viewSets: SetsScreenViewModel,
    item: SetsEntity
) {

    val elements = remember { mutableStateListOf("", "", "", "", "") }

    LaunchedEffect(Unit) {
        /*viewSets.update(
            keyName = item.keyName,
            resultSet = elements.toSet()
        )
        viewSets.updateUniversalSet()*/
        snapshotFlow { elements.toList() }
            .collect { updateList ->
                viewSets.update(
                    keyName = item.keyName,
                    set = updateList.toSet()
                )
                viewSets.updateUniversalSet()
            }
    }
    
    Column (
        modifier = Modifier
            .wrapContentSize()
            .padding(start = 30.dp, end = 30.dp)
            .height(50.dp)
            .clickable {
                Log.e("Estoy tocando este", "este: ${item.keyName}")

            }
    ) {

        Row (
            modifier = Modifier
                .fillMaxWidth(),
            verticalAlignment = Alignment.Bottom,
            horizontalArrangement = Arrangement.Start
        ) {

            Column (
                modifier = Modifier
                    .height(40.dp)
                    .width(25.dp)
                    .background(Color.White, RoundedCornerShape(topStart = 5.dp, topEnd = 5.dp)),
                verticalArrangement = Arrangement.Bottom,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {

                Text(
                    text = item.keyName,
                    fontFamily = MyFont.soraSemibold,
                    fontSize = 18.sp,
                )
            }

            Spacer(modifier = Modifier.width(10.dp))

            TextFieldForPutSetElements(
                onSendInfo = { elements[0] = it },
                elements = elements
            )

            CommaInter()

            TextFieldForPutSetElements(
                onSendInfo = { elements[1] = it },
                elements = elements
            )

            CommaInter()

            TextFieldForPutSetElements(
                onSendInfo = { elements[2] = it },
                elements = elements
            )

            CommaInter()

            TextFieldForPutSetElements(
                onSendInfo = { elements[3] = it },
                elements = elements
            )

            CommaInter()

            TextFieldForPutSetElements(
                onSendInfo = { elements[4] = it },
                elements = elements
            )
        }
    }
}


@Composable fun TextFieldForPutSetElements (
    onSendInfo: (String) -> Unit,
    elements: List<Any>
) {
    var text by remember { mutableStateOf("") }
    val maxChars = 2
    onSendInfo(text)

    TextField(
        value = text,
        onValueChange = {
            if (it.length <= maxChars) text = it
        },
        modifier = Modifier.width(55.dp),
        keyboardOptions = KeyboardOptions(
            keyboardType = KeyboardType.Text,
            imeAction = ImeAction.Done
        ),
        textStyle = TextStyle(
            fontSize = 15.sp,
            fontFamily = MyFont.robotoRegular,
            color = Color.White,
            textAlign = TextAlign.Center
        ),
        colors = TextFieldDefaults.colors(
            focusedContainerColor = Color.Transparent,
            unfocusedContainerColor = Color.Transparent,
            focusedIndicatorColor = Color.White,
            unfocusedIndicatorColor = Color.White
        ),
        minLines = 1,
        maxLines = 2
    )
}


@Composable fun CommaInter ()
{
    Column (
        modifier = Modifier.fillMaxHeight(), 
        verticalArrangement = Arrangement.Bottom,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Text(
            text = ",",
            fontFamily = MyFont.soraSemibold,
            fontSize = 15.sp,
            color = Color.White
        )
    }
}


@Composable fun BottomFormula (
    viewSets: SetsScreenViewModel
) {
    val origin by viewSets.listSets.collectAsState()

    Column (
        modifier = Modifier
            .fillMaxSize(),
        verticalArrangement = Arrangement.Bottom,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Column (
            modifier = Modifier
                .wrapContentSize()
                .background(
                    color = Color.Black,
                    RoundedCornerShape(topStart = 30.dp, topEnd = 30.dp)
                )
                .border(
                    1.dp,
                    brush = Brush.verticalGradient(
                        listOf(Color.White, Color.Transparent)
                    ),
                    RoundedCornerShape(topStart = 30.dp, topEnd = 30.dp)
                )
                .padding(20.dp),
            verticalArrangement = Arrangement.SpaceBetween,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            FormulaAreaToDo(viewSets = viewSets)

            Spacer(modifier = Modifier.height(10.dp))

            Text(
                text = "Operadores",
                fontFamily = MyFont.robotoRegular,
                fontSize = 13.sp,
                color = YellowSeven,
                fontWeight = FontWeight.Thin
            )

            OperatorsForSetsArea(viewSets = viewSets)

            Spacer(modifier = Modifier.height(10.dp))

            Text(
                text = "Separadores",
                fontFamily = MyFont.robotoRegular,
                fontSize = 13.sp,
                color = YellowSeven,
                fontWeight = FontWeight.Thin
            )

            SeparatorsForSetsArea(viewSets = viewSets)

            Spacer(modifier = Modifier.height(10.dp))

            Text(
                text = if (origin.isNotEmpty()) "Conjuntos Diponibles: ${origin.size}" else "Sin Conjuntos",
                fontFamily = MyFont.robotoRegular,
                fontSize = 13.sp,
                color = YellowSeven,
                fontWeight = FontWeight.Thin
            )

            LazyRowBottonSets(viewSets = viewSets)
        }

    }
}


@Composable fun SeparatorsForSetsArea (
    viewSets: SetsScreenViewModel
) {
    Row (
        modifier = Modifier
            .fillMaxWidth()
            .height(30.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center
    ) {

        SepatorLlaves(viewSets = viewSets)

        Spacer(modifier = Modifier.width(5.dp))

        SepatorCorchete(viewSets = viewSets)

        Spacer(modifier = Modifier.width(5.dp))

        SepatorParentesis(viewSets = viewSets)
    }
}


@Composable fun SepatorCorchete (
    viewSets: SetsScreenViewModel
) {
    Surface (
        modifier = Modifier
            .fillMaxHeight()
            .width(50.dp)
            .pointerInput(Unit) {
                detectTapGestures {
                    viewSets.addCorcheteWith()
                }
            },
        color = YellowHead,
        shape = RoundedCornerShape(5.dp)
    ) {

        Column (
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Text (
                text = "[ ]",
                fontFamily = MyFont.soraSemibold,
                fontSize = 18.sp
            )
        }
    }
}


@Composable fun SepatorLlaves (
    viewSets: SetsScreenViewModel
) {
    Surface (
        modifier = Modifier
            .fillMaxHeight()
            .width(50.dp)
            .pointerInput(Unit) {
                detectTapGestures {
                    viewSets.addLlavesWith()
                }
            },
        color = YellowHead,
        shape = RoundedCornerShape(5.dp)
    ) {

        Column (
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Text (
                text = "{ }",
                fontFamily = MyFont.soraSemibold,
                fontSize = 18.sp
            )
        }
    }
}


@Composable fun SepatorParentesis (
    viewSets: SetsScreenViewModel
) {
    Surface (
        modifier = Modifier
            .fillMaxHeight()
            .width(50.dp)
            .pointerInput(Unit) {
                detectTapGestures {
                    viewSets.addParentesisWith()
                }
            },
        color = YellowHead,
        shape = RoundedCornerShape(5.dp)
    ) {

        Column (
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Text (
                text = "( )",
                fontFamily = MyFont.soraSemibold,
                fontSize = 18.sp
            )
        }
    }
}


@Composable fun LazyRowBottonSets (
    viewSets: SetsScreenViewModel
) {
    val origin by viewSets.listSets.collectAsState()

    val sets = listOf(
        SetsEntity("A", setOf()),
        SetsEntity("B", setOf())
    )

    LazyRow (
        modifier = Modifier
            .widthIn(min = 0.dp, max = 270.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center
    ) {

        itemsIndexed(origin, key = { i, item -> "${item}-$i" }
        ) { index, item ->
            CustomLazyRowItemBottom(
                index = index,
                item = item,
                list = origin,/*Change*/
                viewSets = viewSets
            )
        }
    }
}


@Composable fun CustomLazyRowItemBottom (
    index: Int,
    item: SetsEntity,
    list: List<SetsEntity>, 
    viewSets: SetsScreenViewModel
) {
    Surface (
        modifier = Modifier
            .width(50.dp)
            .height(30.dp)
            .pointerInput(Unit) {
                detectTapGestures {
                    viewSets.addKeyNameOnField(item.keyName)
                }
            },
        color = YellowHead,
        shape = RoundedCornerShape(5.dp)
    ) {

        Column (
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Text (
                text = item.keyName,
                fontFamily = MyFont.soraSemibold,
                fontSize = 18.sp
            )
        }
    }

    if (index < list.size - 1) {
        Spacer(modifier = Modifier.width(5.dp))
    }
}


@Composable fun OperatorsForSetsArea (
    viewSets: SetsScreenViewModel
) {
    Row (
        modifier = Modifier
            .fillMaxWidth()
            .height(30.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center
    ) {

        ButtonMainSetsOperator(
            concept = UNION,
            viewSets = viewSets
        )

        Spacer(modifier = Modifier.width(5.dp))

        ButtonMainSetsOperator(
            concept = INTERCCION,
            viewSets = viewSets
        )

        Spacer(modifier = Modifier.width(5.dp))

        ButtonMainSetsOperator(
            concept = DIFERENCIA,
            viewSets = viewSets
        )

        Spacer(modifier = Modifier.width(5.dp))

        ButtonMainSetsOperator(
            concept = SUB_SET,
            viewSets = viewSets
        )

        Spacer(modifier = Modifier.width(5.dp))

        ButtonMainSetsOperator(
            concept = COMPLEMENT,
            viewSets = viewSets
        )
    }
}


@Composable fun ButtonMainSetsOperator (
    concept: String,
    viewSets: SetsScreenViewModel
) {
    Surface (
        modifier = Modifier
            .fillMaxHeight()
            .pointerInput(Unit) {
                detectTapGestures {
                    when (concept) {
                        UNION -> viewSets.addUnion()
                        INTERCCION -> viewSets.addIntersect()
                        DIFERENCIA -> viewSets.addDifference()
                        SUB_SET -> viewSets.addSubSet()
                        COMPLEMENT -> viewSets.addComplement()
                    }
                }
            }
            .width(50.dp),
        color = YellowHead,
        shape = RoundedCornerShape(5.dp)
    ) {

        Column (
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Text (
                text = concept,
                fontFamily = MyFont.soraSemibold,
                fontSize = 18.sp
            )
        }
    }
}


@Composable fun FormulaAreaToDo (
    viewSets: SetsScreenViewModel
) {
    Row (
        modifier = Modifier
            .fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Start
    ) {

        ImageToDeleteChars(viewSets = viewSets)

        Spacer(modifier = Modifier.weight(1f))

        Row (
            modifier = Modifier
                .wrapContentWidth()
                .height(40.dp)
                .width(245.dp)
                .background(Color.White, RoundedCornerShape(7.dp)),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Start
        ) {

            Text(
                text = viewSets.fieldFormula.value,
                fontSize = 14.sp,
                fontFamily = MyFont.robotoRegular,
                modifier = Modifier
                    .padding(start = 10.dp, end = 10.dp)
                    .fillMaxWidth(),
                lineHeight = 12.sp,
                textAlign = TextAlign.Center
            )
        }

        Spacer(modifier = Modifier.weight(1f))

        ImageToAGenerateResult(viewSets = viewSets)
    }
}


@Composable fun ImageToDeleteChars (
    viewSets: SetsScreenViewModel
){
    Column (
        modifier = Modifier
            .size(40.dp)
            .border(1.dp, YellowHead, RoundedCornerShape(10.dp))
            .pointerInput(Unit) {
                detectTapGestures {
                    viewSets.deleteLastOnField()
                }
            },
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Image(
            imageVector = Icons.Default.DeleteSweep,
            contentDescription = "done",
            colorFilter = ColorFilter.tint(YellowHead)
        )
    }
}


@Composable fun ImageToAGenerateResult (
    viewSets: SetsScreenViewModel
) {
    Column (
        modifier = Modifier
            .size(40.dp)
            .border(1.dp, YellowHead, RoundedCornerShape(10.dp))
            .pointerInput(Unit) {
                detectTapGestures {
                    viewSets.getSimplexAnalizer()
                }
            }
            .background(
                if (viewSets.fieldFormula.value.isNotEmpty()) {
                    renderColorInfinityTransition(
                        initalV = Color.Black,
                        targetV = YellowHead,
                        tween = 2500
                    )
                } else {
                    Color.Transparent
                },
                RoundedCornerShape(10.dp)

            ),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Image(
            imageVector = Icons.Default.DoneAll,
            contentDescription = "done",
            colorFilter = ColorFilter.tint(
                if (viewSets.fieldFormula.value.isNotEmpty()) {
                    renderColorInfinityTransition(
                        initalV = YellowHead,
                        targetV = Color.Black,
                        tween = 2500
                    )
                } else { YellowHead },
            )
        )
    }
}



