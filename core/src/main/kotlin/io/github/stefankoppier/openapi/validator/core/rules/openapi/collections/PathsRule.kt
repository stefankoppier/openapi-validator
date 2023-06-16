package io.github.stefankoppier.openapi.validator.core.rules.openapi.collections

import io.github.stefankoppier.openapi.validator.core.ValidationResult
import io.github.stefankoppier.openapi.validator.core.rules.RuleGroup
import io.github.stefankoppier.openapi.validator.core.rules.openapi.PathRule
import io.github.stefankoppier.openapi.validator.core.rules.openapi.ResponseRule
import io.github.stefankoppier.openapi.validator.core.rules.primitives.IterableValidationRule
import io.swagger.v3.oas.models.PathItem

class PathsRule internal constructor(group: RuleGroup = RuleGroup.unknown()) : IterableValidationRule<Pair<String, PathItem>>(group) {

    fun all(description: String = "", rule: PathRule.() -> PathRule) =
        apply {
            add { paths ->
                paths
                    ?.map { path ->
                        rule(PathRule(RuleGroup.named("path '${path.first}'", description, RuleGroup.Category.OBJECT, group)))
                            .validate(path.second)
                    }
                    ?.reduce { left, right -> left.merge(right) }
                    ?: ValidationResult.success()
            }
        }

    fun path(description: String = "", named: String, rule: PathRule.() -> PathRule)  =
        apply {
            add { paths ->
                val path = paths?.find { it.first == named }
                rule(PathRule(RuleGroup.named("path '$named'", description, RuleGroup.Category.OBJECT, group)))
                    .validate(path?.second)
            }
        }
}