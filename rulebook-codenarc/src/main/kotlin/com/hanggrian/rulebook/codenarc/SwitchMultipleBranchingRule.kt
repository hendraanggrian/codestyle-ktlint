package com.hanggrian.rulebook.codenarc

import com.hanggrian.rulebook.codenarc.internals.Messages
import org.codehaus.groovy.ast.stmt.SwitchStatement
import org.codenarc.rule.AbstractAstVisitor

/**
 * [See wiki](https://github.com/hanggrian/rulebook/wiki/Rules/#switch-multiple-branching)
 */
public class SwitchMultipleBranchingRule : Rule() {
    override fun getName(): String = "SwitchMultipleBranching"

    override fun getAstVisitorClass(): Class<*> = Visitor::class.java

    internal companion object {
        const val MSG = "switch.multiple.branching"
    }

    public class Visitor : AbstractAstVisitor() {
        override fun visitSwitch(none: SwitchStatement) {
            // checks for violation
            none.caseStatements.takeIf { it.size < 2 } ?: return
            addViolation(none, Messages[MSG])

            super.visitSwitch(none)
        }
    }
}
