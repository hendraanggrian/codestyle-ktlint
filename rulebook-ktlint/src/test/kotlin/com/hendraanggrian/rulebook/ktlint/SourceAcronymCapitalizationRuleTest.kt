package com.hendraanggrian.rulebook.ktlint

import com.hendraanggrian.rulebook.ktlint.SourceAcronymCapitalizationRule.Companion.MSG
import com.hendraanggrian.rulebook.ktlint.internals.Messages
import com.pinterest.ktlint.test.KtLintAssertThat.Companion.assertThatRule
import com.pinterest.ktlint.test.LintViolation
import kotlin.test.Test

class SourceAcronymCapitalizationRuleTest {
    private val assertThatCode = assertThatRule { SourceAcronymCapitalizationRule() }

    @Test
    fun `Class names with lowercase abbreviation`() =
        assertThatCode(
            """
            class MySqlClass
            annotation class MySqlAnnotationClass
            data class MySqlDataClass
            sealed class MySqlSealedClass
            interface MySqlInterface
            object MySqlObject
            """.trimIndent(),
        ).hasNoLintViolations()

    @Test
    fun `Class names with uppercase abbreviation`() =
        assertThatCode(
            """
            class MySQLClass
            annotation class MySQLAnnotationClass
            data class MySQLDataClass
            sealed class MySQLSealedClass
            interface MySQLInterface
            object MySQLObject
            """.trimIndent(),
        ).hasLintViolationsWithoutAutoCorrect(
            LintViolation(1, 7, Messages.get(MSG, "MySqlClass")),
            LintViolation(2, 18, Messages.get(MSG, "MySqlAnnotationClass")),
            LintViolation(3, 12, Messages.get(MSG, "MySqlDataClass")),
            LintViolation(4, 14, Messages.get(MSG, "MySqlSealedClass")),
            LintViolation(5, 11, Messages.get(MSG, "MySqlInterface")),
            LintViolation(6, 8, Messages.get(MSG, "MySqlObject")),
        )

    @Test
    fun `File abbreviation`() =
        assertThatCode("")
            .asFileWithPath("/some/path/RestAPI.kt")
            .hasLintViolationWithoutAutoCorrect(1, 1, Messages.get(MSG, "RestApi"))

    @Test
    fun `Skip a KTS file`() =
        assertThatCode("class RestAPI")
            .asFileWithPath("/some/path/RestAPI.kts")
            .hasNoLintViolations()
}