package octoping.ticketing.utils

import jakarta.servlet.http.Cookie
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletRequestWrapper

class MutableCookieRequestWrapper(
    request: HttpServletRequest,
) : HttpServletRequestWrapper(request) {
    private val cookies: MutableList<Cookie> = request.cookies.toMutableList()

    override fun getCookies(): Array<Cookie> = cookies.toTypedArray()

    fun replaceCookie(
        cookie: Cookie,
        replaceTarget: String,
    ) {
        cookies.removeIf { it.name == replaceTarget }
        cookies.add(cookie)
    }
}
