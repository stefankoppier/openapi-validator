package io.github.stefankoppier.openapi.validator.rules.openapi

import io.github.stefankoppier.openapi.validator.rules.*
import io.github.stefankoppier.openapi.validator.rules.primitives.StringRule
import io.github.stefankoppier.openapi.validator.rules.primitives.URLRule
import io.swagger.v3.oas.models.info.Contact

class ContactRule(group: RuleGroup) : ValidationRule<Contact?>(group) {

    init {
        required()
    }

    fun required(): ContactRule {
        add {
            val message = "Was required but is '$it'"
            ValidationResult.condition(ValidationFailure(group, message)) {
                it != null
            }
        }
        return this
    }

    fun name(rule: StringRule.() -> StringRule): ContactRule {
        add {
            rule(StringRule(RuleGroup.named("name",  RuleGroupCategory.FIELD, group))).validate(it?.name)
        }
        return this
    }

    fun url(rule: URLRule.() -> URLRule): ContactRule {
        add {
            rule(URLRule(RuleGroup.named("url", RuleGroupCategory.FIELD, group))).validate(it?.url)
        }
        return this
    }

    // TODO: stricter email validation
    fun email(rule: StringRule.() -> StringRule): ContactRule {
        add {
            rule(StringRule(RuleGroup.named("email", RuleGroupCategory.FIELD, group))).validate(it?.email)
        }
        return this
    }
}
