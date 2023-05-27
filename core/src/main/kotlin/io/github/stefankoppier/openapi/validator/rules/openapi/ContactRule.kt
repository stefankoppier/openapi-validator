package io.github.stefankoppier.openapi.validator.rules.openapi

import io.github.stefankoppier.openapi.validator.rules.RuleGroup
import io.github.stefankoppier.openapi.validator.rules.ValidationRule
import io.github.stefankoppier.openapi.validator.rules.primitives.OptionalStringRule
import io.github.stefankoppier.openapi.validator.rules.primitives.URLRule
import io.swagger.v3.oas.models.info.Contact

class ContactRule(group: RuleGroup) : ValidationRule<Contact>(group) {

    fun name(rule: OptionalStringRule.() -> OptionalStringRule): ContactRule {
        add {
            rule(OptionalStringRule(RuleGroup.named("name", group))).validate(it.name)
        }
        return this
    }

//    fun url(rule: URLRule.() -> URLRule): ContactRule {
//        add {
//            rule(URLRule(RuleGroup.named("url", group))).validate(it.url)
//        }
//        return this
//    }

    // TODO: stricter email validation
    fun email(rule: OptionalStringRule.() -> OptionalStringRule): ContactRule {
        add {
            rule(OptionalStringRule(RuleGroup.named("email", group))).validate(it.email)
        }
        return this
    }
}

//fun
