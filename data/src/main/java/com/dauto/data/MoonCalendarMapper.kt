package com.dauto.data

import com.dauto.data.network.models.*
import com.dauto.data.storage.model.moonCalendar.*
import com.dauto.data.storage.model.weatherDay.*
import com.dauto.domain.moonentity.*
import com.dauto.domain.weatherentity.*
import java.text.SimpleDateFormat
import java.util.*

//--------------- map calendar models ----------------

fun MoonDayDbModel.toEntity() = MoonDay(
    day = formattedMoonDate(day),
    moonPhase = moonPhase ?: "",
    moonInfo = moonInfo?.trim() ?: "",
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


fun convertWeatherDtoToDbModel(weatherItemDto: WeatherItemDto): CurrentWeatherDbModel {
    val currentWeather = weatherItemDto.current
    val location = "${weatherItemDto.location.name}, ${weatherItemDto.location.region}"
    return CurrentWeatherDbModel(
        id = 132,
        location = location,
        lastUpdate = replaceDate(currentWeather.lastUpdate),
        temperature = String.format(CELSIUS, currentWeather.temperature.convertToString()),
        condition = currentWeather.condition.toDbmodel(),
        wind = convertKmhToMc(currentWeather.wind),
        humidity = currentWeather.humidity,
        cloud = currentWeather.cloud,
        feelsLike = String.format(CELSIUS, currentWeather.feelsLike.convertToString())
    )

}

private fun Float.toIntToString() = this.toInt().toString()

private fun Float.convertToString(): String {
    val result = this.toInt()
    if (result <= 0) {
        return "$result"
    }
    return "+$result"
}

private fun WeatherDayDbModel.toEntity() = Day(
    date = formattedDate(date),
    avgTemperature = String.format("${minTemperature}..${maxTemperature} ℃"),
    wind = "$wind $MC",
    humidity = "$humidity $PERCENT",
    chanceRain = "$chanceRain $PERCENT",
    chanceSnow = "$chanceSnow $PERCENT",
    condition = condition.toEntity(),
    astro = astro.toEntity()
)

fun HoursDbModel.toEntity() = Hour(
    time = time,
    temperature = String.format(CELSIUS, temperature),
    conditionIcon = conditionIcon
)

fun List<HoursDbModel>.convertDbToEntity() = map { it.toEntity() }

fun mapDayListDbModelToEntity(dayWithHoursDbModel: List<DayWithHoursDbModel>): List<WeatherDayWithHours> {
    val list = mutableListOf<WeatherDayWithHours>()
    for (dayWithH in dayWithHoursDbModel) {
        list.add(
            WeatherDayWithHours(
                dayWithH.weatherDayDbModel.toEntity(),
                dayWithH.hoursList.convertDbToEntity()
            )
        )
    }
    return list.toList()
}

fun CurrentWeatherDbModel.toEntity() = CurrentWeather(
    location = location,
    lastUpdate = lastUpdate,
    temperature = temperature,
    condition = condition.toEntity(),
    wind = "$wind $MC",
    humidity = "$humidity $PERCENT",
    cloud = "$cloud $PERCENT",
    feelsLike = feelsLike
)

fun mapDtoHoursListToDbModelList(weatherItemDto: WeatherItemDto): List<HoursDbModel> {
    val dayWithHoursList = weatherItemDto.forecast.forecastday
    val hoursList = dayWithHoursList.flatMap { it.hour }.toList()
    val housrListDbModel = mutableListOf<HoursDbModel>()
    var countId = 0
    for (hour in hoursList) {
        val itemHour = HoursDbModel(
            id = ++countId,
            dayId = cutDateToDay(hour.time),
            time = cutDateToHour(hour.time),
            temperature = hour.temperature.convertToString(),
            conditionIcon = hour.condition.icon
        )
        housrListDbModel.add(itemHour)
    }
    return housrListDbModel.toList()
}

fun mapDtoDayListToDbModelList(weatherItemDto: WeatherItemDto): List<WeatherDayDbModel> {
    val dayWithHoursList = weatherItemDto.forecast.forecastday
    return dayWithHoursList.map { it.toDbModel() }
}

private fun DayWithHoursDto.toDbModel() = WeatherDayDbModel(
    date = date,
    maxTemperature = day.maxtemp.convertToString(),
    minTemperature = day.mintemp.convertToString(),
    wind = convertKmhToMc(day.wind).toString(),
    humidity = day.humidity.toIntToString(),
    chanceRain = day.chanceRain,
    chanceSnow = day.chanceSnow,
    condition = day.condition.toDbmodel(),
    astro = astro.toDbModel()
)

private fun cutDateToHour(date: String) = date.split(" ")[1]
private fun cutDateToDay(date: String) = date.split(" ")[0]


private fun ConditionsDto.toDbmodel() = ConditionDbModel(
    text = text,
    icon = icon
)

private fun ConditionDbModel.toEntity() = Condition(
    text = text,
    icon = icon
)


private fun AstroDto.toDbModel() = AstroDbModel(
    sunrise = sunrise,
    sunset = sunset
)

private fun AstroDbModel.toEntity() = Astro(
    sunrise = formattedHour(sunrise),
    sunset = formattedHour(sunset)
)

private fun convertKmhToMc(wind: Float): Int {
    return (wind / 3.6).toInt()
}


private fun replaceDate(string: String): String = try {
    val df = SimpleDateFormat("yyyy-MM-dd HH:mm", Locale.ENGLISH)
    val date = df.parse(string)
    val forDay = SimpleDateFormat(/* pattern = */ "HH:mm dd MMM, EE", Locale.getDefault())
    date?.let { forDay.format(it) }.toString()
} catch (e: Exception){
    string
}

private fun formattedDate(dataDate: String): String =try {
    val df = SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH)
    val date = df.parse(dataDate)
    val forDay = SimpleDateFormat(/* pattern = */ "EE, dd MMM", Locale.getDefault())
    date?.let { forDay.format(it) }.toString()
} catch (e: Exception){
    dataDate
}

private fun formattedMoonDate(dataDate: String): String = try {
    val df = SimpleDateFormat("dd-MM-yy", Locale.ENGLISH)
    val date = df.parse(dataDate)
    val forDay = SimpleDateFormat(/* pattern = */ "EE, dd MMM", Locale.getDefault())
    date?.let { forDay.format(it) }.toString()
    } catch (e: Exception) {
    dataDate
    }

private fun formattedHour(hour: String): String = try {
    val hf = SimpleDateFormat("hh:mm aa", Locale.ENGLISH)
    val hours = hf.parse(hour)
    val forHour = SimpleDateFormat(/* pattern = */ "HH:mm", Locale.getDefault())
    hours?.let { forHour.format(it) }.toString()
} catch (e: Exception){
    hour
}