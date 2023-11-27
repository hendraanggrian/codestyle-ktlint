package com.hendraanggrian.rulebook.ktlint

import com.hendraanggrian.rulebook.ktlint.ReduceFileLengthRule.Companion.FILE_LENGTH_PROPERTY
import com.hendraanggrian.rulebook.ktlint.ReduceFileLengthRule.Companion.MSG
import com.hendraanggrian.rulebook.ktlint.internals.Messages
import com.pinterest.ktlint.test.KtLintAssertThat.Companion.assertThatRule
import kotlin.test.Test

class ReduceFileLengthRuleTest {
    private val assertThatCode =
        assertThatRule(
            { ReduceFileLengthRule() },
            editorConfigProperties = setOf(FILE_LENGTH_PROPERTY to 3),
        )

    @Test
    fun `Small file`() =
        assertThatCode(
            """
            package my.namespace

            import namespace.one.*
            """.trimIndent(),
        ).hasNoLintViolations()

    @Test
    fun `Large file`() =
        assertThatCode(
            """
            package my.namespace

            import namespace.one.*
            import namespace.two.*
            """.trimIndent(),
        ).hasLintViolationWithoutAutoCorrect(1, 1, Messages.get(MSG, 3))
}
