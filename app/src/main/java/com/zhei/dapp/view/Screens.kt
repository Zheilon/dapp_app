package com.zhei.dapp.view
import kotlinx.serialization.Serializable

@Serializable sealed class Screens {

    @Serializable data object MainScreen : Screens()

    @Serializable data object TableScreen : Screens()

    @Serializable data object SetsScreen : Screens()

    @Serializable data object BinadecScreen : Screens()

}