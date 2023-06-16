package io.github.stefankoppier.openapi.validator.core.rules.openapi

import io.github.stefankoppier.openapi.validator.core.rules.RuleGroup
import io.github.stefankoppier.openapi.validator.core.rules.ValidationRule
import io.github.stefankoppier.openapi.validator.core.rules.openapi.collections.ParametersRule
import io.github.stefankoppier.openapi.validator.core.rules.openapi.collections.ServersRule
import io.github.stefankoppier.openapi.validator.core.rules.primitives.StringRule
import io.swagger.v3.oas.models.PathItem

class PathRule internal constructor(group: RuleGroup = RuleGroup.unknown()) : ValidationRule<PathItem>(group) {

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

    fun operations(description: String = "", rule: OperationRule.() -> OperationRule) =
        apply {
            optional({ it?.get }) { get(description, rule) }
            optional({ it?.delete }) { delete(description, rule) }
            optional({ it?.options }) { options(description, rule) }
            optional({ it?.head }) { head(description, rule) }
            optional({ it?.patch }) { patch(description, rule) }
            optional({ it?.trace }) { trace(description, rule) }
        }

    fun get(description: String = "", rule: OperationRule.() -> OperationRule) = 
        apply {
            add {
                rule(OperationRule(RuleGroup.named("get", description, RuleGroup.Category.OBJECT, group)))
                    .validate(it?.get)
            }
        }

    fun put(description: String = "", rule: OperationRule.() -> OperationRule) = 
        apply {
            add {
                rule(OperationRule(RuleGroup.named("put", description, RuleGroup.Category.OBJECT, group)))
                    .validate(it?.put)
            }
        }

    fun post(description: String = "", rule: OperationRule.() -> OperationRule) = 
        apply {
            add {
                rule(OperationRule(RuleGroup.named("post", description, RuleGroup.Category.OBJECT, group)))
                    .validate(it?.post)
            }
        }

    fun delete(description: String = "", rule: OperationRule.() -> OperationRule) = 
        apply {
            add {
                rule(OperationRule(RuleGroup.named("delete", description, RuleGroup.Category.OBJECT, group)))
                    .validate(it?.delete)
            }
        }

    fun options(description: String = "", rule: OperationRule.() -> OperationRule) = 
        apply {
            add {
                rule(OperationRule(RuleGroup.named("options", description, RuleGroup.Category.OBJECT, group)))
                    .validate(it?.options)
            }
        }

    fun head(description: String = "", rule: OperationRule.() -> OperationRule) = 
        apply {
            add {
                rule(OperationRule(RuleGroup.named("head", description, RuleGroup.Category.OBJECT, group)))
                    .validate(it?.head)
            }
        }

    fun patch(description: String = "", rule: OperationRule.() -> OperationRule) = 
        apply {
            add {
                rule(OperationRule(RuleGroup.named("patch", description, RuleGroup.Category.OBJECT, group)))
                    .validate(it?.patch)
            }
        }

    fun trace(description: String = "", rule: OperationRule.() -> OperationRule) = 
        apply {
            add {
                rule(OperationRule(RuleGroup.named("trace", description, RuleGroup.Category.OBJECT, group)))
                    .validate(it?.trace)
            }
        }

    fun servers(description: String = "", rule: ServersRule.() -> ServersRule) = 
        apply {
            add {
                rule(ServersRule(RuleGroup.named("servers", description, RuleGroup.Category.OBJECT, group)))
                    .validate(it?.servers)
            }
        }

    fun parameters(description: String = "", rule: ParametersRule.() -> ParametersRule) = 
        apply {
            add {
                rule(ParametersRule(RuleGroup.named("parameters", description, RuleGroup.Category.OBJECT, group)))
                    .validate(it?.parameters)
            }
        }
}