package com.cd18.infra.persistence.config

import com.cd18.common.util.Color
import com.cd18.infra.persistence.enums.SqlKeywords
import com.p6spy.engine.logging.Category
import com.p6spy.engine.spy.P6SpyOptions
import com.p6spy.engine.spy.appender.MessageFormattingStrategy
import jakarta.annotation.PostConstruct
import org.hibernate.engine.jdbc.internal.FormatStyle
import org.hibernate.engine.jdbc.internal.Formatter
import org.springframework.context.annotation.Configuration
import org.springframework.util.ClassUtils
import java.text.MessageFormat
import java.util.Locale

@Configuration
class P6SpyConfig {
    @PostConstruct
    fun setLogMessageFormat() {
        P6SpyOptions.getActiveInstance().logMessageFormat = P6SpyPrettySqlFormatter::class.java.name
    }
}

internal class P6SpyPrettySqlFormatter : MessageFormattingStrategy {
    companion object {
        private const val REPOSITORY_PACKAGE = "com.cd18.infra.persistence.repository"

        private val NEW_LINE = System.lineSeparator()
        private val SQL_KEYWORDS_REGEX =
            SqlKeywords.entries
                .joinToString("|") { "\\b${it.name}\\b" }
                .toRegex(RegexOption.IGNORE_CASE)
    }

    override fun formatMessage(
        connectionId: Int,
        now: String,
        elapsed: Long,
        category: String,
        prepared: String,
        sql: String?,
        url: String,
    ): String {
        if (sql.isNullOrBlank()) return ""
        return """$NEW_LINE
            |Executing Query[$category] ⇒ ${formatSql(sql, category)}${NEW_LINE}
            |Execution Time: ${elapsed}ms
            |Connection ID: $connectionId
            |Call Stack: ${getCallStack()}
            |${"-----".repeat(10)}
            """.trimMargin()
    }

    private fun getFormatter(
        sql: String,
        category: String,
    ): Formatter {
        return if (isDDLStatement(sql, category)) FormatStyle.DDL.formatter else FormatStyle.BASIC.formatter
    }

    private fun formatSql(
        sql: String,
        category: String,
    ): String {
        return getFormatter(sql, category).format(sql)
            .replace(SQL_KEYWORDS_REGEX) {
                "${Color.SEA_GREEN.ANSI}${it.value.uppercase()}${Color.RESET_ANSI_COLOR}"
            }
            .replace("+0900", "")
    }

    private fun isDDLStatement(
        sql: String,
        category: String,
    ): Boolean {
        return category == Category.STATEMENT.name &&
            sql.trimStart().uppercase(Locale.ROOT).run {
                SqlKeywords.DDL_KEYWORDS.any { startsWith(it.upper()) }
            }
    }

    private fun getCallStack(): StringBuilder {
        return Throwable().stackTrace
            .filter {
                it.className.startsWith(REPOSITORY_PACKAGE) &&
                    !it.toString().contains(ClassUtils.getUserClass(this).getName())
            }
            .reversed()
            .mapIndexed { idx, it ->
                MessageFormat.format("{0}\t\t{1}. {2}", NEW_LINE, idx + 1, it.toString())
            }
            .fold(StringBuilder()) { acc, it -> acc.append(it) }
    }
}
