package octoping.ticketing.utils

object StacktraceParser {
    private const val MEANINGFUL_LINE_THRESHOLD = "Controller"

    fun parseMeaningful(
        throwable: Throwable,
        separator: String = "\n",
    ): String {
        val stackTrace = throwable.stackTraceToString().split("\n")

        return stackTrace
            .take(stackTrace.indexOfLast { it.contains(MEANINGFUL_LINE_THRESHOLD) } + 1)
            .joinToString(separator)
    }
}
