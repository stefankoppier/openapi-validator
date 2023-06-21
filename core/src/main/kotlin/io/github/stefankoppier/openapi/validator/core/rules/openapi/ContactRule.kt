package io.github.stefankoppier.openapi.validator.core.rules.openapi

import io.github.stefankoppier.openapi.validator.core.rules.RuleGroup
import io.github.stefankoppier.openapi.validator.core.rules.ValidationRule
import io.github.stefankoppier.openapi.validator.core.rules.primitives.StringRule
import io.github.stefankoppier.openapi.validator.core.rules.primitives.URLRule
import io.swagger.v3.oas.models.info.Contact

class ContactRule internal constructor(group: RuleGroup = RuleGroup.unknown()) : ValidationRule<Contact>(group) {

    init {
        required()
    }

    fun name(description: String = "", rule: StringRule.() -> StringRule) =
        apply {
            add {
                rule(StringRule(RuleGroup.named("name", description, RuleGroup.Category.FIELD, group)))
                    .validate(it?.name)
            }
        }

    fun url(description: String = "", rule: URLRule.() -> URLRule) =
        apply {
            add {
                rule(URLRule(RuleGroup.named("url", description, RuleGroup.Category.FIELD, group)))
                    .validate(it?.url)
            }
        }

    // TODO: stricter email validation
    fun email(description: String = "", rule: StringRule.() -> StringRule) =
        apply {
            add {
                rule(StringRule(RuleGroup.named("email", description, RuleGroup.Category.FIELD, group)))
                    .validate(it?.email)
            }
        }
}
