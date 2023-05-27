package io.github.stefankoppier.openapi.validator.rules.openapi

import io.github.stefankoppier.openapi.validator.rules.RuleGroup
import io.github.stefankoppier.openapi.validator.rules.ValidationFailure
import io.github.stefankoppier.openapi.validator.rules.ValidationResult
import io.github.stefankoppier.openapi.validator.rules.ValidationRule
import io.github.stefankoppier.openapi.validator.rules.primitives.OptionalStringRule
import io.swagger.v3.oas.models.info.Info

class InfoRule(group: RuleGroup) : ValidationRule<Info>(group) {

    init {
        title { required() }
        version { required() }
    }

    fun title(rule: OptionalStringRule.() -> OptionalStringRule): InfoRule {
        add {
            rule(OptionalStringRule(RuleGroup.named("title", group))).validate(it.title)
        }
        return this
    }

    fun summary(rule: OptionalStringRule.() -> OptionalStringRule): InfoRule {
        add {
            rule(OptionalStringRule(RuleGroup.named("summary", group))).validate(it.summary)
        }
        return this
    }

    fun description(rule: OptionalStringRule.() -> OptionalStringRule): InfoRule {
        add {
            rule(OptionalStringRule(RuleGroup.named("description", group))).validate(it.description)
        }
        return this
    }

    fun termsOfService(rule: OptionalStringRule.() -> OptionalStringRule): InfoRule {
        add {
            rule(OptionalStringRule(RuleGroup.named("Terms of Service", group))).validate(it.termsOfService)
        }
        return this
    }

    fun contact(rule: ContactRule.() -> ContactRule): InfoRule {
        add {
            rule(ContactRule(RuleGroup.named("contact", group))).validate(it.contact)
        }
        return this
    }


    fun licence(rule: LicenceRule.() -> LicenceRule): InfoRule {
        add {
            rule(LicenceRule(RuleGroup.named("licence", group))).validate(it.licence)
        }
        return this
    }

    fun version(rule: OptionalStringRule.() -> OptionalStringRule): InfoRule {
        add {
            rule(OptionalStringRule(RuleGroup.named("version", group))).validate(it.version)
        }
        return this
    }
}

fun InfoRule.info(rule: InfoRule.() -> InfoRule): InfoRule {
    return rule(InfoRule(RuleGroup.named("info", group)))
}