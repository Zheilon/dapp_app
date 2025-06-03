package com.zhei.dapp.view.ui.binadecscreen.archivetransforms
import android.content.res.Resources
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.border
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
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.itemsIndexed
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Paint
import androidx.compose.ui.graphics.nativeCanvas
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.style.BaselineShift
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.content.res.ResourcesCompat
import androidx.lifecycle.viewmodel.compose.viewModel
import com.zhei.dapp.MyFont
import com.zhei.dapp.R
import com.zhei.dapp.data.models.TransformsBEntity
import com.zhei.dapp.ui.theme.BLACK_ONE
import com.zhei.dapp.ui.theme.BLACK_TWO
import com.zhei.dapp.ui.theme.ORANGE_ONE
import com.zhei.dapp.ui.theme.YellowSeven
import com.zhei.dapp.view.viewmodels.BinadecScreenViewModel

@Preview
@Composable fun TransformsScreenBinadec(
    viewBinadec: BinadecScreenViewModel = viewModel()
) {
    viewBinadec.executeDetailTransform()

    Box(
        modifier = Modifier.fillMaxSize()
            .background(BLACK_TWO)
    ) {
        Column (
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            HeaderTransforms()

            Spacer(modifier = Modifier.height(20.dp))

            LazyResponsesTransforms(viewBinadec = viewBinadec)
        }
    }
}


@Composable fun HeaderTransforms ()
{
    Row (
        modifier = Modifier
            .fillMaxWidth()
            .height(100.dp)
            .background(Color.Black, RoundedCornerShape(bottomStart = 5.dp, bottomEnd = 5.dp)),
        horizontalArrangement = Arrangement.Center, 
        verticalAlignment = Alignment.CenterVertically
    ) {

        Text(
            text = "Transformación",
            color = Color.White,
            fontSize = 18.sp,
            fontFamily = MyFont.soraSemibold
        )
    }
}


@Composable
fun LazyResponsesTransforms(
    viewBinadec: BinadecScreenViewModel
) {
    val origin by viewBinadec.listWithDetailPross.collectAsState()

    val testing = listOf(
        TransformsBEntity(
            elevateExp = "2",
            result = "34 - 43 = 11"
        )
    )

    LazyColumn (
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 20.dp, end = 20.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {

        items(origin) { item ->
            CustomeDetailTransforms(item = item)
        }
    }
}


@Composable fun CustomeDetailTransforms(
    item: TransformsBEntity
) {
    Row (
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .background(Color.Transparent, RoundedCornerShape(10.dp)),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center
    ) {

        Box (
            modifier = Modifier
                .size(40.dp)
                .background(Color.Black, RoundedCornerShape(10.dp)),
        ) {

            Column (
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {

                PowerTextOnCanvas(base = "2", exponent = item.elevateExp)
            }
        }

        Spacer(modifier = Modifier.width(10.dp))

        Row (
            modifier = Modifier
                .wrapContentWidth()
                .heightIn(min = 40.dp, max = 200.dp)
                .background(Color.Black, RoundedCornerShape(10.dp)),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {

            Text(
                text = item.result,
                fontSize = 15.sp,
                fontFamily = MyFont.soraRegular,
                color = Color.White,
                modifier = Modifier
                    .padding(10.dp)
                    .weight(1f)
            )
        }
    }
    
    Spacer(modifier = Modifier.height(10.dp))
}


@Composable
fun PowerTextOnCanvas(base: String, exponent: String)
{
    val context = LocalContext.current
    val soraRegu = remember { ResourcesCompat.getFont(context, R.font.soraregular) }

    Canvas(
        modifier = Modifier.size(100.dp)
    ) {

        val basePaint = Paint().asFrameworkPaint().apply {
            isAntiAlias = true
            textSize = 40f
            color = android.graphics.Color.WHITE
            typeface = soraRegu
        }

        val exponentPaint = Paint().asFrameworkPaint().apply {
            isAntiAlias = true
            textSize = 20f // más pequeño
            color = android.graphics.Color.WHITE
            typeface = soraRegu
        }

        // Posiciones básicas
        drawContext.canvas.nativeCanvas.drawText(base, 38f, 65f, basePaint)

        // Posición del exponente (a la derecha y más arriba)
        val baseWidth = basePaint.measureText(base)

        drawContext.canvas.nativeCanvas.drawText(
            exponent,
            baseWidth + 35f,
            37f,
            exponentPaint
        )
    }
}

