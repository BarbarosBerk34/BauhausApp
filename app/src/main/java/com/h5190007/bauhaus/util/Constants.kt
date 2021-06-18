package com.h5190007.bauhaus.util

object Constants {
    const val MOVED_CATEGORY_TITLE: String = "MOVED_CATEGORY_TITLE"
    const val MOVED_PRODUCT_TITLE: String = "MOVED_PRODUCT_TITLE"
    const val MOVED_USER_TITLE: String = "MOVED_USER_TITLE"
    const val MOVED_USERNAME: String = "MOVED_USERNAME"


    const val COUNTDOWN_MILISECONDS: Long = 3000
    const val COUNTDOWN_INTERVAL: Long = 1000

    const val SPACE_REGEX: String = "\\s+"

    const val BASE_URL="https://raw.githubusercontent.com/BarbarosBerk34/BauhausWebService/main/"

    const val GRID_COLUMN_SIZE = 2

    const val LOCALE_TURKISH = "TR"
}

enum class ALERT_TYPE() {
    ALERT_NO_INTERNET,
    ALERT_EXIT,
    ALERT_SORT
}