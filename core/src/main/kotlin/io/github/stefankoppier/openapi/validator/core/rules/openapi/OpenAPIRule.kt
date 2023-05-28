package io.github.stefankoppier.openapi.validator.core.rules.openapi

import io.github.stefankoppier.openapi.validator.core.rules.RuleGroup
import io.github.stefankoppier.openapi.validator.core.rules.RuleGroupCategory
import io.github.stefankoppier.openapi.validator.core.rules.ValidationRule
import io.swagger.v3.oas.models.OpenAPI

class OpenAPIRule(group: RuleGroup) : ValidationRule<OpenAPI>(group) {

    fun info(description: String = "", rule: InfoRule.() -> InfoRule): OpenAPIRule {
        add {
            rule(InfoRule(RuleGroup.named("info", description, RuleGroupCategory.OBJECT, group))).validate(it.info)
        }
        return this
    }

    fun paths(description: String = "", rule: PathsRule.() -> PathsRule): OpenAPIRule {
        add {
            rule(PathsRule(RuleGroup.named("paths", description, RuleGroupCategory.OBJECT, group))).validate(it.paths)
        }
        return this
    }

    fun components(description: String = "", rule: ComponentsRule.() -> ComponentsRule): OpenAPIRule {
        add {
            rule(ComponentsRule(RuleGroup.named("components", description, RuleGroupCategory.OBJECT, group))).validate(it.components)
        }
        return this
    }
}

fun openAPI(description: String = "", rule: OpenAPIRule.() -> OpenAPIRule): OpenAPIRule {
    return rule(OpenAPIRule(RuleGroup.named("document", description, RuleGroupCategory.OBJECT)))
}