package com.dauto.data

import com.dauto.data.network.models.*
import com.dauto.data.storage.model.*
import com.dauto.domain.moonentity.*
import com.dauto.domain.weatherentity.*
import java.text.SimpleDateFormat
import java.util.*

//--------------- map calendar models ----------------

fun MoonDayDbModel.toEntity() = MoonDay(
    day = day,
    moonPhase = moonPhase ?: "",
    description = collectColumnToDescription(this)
)

private fun collectColumnToDescription(model: MoonDayDbModel): String {
    val str = StringBuilder()
    for ((_, prop) in model.iterator()) {
        if (!prop.isNullOrEmpty()) {
            str.append("$prop \n\n")
        }
    }
    return str.toString()
}

fun List<MoonDayDbModel>.toEntity() = map { it.toEntity() }


fun MoonMonthDbModel.toEntity() = MoonMonth(
    name = this.name,
    description = this.description ?: "",
    dayGoodBad = this.dayGoodBad?.toEntity() ?: DayGoodBad("", ""),
    daysForSowing = this.daysForSowing?.toEntity() ?: DayForSowing("", "", "", "", "", "", "", ""),
    flowers = this.flowers?.toEntity() ?: Flowers("", "", ""),
    seedlings = this.seedlings?.toEntity() ?: Seedlings(true, "", "", "", "", "", "", "")
)

private fun DayGoodBadDbModel.toEntity() = DayGoodBad(
    favorable = favorable ?: "",
    unfavorable = unfavorable ?: ""
)

private fun DayForSowingDbModel.toEntity() = DayForSowing(
    cucumbers = cucumbers ?: "",
    pepperEggplant = pepperEggplant ?: "",
    cabbage = cabbage ?: "",
    garlic = garlic ?: "",
    rootVegetables = rootVegetables ?: "",
    tomato = tomato ?: "",
    onion = onion ?: "",
    differentGreens = differentGreens ?: "",
)

private fun FlowersDbModel.toEntity() = Flowers(
    annuals = this.annuals ?: "",
    twoyearAndLongterm = this.twoyearAndLongterm ?: "",
    bulbousAndTuberous = this.bulbousAndTuberous ?: ""
)

private fun SeedlingsDbModel.toEntity() = Seedlings(
    isEmpty = (isEmpty == 1),
    fruitTrees = fruitTrees ?: "",
    grape = grape ?: "",
    gooseberry = gooseberry ?: "",
    raspberry = raspberry ?: "",
    strawberry = strawberry ?: "",
    rooting_digging = rooting_digging ?: "",
    grafting = grafting ?: "",
)

//--------------- map weather models ----------------

//map Dto to DBModel
fun WeatherItemDto.toDbModel(): WeatherDay = WeatherDay(
    lastUpdate = current.lastUpdate,
    temperature = current.temperature,
    condition = current.condition.toDbmodel(),
    wind = (current.wind/3.6).toInt(),
    humidity = current.humidity,
    cloud = current.cloud,
    feelsLike = current.feelsLike.toInt()
)

//map Dto to Entity
fun WeatherItemDto.toEntity() = WeatherItem(
    location = location.toEntity(),
    current = current.toEntity(),
    forecast = forecast.toEntity()
)

private fun ForecastDto.toEntity()=Forecast(
    forecastDay = forecastday.toEntityDayWithHours()
)

private fun ConditionsDto.toDbmodel() = ConditionDbModel(
    text = text,
    icon = icon
)

private fun ConditionsDto.toEntity() = Condition(
    text = text,
    icon = icon
)

private fun HoursWeatherDto.toEntity() = Hour(
    time = time,
    temperature = temperature,
    condition = condition.toEntity()
)

private fun DayWeatherDto.toEntity() = Day(
    maxtemp = maxtemp,
    mintemp = mintemp,
    chanceRain = chanceRain,
    condition = condition.toEntity()
)

private fun DayWithHoursDto.toEntity() = DayWithHour(
    date = date,
    day = day.toEntity(),
    astro = astro.toEntity(),
    hour = hour.toEntityHoursWeatherDto()

)

private fun CurrentWeatherDto.toEntity() = CurrentWeather(
    lastUpdate = lastUpdate,
    temperature = temperature,
    condition = condition.toEntity(),
    wind = (wind/3.6).toInt(),
    humidity = humidity,
    cloud = cloud,
    feelsLike = feelsLike.toInt()
)

private fun LocationDto.toEntity() = Location(
    name=name,
    region=region
)

private fun AstroDto.toEntity() = Astro(
    sunrise = sunrise,
    sunset = sunset
)


fun List<HoursWeatherDto>.toEntityHoursWeatherDto() = map { it.toEntity() }
fun List<DayWithHoursDto>.toEntityDayWithHours()=map{it.toEntity()}

private fun replaceDate(string: String): String {
    val tr = string.split(" ")
    return tr[1] + tr[0]
}

//private fun replaceDateFormat(string: String): String{
//    val formatter = SimpleDateFormat("HH^mm dd-M-yyyy", Locale.ROOT)
//
//    val date = formatter.parse(string)
//    return date.toString()
//}