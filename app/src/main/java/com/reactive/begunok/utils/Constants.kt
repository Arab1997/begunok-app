package com.reactive.begunok.utils

import android.widget.TextView
import com.reactive.begunok.BuildConfig
import com.reactive.begunok.R
import com.reactive.begunok.ui.adapters.CancelData
import com.reactive.begunok.utils.extensions.invisible
import com.reactive.begunok.utils.extensions.setTextColorRes

object Constants {

    const val BASE_URL = BuildConfig.BASE_URL
    const val TIMEOUT = 10.toLong()

    const val NEW = "NEW"
    const val ACCEPTED = "ACCEPTED"
    const val EXECUTOR_ASSIGNED = "EXECUTOR_ASSIGNED"
    const val IN_PROGRESS = "IN_PROGRESS"
    const val DONE = "DONE"
    const val CANCELLED = "CANCELLED"

    const val WAITING = "WAITING"

    const val ALL_EXCEPT_DONE = "ALL_EXCEPT_DONE"

    val orderStatuses = arrayListOf(
        KeyValueColor(NEW, "ожидает специалиста", R.color.black),
        KeyValueColor(ACCEPTED, "ожидает специалиста", R.color.red),
        KeyValueColor(EXECUTOR_ASSIGNED, "специалиста найден", R.color.black),
        KeyValueColor(IN_PROGRESS, "в работе", R.color.green),
        KeyValueColor(DONE, "", R.color.yellow),
        KeyValueColor(CANCELLED, "отменено", R.color.red)
    )


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

    val cancelOrderAsClient = arrayListOf(
        CancelData("Заявка уже не актуальна"),
        CancelData("Исполнитель попросил отменить заявку, чтобы не платить комиссию команде Begunok"),
        CancelData("Другая причина")
    )

    val cancelOrderAsExecutor = arrayListOf(
        CancelData("Заявка уже не актуальна"),
        CancelData("Не могу связаться с заказчиком"),
        CancelData("Заявка выполнена другим исполнителем"),
        CancelData("Не могу выполнить это задание")
    )

}

fun TextView.setOrderStatus(status: String) {
    val stats = Constants.orderStatuses.filter { it.key == status }
    if (stats.isNotEmpty()) {
        this.text = stats.first().value
        this.setTextColorRes(stats.first().color)
    } else this.invisible()

}

data class KeyValue(val key: String, var value: String)
data class KeyValueColor(val key: String, var value: String, var color: Int)
