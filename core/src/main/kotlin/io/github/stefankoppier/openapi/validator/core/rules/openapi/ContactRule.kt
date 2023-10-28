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
        add {
            rule(StringRule(RuleGroup.named("name", RuleGroup.Category.FIELD, description, group)))
                .validate(it?.name)
        }

    fun url(description: String = "", rule: URLRule.() -> URLRule) =
        add {
            rule(URLRule(RuleGroup.named("url", RuleGroup.Category.FIELD, description, group)))
                .validate(it?.url)
        }

    fun email(description: String = "", rule: StringRule.() -> StringRule) =
        add {
            rule(StringRule(RuleGroup.named("email", RuleGroup.Category.FIELD, description, group)))
                .validate(it?.email)
        }
}
