package io.github.stefankoppier.openapi.validator.core.rules.openapi

import io.github.stefankoppier.openapi.validator.core.rules.RuleGroup
import io.github.stefankoppier.openapi.validator.core.rules.RuleGroupCategory
import io.github.stefankoppier.openapi.validator.core.rules.ValidationRule
import io.github.stefankoppier.openapi.validator.core.rules.openapi.collections.ParametersRule
import io.github.stefankoppier.openapi.validator.core.rules.openapi.collections.PathsRule
import io.github.stefankoppier.openapi.validator.core.rules.openapi.collections.ServersRule
import io.github.stefankoppier.openapi.validator.core.rules.primitives.StringRule
import io.swagger.v3.oas.models.PathItem

class PathRule(group: RuleGroup = RuleGroup.unknown()) : ValidationRule<PathItem>(group) {

    fun summary(description: String = "", rule: StringRule.() -> StringRule) = 
        apply {
            add {
                rule(StringRule(RuleGroup.named("summary", description, RuleGroupCategory.FIELD, group))).validate(it?.summary)
            }
        }

    fun description(description: String = "", rule: StringRule.() -> StringRule) = 
        apply {
            add {
                rule(StringRule(RuleGroup.named("description", description, RuleGroupCategory.FIELD, group)))
                    .validate(it?.description)
            }
        }

    fun get(description: String = "", rule: OperationRule.() -> OperationRule) = 
        apply {
            add {
                rule(OperationRule(RuleGroup.named("get", description, RuleGroupCategory.OBJECT, group)))
                    .validate(it?.get)
            }
        }

    fun put(description: String = "", rule: OperationRule.() -> OperationRule) = 
        apply {
            add {
                rule(OperationRule(RuleGroup.named("put", description, RuleGroupCategory.OBJECT, group)))
                    .validate(it?.put)
            }
        }

    fun post(description: String = "", rule: OperationRule.() -> OperationRule) = 
        apply {
            add {
                rule(OperationRule(RuleGroup.named("post", description, RuleGroupCategory.OBJECT, group)))
                    .validate(it?.post)
            }
        }

    fun delete(description: String = "", rule: OperationRule.() -> OperationRule) = 
        apply {
            add {
                rule(OperationRule(RuleGroup.named("delete", description, RuleGroupCategory.OBJECT, group)))
                    .validate(it?.delete)
            }
        }

    fun options(description: String = "", rule: OperationRule.() -> OperationRule) = 
        apply {
            add {
                rule(OperationRule(RuleGroup.named("options", description, RuleGroupCategory.OBJECT, group)))
                    .validate(it?.options)
            }
        }

    fun head(description: String = "", rule: OperationRule.() -> OperationRule) = 
        apply {
            add {
                rule(OperationRule(RuleGroup.named("head", description, RuleGroupCategory.OBJECT, group)))
                    .validate(it?.head)
            }
        }

    fun patch(description: String = "", rule: OperationRule.() -> OperationRule) = 
        apply {
            add {
                rule(OperationRule(RuleGroup.named("patch", description, RuleGroupCategory.OBJECT, group)))
                    .validate(it?.patch)
            }
        }

    fun trace(description: String = "", rule: OperationRule.() -> OperationRule) = 
        apply {
            add {
                rule(OperationRule(RuleGroup.named("trace", description, RuleGroupCategory.OBJECT, group)))
                    .validate(it?.trace)
            }
        }

    fun servers(description: String = "", rule: ServersRule.() -> ServersRule) = 
        apply {
            add {
                rule(ServersRule(RuleGroup.named("servers", description, RuleGroupCategory.OBJECT, group)))
                    .validate(it?.servers)
            }
        }

    fun parameters(description: String = "", rule: ParametersRule.() -> ParametersRule) = 
        apply {
            add {
                rule(ParametersRule(RuleGroup.named("parameters", description, RuleGroupCategory.OBJECT, group)))
                    .validate(it?.parameters)
            }
        }
}