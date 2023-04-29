package com.dauto.domain.moonentity

data class MoonMonth(

    val name: String,

    val description: String,

    val dayGoodBad: DayGoodBad,

    val daysForSowing: DayForSowing,

    val flowers: Flowers,

    val seedlings: Seedlings,

)