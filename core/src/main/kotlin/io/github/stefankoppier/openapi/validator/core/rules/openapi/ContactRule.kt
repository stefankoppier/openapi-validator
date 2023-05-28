package io.github.stefankoppier.openapi.validator.core.rules.openapi

import io.github.stefankoppier.openapi.validator.core.rules.*
import io.github.stefankoppier.openapi.validator.core.rules.primitives.StringRule
import io.github.stefankoppier.openapi.validator.core.rules.primitives.URLRule
import io.swagger.v3.oas.models.info.Contact

class ContactRule(group: RuleGroup) : ValidationRule<Contact?>(group) {

    init {
        required()
    }

    fun name(description: String = "", rule: StringRule.() -> StringRule): ContactRule {
        add {
            rule(StringRule(RuleGroup.named("name", description,  RuleGroupCategory.FIELD, group))).validate(it?.name)
        }
        return this
    }

    fun url(description: String = "", rule: URLRule.() -> URLRule): ContactRule {
        add {
            rule(URLRule(RuleGroup.named("url", description, RuleGroupCategory.FIELD, group))).validate(it?.url)
        }
        return this
    }

    // TODO: stricter email validation
    fun email(description: String = "", rule: StringRule.() -> StringRule): ContactRule {
        add {
            rule(StringRule(RuleGroup.named("email", description, RuleGroupCategory.FIELD, group))).validate(it?.email)
        }
        return this
    }
}
