package com.example.project2.dataModel

data class WeatherDataModel(
    val location: LocaleModel,
    val current: CurrentModel,
    val forecast: ForecastModel
)

data class LocaleModel(
    val name: String,
    val localtime: String
)

data class CurrentModel(
    val last_updated: String,
    val temp_c: Float,
    val condition: ConditionalModel
)

data class ConditionalModel(
    val text: String,
    val icon: String
)