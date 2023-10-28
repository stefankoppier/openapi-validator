package io.github.stefankoppier.openapi.validator.core.rules.openapi

import io.github.stefankoppier.openapi.validator.core.rules.RuleGroup
import io.github.stefankoppier.openapi.validator.core.rules.primitives.IterableRule
import io.github.stefankoppier.openapi.validator.core.rules.primitives.StringRule

class OperationPathRule internal constructor(group: RuleGroup = RuleGroup.unknown()) : StringRule(group) {

    fun segments(rule: OperationPathSegmentsRule.() -> OperationPathSegmentsRule) =
        add { segments ->
            rule(OperationPathSegmentsRule(group)).validate(segments?.split('/')?.filter { it.isNotEmpty() })
        }

    fun fixed(rule: OperationPathSegmentsRule.() -> OperationPathSegmentsRule) =
        add { segments ->
            rule(OperationPathSegmentsRule(group))
                .validate(segments?.split('/')?.filter { !it.startsWith('{') && !it.endsWith('}') })
        }

    fun templates(rule: OperationPathSegmentsRule.() -> OperationPathSegmentsRule) =
        add { segments ->
            rule(OperationPathSegmentsRule(group))
                .validate(segments?.split('/')?.filter { it.startsWith('{') && it.endsWith('}') })
        }
}

class OperationPathSegmentsRule internal constructor(group: RuleGroup = RuleGroup.unknown()) : IterableRule<String>(group) {

    fun first(description: String = "", rule: StringRule.() -> StringRule) =
        add { segments ->
            rule(StringRule(RuleGroup.named("first segment '${segments?.firstOrNull()}'", RuleGroup.Category.GROUP, description, group)))
                .validate(segments?.firstOrNull())
        }

    fun last(description: String = "", rule: StringRule.() -> StringRule) =
        add { segments ->
            rule(StringRule(RuleGroup.named("last segment '${segments?.firstOrNull()}'", RuleGroup.Category.GROUP, description, group)))
                .validate(segments?.lastOrNull())
        }

    fun all(description: String = "", rule: StringRule.() -> StringRule) =
        addForEach { segment ->
            rule(StringRule(RuleGroup.named("segment '$segment'", RuleGroup.Category.GROUP, description, group)))
                .validate(segment)
        }
}
