package com.cd18.common.util

enum class Color(
    private val _hex: String,
) {
    RED("#FF0000"),
    GREEN("#00FF00"),
    SEA_GREEN("#2E8B57"),
    BLUE("#0000FF"),
    DOGER_BLUE("#1E90FF"),
    ;

    val HEX: String
        get() = _hex
    val ANSI: String
        get() = hexToAnsi(_hex)

    private fun hexToAnsi(hex: String): String {
        val red = hex.substring(1, 3).toInt(16)
        val green = hex.substring(3, 5).toInt(16)
        val blue = hex.substring(5, 7).toInt(16)

        return "\u001B[38;5;${getClosest256Color(red, green, blue)}m"
    }

    private fun getClosest256Color(
        red: Int,
        green: Int,
        blue: Int,
    ): Int {
        val r = (red / 51) * 36
        val g = (green / 51) * 6
        val b = blue / 51
        return r + g + b + 16
    }

    companion object {
        const val RESET_ANSI_COLOR = "\u001B[0m"
    }
}
