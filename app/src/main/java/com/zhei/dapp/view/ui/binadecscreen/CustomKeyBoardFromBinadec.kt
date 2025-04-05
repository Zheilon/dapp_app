package com.zhei.dapp.view.ui.binadecscreen
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
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DeleteSweep
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.zhei.dapp.MyFont
import com.zhei.dapp.view.viewmodels.BinadecScreenViewModel


@Preview
@Composable fun CustomeKeyBoardForBinadec (
    viewBinadec: BinadecScreenViewModel = viewModel()
) {
    Column (
        modifier = Modifier
            .wrapContentSize()
            .padding(20.dp),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.Start
    ) {

        Row (
            modifier = Modifier.fillMaxWidth()
        ) {

            Column (
                modifier = Modifier
                    .wrapContentSize()
                    .width(190.dp)
            ) {

                Row (
                    modifier = Modifier.wrapContentSize()
                ) {

                    Numbers(7, viewBinadec = viewBinadec)
                    Spacer(modifier = Modifier.width(5.dp))

                    Numbers(8, viewBinadec = viewBinadec)
                    Spacer(modifier = Modifier.width(5.dp))

                    Numbers(9, viewBinadec = viewBinadec)
                }

                Spacer(modifier = Modifier.height(5.dp))

                Row (
                    modifier = Modifier.wrapContentSize()
                ) {

                    Numbers(4, viewBinadec = viewBinadec)
                    Spacer(modifier = Modifier.width(5.dp))

                    Numbers(5, viewBinadec = viewBinadec)
                    Spacer(modifier = Modifier.width(5.dp))

                    Numbers(6, viewBinadec = viewBinadec)
                }

                Spacer(modifier = Modifier.height(5.dp))

                Row (
                    modifier = Modifier.wrapContentSize()
                ) {

                    Numbers(1, viewBinadec = viewBinadec)
                    Spacer(modifier = Modifier.width(5.dp))

                    Numbers(2, viewBinadec = viewBinadec)
                    Spacer(modifier = Modifier.width(5.dp))

                    Numbers(3, viewBinadec = viewBinadec)
                }

                Spacer(modifier = Modifier.height(5.dp))

                NumberZero(number = 0, viewBinadec = viewBinadec)
            }


            Spacer(modifier = Modifier.weight(1f))


            Column (
                modifier = Modifier
                    .wrapContentSize()
                    .width(125.dp)
            ) {

                Row (
                    modifier = Modifier.wrapContentSize()
                ) {

                    Letters(letter = "A", viewBinadec = viewBinadec)
                    Spacer(modifier = Modifier.width(5.dp))

                    Letters(letter = "B", viewBinadec = viewBinadec)

                }
                
                Spacer(modifier = Modifier.height(5.dp))

                Row (
                    modifier = Modifier.wrapContentSize()
                ) {

                    Letters(letter = "C", viewBinadec = viewBinadec)
                    Spacer(modifier = Modifier.width(5.dp))

                    Letters(letter = "D", viewBinadec = viewBinadec)

                }

                Spacer(modifier = Modifier.height(5.dp))

                Row (
                    modifier = Modifier.wrapContentSize()
                ) {

                    Letters(letter = "E", viewBinadec = viewBinadec)
                    Spacer(modifier = Modifier.width(5.dp))

                    Letters(letter = "F", viewBinadec = viewBinadec)

                }

                Spacer(modifier = Modifier.height(5.dp))

                Deletter(viewBinadec = viewBinadec)
            }
        }
    }
}


@Composable fun Numbers(number: Int, viewBinadec: BinadecScreenViewModel)
{
    Box (
        modifier = Modifier
            .height(40.dp)
            .width(60.dp)
            .background(Color.Black, RoundedCornerShape(5.dp))
            .pointerInput(Unit) {
                detectTapGestures {
                    when (number) {
                        1 -> viewBinadec.updateText("1")
                        2 -> viewBinadec.updateText("2")
                        3 -> viewBinadec.updateText("3")
                        4 -> viewBinadec.updateText("4")
                        5 -> viewBinadec.updateText("5")
                        6 -> viewBinadec.updateText("6")
                        7 -> viewBinadec.updateText("7")
                        8 -> viewBinadec.updateText("8")
                        9 -> viewBinadec.updateText("9")
                    }
                }
            }
    ) {

        Column (
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Text(
                text = number.toString(),
                fontFamily = MyFont.soraSemibold,
                fontSize = 14.sp,
                color = Color.White
            )
        }
    }
}


@Composable fun NumberZero(number: Int, viewBinadec: BinadecScreenViewModel)
{
    Box (
        modifier = Modifier
            .height(40.dp)
            .fillMaxWidth()
            .background(Color.Black, RoundedCornerShape(5.dp))
            .pointerInput(Unit) {
                detectTapGestures {
                    when (number) {
                        0 -> viewBinadec.updateText("0")
                    }
                }
            }
    ) {

        Column (
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Text(
                text = number.toString(),
                fontFamily = MyFont.soraSemibold,
                fontSize = 14.sp,
                color = Color.White
            )
        }
    }
}


@Composable fun Letters(letter: String, viewBinadec: BinadecScreenViewModel)
{
    Box (
        modifier = Modifier
            .height(40.dp)
            .width(60.dp)
            .background(Color.Black, RoundedCornerShape(5.dp))
            .pointerInput(Unit) {
                detectTapGestures {
                    when (letter) {
                        "A" -> viewBinadec.updateText("A")
                        "B" -> viewBinadec.updateText("B")
                        "C" -> viewBinadec.updateText("C")
                        "D" -> viewBinadec.updateText("D")
                        "E" -> viewBinadec.updateText("E")
                        "F" -> viewBinadec.updateText("F")
                    }
                }
            }
    ) {

        Column (
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Text(
                text = letter,
                fontFamily = MyFont.soraSemibold,
                fontSize = 14.sp,
                color = Color.White
            )
        }
    }
}


@Composable fun Deletter(viewBinadec: BinadecScreenViewModel)
{
    Box (
        modifier = Modifier
            .height(40.dp)
            .fillMaxWidth()
            .background(Color.Black, RoundedCornerShape(5.dp))
            .pointerInput(Unit) {
                detectTapGestures {
                    viewBinadec.deleteText()
                }
            }
    ) {

        Column (
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Image(
                imageVector = Icons.Default.DeleteSweep,
                contentDescription = "delete",
                colorFilter = ColorFilter.tint(Color.White)
            )
        }
    }
}