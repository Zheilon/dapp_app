package com.zhei.dapp.view
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.zhei.dapp.view.ui.binadecscreen.BinadecScreen
import com.zhei.dapp.view.ui.mainscreen.MainScreen
import com.zhei.dapp.view.ui.setsscreen.SetsScreen
import com.zhei.dapp.view.ui.tablescreen.TableScreen
import com.zhei.dapp.view.viewmodels.MainScreenViewModel

@Composable fun MyNavGraph ()
{
    val navHost = rememberNavController()
    val viewMain = MainScreenViewModel()

    NavHost(
        navController = navHost,
        startDestination = Screens.MainScreen
    ) {

        composable<Screens.MainScreen> {
            MainScreen(navHost = navHost)
        }

        composable<Screens.TableScreen> {
            TableScreen()
        }

        composable<Screens.SetsScreen> {
            SetsScreen()
        }

        composable<Screens.BinadecScreen> {
            BinadecScreen()
        }
    }
}