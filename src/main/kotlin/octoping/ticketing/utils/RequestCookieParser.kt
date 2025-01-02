package octoping.ticketing.utils

import jakarta.servlet.http.HttpServletRequest

object RequestCookieParser {
    private const val ACCESS_TOKEN_NAME = "accessToken"
    private const val REFRESH_TOKEN_NAME = "refreshToken"

    fun getAccessCookie(request: HttpServletRequest): String? {
        val cookies = request.cookies ?: return null

        return cookies
            .filter { ACCESS_TOKEN_NAME == it.name }
            .map { it.value }
            .firstOrNull()
    }

    fun getRefreshCookie(request: HttpServletRequest): String? {
        val cookies = request.cookies ?: return null

        return cookies
            .filter { REFRESH_TOKEN_NAME == it.name }
            .map { it.value }
            .firstOrNull()
    }
}
