package com.hanggrian.rulebook.ktlint

import com.hanggrian.rulebook.ktlint.internals.Emit
import com.hanggrian.rulebook.ktlint.internals.Messages
import com.pinterest.ktlint.rule.engine.core.api.ElementType.BLOCK
import com.pinterest.ktlint.rule.engine.core.api.ElementType.LBRACE
import com.pinterest.ktlint.rule.engine.core.api.ElementType.RBRACE
import com.pinterest.ktlint.rule.engine.core.api.ElementType.WHITE_SPACE
import com.pinterest.ktlint.rule.engine.core.api.RuleAutocorrectApproveHandler
import com.pinterest.ktlint.rule.engine.core.api.children
import org.jetbrains.kotlin.com.intellij.lang.ASTNode

/**
 * [See wiki](https://github.com/hanggrian/rulebook/wiki/Rules/#empty-code-block-conciseness)
 */
public class EmptyCodeBlockConcisenessRule :
    Rule("empty-code-block-conciseness"),
    RuleAutocorrectApproveHandler {
    override fun beforeVisitChildNodes(node: ASTNode, emit: Emit) {
        // first line of filter
        if (node.elementType != BLOCK) {
            return
        }

        // checks for violation
        val children = node.children().toList()
        children
            .takeIf {
                it.firstOrNull()?.elementType == LBRACE &&
                    it.lastOrNull()?.elementType == RBRACE
            }?.slice(1 until children.lastIndex)
            ?.takeIf { n -> n.isNotEmpty() && n.all { it.elementType == WHITE_SPACE } }
            ?: return
        emit(node.startOffset, Messages[MSG], false)
    }

    internal companion object {
        const val MSG = "empty.code.block.conciseness"
    }
}