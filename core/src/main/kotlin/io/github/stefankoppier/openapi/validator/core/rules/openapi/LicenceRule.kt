package io.github.stefankoppier.openapi.validator.core.rules.openapi

import io.github.stefankoppier.openapi.validator.core.rules.RuleGroup
import io.github.stefankoppier.openapi.validator.core.rules.RuleGroupCategory
import io.github.stefankoppier.openapi.validator.core.rules.ValidationRule
import io.github.stefankoppier.openapi.validator.core.rules.primitives.StringRule
import io.github.stefankoppier.openapi.validator.core.rules.primitives.URLRule
import io.swagger.v3.oas.models.info.License

class LicenceRule(group: RuleGroup) : ValidationRule<License>(group) {

    init {
        name { required() }
    }

    fun name(rule: StringRule.() -> StringRule): LicenceRule {
        add {
            rule(StringRule(RuleGroup.named("name", RuleGroupCategory.FIELD, group))).validate(it.name)
        }
        return this
    }

    fun identifier(rule: StringRule.() -> StringRule): LicenceRule {
        add {
            rule(StringRule(RuleGroup.named("identifier", RuleGroupCategory.FIELD, group))).validate(it.identifier)
        }
        return this
    }

    fun url(rule: URLRule.() -> URLRule): LicenceRule {
        add {
            rule(URLRule(RuleGroup.named("url", RuleGroupCategory.FIELD, group))).validate(it.url)
        }
        return this
    }
}
