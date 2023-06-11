package io.github.stefankoppier.openapi.validator.core.rules.openapi

import io.github.stefankoppier.openapi.validator.core.rules.RuleGroup
import io.github.stefankoppier.openapi.validator.core.rules.RuleGroupCategory
import io.github.stefankoppier.openapi.validator.core.rules.ValidationRule
import io.github.stefankoppier.openapi.validator.core.rules.primitives.StringRule
import io.swagger.v3.oas.models.info.Info

class InfoRule(group: RuleGroup) : ValidationRule<Info>(group) {

    init {
        title { required() }
        version { required() }
    }

    fun title(description: String = "", rule: StringRule.() -> StringRule) =
        apply {
            add {
                rule(StringRule(RuleGroup.named("title", description, RuleGroupCategory.FIELD, group)))
                    .validate(it?.title)
            }
        }

    fun summary(description: String = "", rule: StringRule.() -> StringRule) =
        apply {
            add {
                rule(StringRule(RuleGroup.named("summary", description, RuleGroupCategory.FIELD, group)))
                    .validate(it?.summary)
            }
        }

    fun description(description: String = "", rule: StringRule.() -> StringRule) =
        apply {
            add {
                rule(StringRule(RuleGroup.named("description", description, RuleGroupCategory.FIELD, group)))
                    .validate(it?.description)
            }
        }

    fun termsOfService(description: String = "", rule: StringRule.() -> StringRule) =
        apply {
            add {
                rule(StringRule(RuleGroup.named("Terms of Service", description, RuleGroupCategory.FIELD, group)))
                    .validate(it?.termsOfService)
            }
        }

    fun contact(description: String = "", rule: ContactRule.() -> ContactRule) =
        apply {
            add {
                rule(ContactRule(RuleGroup.named("contact", description, RuleGroupCategory.OBJECT, group)))
                    .validate(it?.contact)
            }
        }

    fun licence(description: String = "", rule: LicenceRule.() -> LicenceRule) =
        apply {
            add {
                rule(LicenceRule(RuleGroup.named("licence", description, RuleGroupCategory.OBJECT, group)))
                    .validate(it?.license)
            }
        }

    fun version(description: String = "", rule: StringRule.() -> StringRule) =
        apply {
            add {
                rule(StringRule(RuleGroup.named("version", description, RuleGroupCategory.FIELD, group)))
                    .validate(it?.version)
            }
        }
}