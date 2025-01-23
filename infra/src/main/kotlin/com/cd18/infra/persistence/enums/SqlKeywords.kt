package com.cd18.infra.persistence.enums

import java.util.Locale

enum class SqlKeywords {
    SELECT,
    FROM,
    WHERE,
    INSERT,
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
        fun getDdlKeywords(): Set<SqlKeywords> {
            return setOf(
                CREATE,
                ALTER,
                DROP,
                COMMENT,
                TRUNCATE,
                RENAME,
            )
        }

        fun getDmlKeywords(): Set<SqlKeywords> =
            setOf(
                INSERT,
                UPDATE,
                DELETE,
            )

        fun getDqlKeywords(): Set<SqlKeywords> =
            setOf(
                SELECT,
            )

        fun getTclKeywords(): Set<SqlKeywords> =
            setOf(
                COMMIT,
            )
    }

    fun upper(locale: Locale = Locale.ROOT): String = this.name.uppercase(locale)

    fun lower(locale: Locale = Locale.ROOT): String = this.name.lowercase(locale)
}
