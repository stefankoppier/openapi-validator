package io.github.stefankoppier.openapi.validator.core.rules.openapi

import io.github.stefankoppier.openapi.validator.core.rules.RuleGroup
import io.github.stefankoppier.openapi.validator.core.rules.ValidationRule
import io.github.stefankoppier.openapi.validator.core.rules.primitives.StringRule
import io.swagger.v3.oas.models.info.Info

class InfoRule internal constructor(group: RuleGroup = RuleGroup.unknown()) : ValidationRule<Info>(group) {

    init {
        title { required() }
        version { required() }
    }

    fun title(description: String = "", rule: StringRule.() -> StringRule) =
        add {
            rule(StringRule(RuleGroup.named("title", RuleGroup.Category.FIELD, description, group)))
                .validate(it?.title)
        }

    fun summary(description: String = "", rule: StringRule.() -> StringRule) =
        add {
            rule(StringRule(RuleGroup.named("summary", RuleGroup.Category.FIELD, description, group)))
                .validate(it?.summary)
        }

    fun description(description: String = "", rule: StringRule.() -> StringRule) =
        add {
            rule(StringRule(RuleGroup.named("description", RuleGroup.Category.FIELD, description, group)))
                .validate(it?.description)
        }

    fun termsOfService(description: String = "", rule: StringRule.() -> StringRule) =
        add {
            rule(StringRule(RuleGroup.named("termsOfService", RuleGroup.Category.FIELD, description, group)))
                .validate(it?.termsOfService)
        }

    fun contact(description: String = "", rule: ContactRule.() -> ContactRule) =
        add {
            rule(ContactRule(RuleGroup.named("contact", RuleGroup.Category.GROUP, description, group)))
                .validate(it?.contact)
        }

    fun licence(description: String = "", rule: LicenceRule.() -> LicenceRule) =
        add {
            rule(LicenceRule(RuleGroup.named("licence", RuleGroup.Category.GROUP, description, group)))
                .validate(it?.license)
        }

    fun version(description: String = "", rule: StringRule.() -> StringRule) =
        add {
            rule(StringRule(RuleGroup.named("version", RuleGroup.Category.FIELD, description, group)))
                .validate(it?.version)
        }
}
