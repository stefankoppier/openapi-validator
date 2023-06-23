package io.github.stefankoppier.openapi.validator.core.rules.openapi

import io.github.stefankoppier.openapi.validator.core.rules.RuleGroup
import io.github.stefankoppier.openapi.validator.core.rules.ValidationRule
import io.github.stefankoppier.openapi.validator.core.rules.primitives.StringRule
import io.github.stefankoppier.openapi.validator.core.rules.primitives.URLRule
import io.swagger.v3.oas.models.info.License

class LicenceRule internal constructor(group: RuleGroup = RuleGroup.unknown()) : ValidationRule<License>(group) {

    init {
        name { required() }
    }

    fun name(description: String = "", rule: StringRule.() -> StringRule) =
        apply {
            add {
                rule(StringRule(RuleGroup.named("name", RuleGroup.Category.FIELD, description, group)))
                    .validate(it?.name)
            }
        }

    fun identifier(description: String = "", rule: StringRule.() -> StringRule) =
        apply {
            add {
                rule(StringRule(RuleGroup.named("identifier", RuleGroup.Category.FIELD, description, group)))
                    .validate(it?.identifier)
            }
        }

    fun url(description: String = "", rule: URLRule.() -> URLRule) =
        apply {
            add {
                rule(URLRule(RuleGroup.named("url", RuleGroup.Category.FIELD, description, group)))
                    .validate(it?.url)
            }
        }
}
