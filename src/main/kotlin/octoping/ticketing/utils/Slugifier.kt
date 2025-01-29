package octoping.ticketing.utils

import java.util.*

fun String.slugify(): String =
    this
        .lowercase(Locale.getDefault())
        .replace("[^a-z0-9가-힣ㄱ-ㅎㅏ-ㅣ]".toRegex(), "-")
