package io.github.stefankoppier.openapi.validator.core.rules.openapi

import io.github.stefankoppier.openapi.validator.core.rules.RuleGroup
import io.github.stefankoppier.openapi.validator.core.rules.ValidationRule
import io.github.stefankoppier.openapi.validator.core.rules.primitives.IterableStringRule
import io.github.stefankoppier.openapi.validator.core.rules.primitives.StringRule
import io.swagger.v3.oas.models.servers.ServerVariable

class ServerVariableRule internal constructor(group: RuleGroup = RuleGroup.unknown()) : ValidationRule<ServerVariable>(group) {

    init {
        default { required() }
        given({ it != null && it.enum != null && it.enum.isNotEmpty() }) {
            holds({ "Should contain '${it?.default}' but is '${it?.enum}'" }) { it?.enum?.contains(it.default) ?: true }
        }
    }

    fun enum(description: String = "", rule: IterableStringRule.() -> IterableStringRule) =
        apply {
            add {
                rule(IterableStringRule(RuleGroup.named("enum", RuleGroup.Category.FIELD, description, group)))
                    .validate(it?.enum)
            }
        }

    fun default(description: String = "", rule: StringRule.() -> StringRule) =
        apply {
            add {
                rule(StringRule(RuleGroup.named("default", RuleGroup.Category.FIELD, description, group)))
                    .validate(it?.default)
            }
        }

    fun description(description: String = "", rule: StringRule.() -> StringRule) =
        apply {
            add {
                rule(StringRule(RuleGroup.named("description", RuleGroup.Category.FIELD, description, group)))
                    .validate(it?.description)
            }
        }
}
