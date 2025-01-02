package octoping.ticketing.utils

import org.springframework.util.LinkedMultiValueMap

object LinkedMultiValueMapMaker {
    fun make(vararg pairs: Pair<String, String>): LinkedMultiValueMap<String, String> {
        val map = LinkedMultiValueMap<String, String>()

        pairs.forEach { map.add(it.first, it.second) }

        return map
    }
}
