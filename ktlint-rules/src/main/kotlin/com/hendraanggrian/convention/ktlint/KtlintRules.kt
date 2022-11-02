package com.hendraanggrian.convention.ktlint

import com.pinterest.ktlint.core.RuleProvider
import com.pinterest.ktlint.core.RuleSetProviderV2

class KtlintRules : RuleSetProviderV2(
    "ktlint-rules",
    About(
        "Hendra Anggrian",
        "Personal code conventions",
        "The Apache License, Version 2.0",
        "https://github.com/hendraanggrian/ktlint-rules/",
        "https://github.com/hendraanggrian/ktlint-rules/issues/"
    )
) {
    override fun getRuleProviders(): Set<RuleProvider> = setOf(
        RuleProvider { DeclarationReturnTypeRule() }
    )
}
