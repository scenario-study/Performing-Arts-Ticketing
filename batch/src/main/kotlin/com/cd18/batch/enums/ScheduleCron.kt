package com.cd18.batch.enums

object ScheduleCron {
    const val EVERY_SECOND = "0/1 * * * * *"
    const val EVERY_10_SECONDS = "0/10 * * * * *"
    const val EVERY_MINUTE = "0 * * * * ?"
    const val EVERY_HOUR = "0 0 * * * *"
    const val EVERY_DAY = "0 0 0 * * *"
}
