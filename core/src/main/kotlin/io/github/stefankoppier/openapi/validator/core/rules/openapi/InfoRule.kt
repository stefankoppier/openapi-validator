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
        apply {
            add {
                rule(StringRule(RuleGroup.named("title", description, RuleGroup.Category.FIELD, group)))
                    .validate(it?.title)
            }
        }

    fun summary(description: String = "", rule: StringRule.() -> StringRule) =
        apply {
            add {
                rule(StringRule(RuleGroup.named("summary", description, RuleGroup.Category.FIELD, group)))
                    .validate(it?.summary)
            }
        }

    fun description(description: String = "", rule: StringRule.() -> StringRule) =
        apply {
            add {
                rule(StringRule(RuleGroup.named("description", description, RuleGroup.Category.FIELD, group)))
                    .validate(it?.description)
            }
        }

    fun termsOfService(description: String = "", rule: StringRule.() -> StringRule) =
        apply {
            add {
                rule(StringRule(RuleGroup.named("Terms of Service", description, RuleGroup.Category.FIELD, group)))
                    .validate(it?.termsOfService)
            }
        }

    fun contact(description: String = "", rule: ContactRule.() -> ContactRule) =
        apply {
            add {
                rule(ContactRule(RuleGroup.named("contact", description, RuleGroup.Category.OBJECT, group)))
                    .validate(it?.contact)
            }
        }

    fun licence(description: String = "", rule: LicenceRule.() -> LicenceRule) =
        apply {
            add {
                rule(LicenceRule(RuleGroup.named("licence", description, RuleGroup.Category.OBJECT, group)))
                    .validate(it?.license)
            }
        }

    fun version(description: String = "", rule: StringRule.() -> StringRule) =
        apply {
            add {
                rule(StringRule(RuleGroup.named("version", description, RuleGroup.Category.FIELD, group)))
                    .validate(it?.version)
            }
        }
}
