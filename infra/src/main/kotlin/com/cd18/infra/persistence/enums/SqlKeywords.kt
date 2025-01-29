package com.cd18.infra.persistence.enums

import java.util.Locale

enum class SqlKeywords {
    SELECT,
    FROM,
    WHERE,
    INSERT,
    INTO,
    UPDATE,
    DELETE,
    CREATE,
    ALTER,
    DROP,
    TABLE,
    INDEX,
    VIEW,
    JOIN,
    INNER,
    OUTER,
    LEFT,
    RIGHT,
    ON,
    AND,
    OR,
    NOT,
    ORDER,
    BY,
    GROUP,
    HAVING,
    LIMIT,
    OFFSET,
    VALUES,
    SET,
    COMMENT,
    COMMIT,
    TRUNCATE,
    RENAME,
    ;

    companion object {
        val DDL_KEYWORDS = setOf(CREATE, ALTER, DROP, COMMENT, TRUNCATE, RENAME)
        val DML_KEYWORDS = setOf(INSERT, UPDATE, DELETE)
        val DQL_KEYWORDS = setOf(SELECT)
        val TCL_KEYWORDS = setOf(COMMIT)
    }

    fun upper(locale: Locale = Locale.ROOT): String = this.name.uppercase(locale)

    fun lower(locale: Locale = Locale.ROOT): String = this.name.lowercase(locale)
}
