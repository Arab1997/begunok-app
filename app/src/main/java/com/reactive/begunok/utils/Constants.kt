package com.reactive.begunok.utils

import com.reactive.begunok.BuildConfig

object Constants {

    const val BASE_URL = BuildConfig.BASE_URL
    const val TIMEOUT = 10.toLong()

    val cities = arrayListOf(
        "Киев",
        "Харьков",
        "Одесса",
        "Днепр",
        "Запорожье",
        "Львов",
        "Кривой Рог",
        "Николаев",
        "Мариуполь",
        "Луганск",
        "Винница",
        "Херсон",
        "Полтава",
        "Чернигов",
        "Черкассы",
        "Житомир",
        "Сумы",
        "Хмельницкий",
        "Черновцы",
        "Ровно",
        "Каменское",
        "Кропивницкий",
        "Ивано-Франковск",
        "Кременчуг",
        "Тернополь",
        "Луцк",
        "Белая Церковь",
        "Краматорск",
        "Мелитополь",
        "Никополь",
        "Ужгород",
        "Бердянск",
        "Алчевск",
        "Павлоград",
        "Северодонецк",
        "Лисичанск",
        "Каменец-Подольский",
        "Бровары",
        "Конотоп",
        "Умань",
        "Мукачево",
        "Александрия",
        "Шостка",
        "Бердичев",
        "Дрогобыч",
        "Нежин",
        "Измаил",
        "Новомосковск",
        "Ковель",
        "Смела",
        "Червоноград",
        "Калуш",
        "Первомайськ",
        "Коростень",
        "Коломыя",
        "Борисполь",
        "Рубежное",
        "Черноморск",
        "Стрый",
        "Прилуки",
        "Лозовая",
        "Новоград-Волынский",
        "Энергодар",
        "Нововолынск",
        "Горишние Плавни",
        "Изюм",
        "Белгород-Днестровский"
    )
}

data class KeyValue(val key: String, var value: String)
