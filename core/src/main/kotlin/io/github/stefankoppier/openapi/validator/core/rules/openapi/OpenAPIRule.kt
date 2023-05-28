package io.github.stefankoppier.openapi.validator.core.rules.openapi

import io.github.stefankoppier.openapi.validator.core.rules.RuleGroup
import io.github.stefankoppier.openapi.validator.core.rules.RuleGroupCategory
import io.github.stefankoppier.openapi.validator.core.rules.ValidationRule
import io.swagger.v3.oas.models.OpenAPI

class OpenAPIRule(group: RuleGroup) : ValidationRule<OpenAPI>(group) {

    fun info(rule: InfoRule.() -> InfoRule): OpenAPIRule {
        add {
            rule(InfoRule(RuleGroup.named("info", RuleGroupCategory.OBJECT, group))).validate(it.info)
        }
        return this
    }

    fun paths(rule: PathsRule.() -> PathsRule): OpenAPIRule {
        add {
            rule(PathsRule(RuleGroup.named("paths", RuleGroupCategory.OBJECT, group))).validate(it.paths)
        }
        return this
    }

    fun components(rule: ComponentsRule.() -> ComponentsRule): OpenAPIRule {
        add {
            rule(ComponentsRule(RuleGroup.named("components", RuleGroupCategory.OBJECT, group))).validate(it.components)
        }
        return this
    }
}

fun openAPI(rule: OpenAPIRule.() -> OpenAPIRule): OpenAPIRule {
    return rule(OpenAPIRule(RuleGroup.named("document", RuleGroupCategory.OBJECT)))
}