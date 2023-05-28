package io.github.stefankoppier.openapi.validator.rules.openapi

import io.github.stefankoppier.openapi.validator.rules.RuleGroup
import io.github.stefankoppier.openapi.validator.rules.RuleGroupCategory
import io.github.stefankoppier.openapi.validator.rules.ValidationRule
import io.github.stefankoppier.openapi.validator.rules.primitives.StringRule
import io.swagger.v3.oas.models.info.Info

class InfoRule(group: RuleGroup) : ValidationRule<Info>(group) {

    init {
        title { required() }
        version { required() }
    }

    fun title(rule: StringRule.() -> StringRule): InfoRule {
        add {
            rule(StringRule(RuleGroup.named("title", RuleGroupCategory.FIELD, group))).validate(it.title)
        }
        return this
    }

    fun summary(rule: StringRule.() -> StringRule): InfoRule {
        add {
            rule(StringRule(RuleGroup.named("summary", RuleGroupCategory.FIELD, group))).validate(it.summary)
        }
        return this
    }

    fun description(rule: StringRule.() -> StringRule): InfoRule {
        add {
            rule(StringRule(RuleGroup.named("description", RuleGroupCategory.FIELD, group))).validate(it.description)
        }
        return this
    }

    fun termsOfService(rule: StringRule.() -> StringRule): InfoRule {
        add {
            rule(StringRule(RuleGroup.named("Terms of Service", RuleGroupCategory.FIELD, group))).validate(it.termsOfService)
        }
        return this
    }

    fun contact(rule: ContactRule.() -> ContactRule): InfoRule {
        add {
            rule(ContactRule(RuleGroup.named("contact", RuleGroupCategory.OBJECT, group))).validate(it.contact)
        }
        return this
    }

    fun licence(rule: LicenceRule.() -> LicenceRule): InfoRule {
        add {
            rule(LicenceRule(RuleGroup.named("licence", RuleGroupCategory.OBJECT, group))).validate(it.license)
        }
        return this
    }

    fun version(rule: StringRule.() -> StringRule): InfoRule {
        add {
            rule(StringRule(RuleGroup.named("version", RuleGroupCategory.FIELD, group))).validate(it.version)
        }
        return this
    }
}

fun InfoRule.info(rule: InfoRule.() -> InfoRule): InfoRule {
    return rule(InfoRule(RuleGroup.named("info", RuleGroupCategory.OBJECT, group)))
}