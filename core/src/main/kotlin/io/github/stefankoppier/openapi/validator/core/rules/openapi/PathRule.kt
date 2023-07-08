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
                rule(StringRule(RuleGroup.named("summary", RuleGroup.Category.FIELD, description, group)))
                    .validate(it?.summary)
            }
        }

    fun description(description: String = "", rule: StringRule.() -> StringRule) =
        apply {
            add {
                rule(StringRule(RuleGroup.named("description", RuleGroup.Category.FIELD, description, group)))
                    .validate(it?.description)
            }
        }

    fun operations(description: String = "", rule: OperationRule.() -> OperationRule) =
        apply {
            optional({ it?.get }) { get(description, rule) }
            optional({ it?.put }) { put(description, rule) }
            optional({ it?.post }) { put(description, rule) }
            optional({ it?.delete }) { delete(description, rule) }
            optional({ it?.options }) { options(description, rule) }
            optional({ it?.head }) { head(description, rule) }
            optional({ it?.patch }) { patch(description, rule) }
            optional({ it?.trace }) { trace(description, rule) }
        }

    fun get(description: String = "", rule: OperationRule.() -> OperationRule) =
        apply {
            add {
                rule(OperationRule(RuleGroup.named("get", RuleGroup.Category.GROUP, description, group)))
                    .validate(it?.get)
            }
        }

    fun put(description: String = "", rule: OperationRule.() -> OperationRule) =
        apply {
            add {
                rule(OperationRule(RuleGroup.named("put", RuleGroup.Category.GROUP, description, group)))
                    .validate(it?.put)
            }
        }

    fun post(description: String = "", rule: OperationRule.() -> OperationRule) =
        apply {
            add {
                rule(OperationRule(RuleGroup.named("post", RuleGroup.Category.GROUP, description, group)))
                    .validate(it?.post)
            }
        }

    fun delete(description: String = "", rule: OperationRule.() -> OperationRule) =
        apply {
            add {
                rule(OperationRule(RuleGroup.named("delete", RuleGroup.Category.GROUP, description, group)))
                    .validate(it?.delete)
            }
        }

    fun options(description: String = "", rule: OperationRule.() -> OperationRule) =
        apply {
            add {
                rule(OperationRule(RuleGroup.named("options", RuleGroup.Category.GROUP, description, group)))
                    .validate(it?.options)
            }
        }

    fun head(description: String = "", rule: OperationRule.() -> OperationRule) =
        apply {
            add {
                rule(OperationRule(RuleGroup.named("head", RuleGroup.Category.GROUP, description, group)))
                    .validate(it?.head)
            }
        }

    fun patch(description: String = "", rule: OperationRule.() -> OperationRule) =
        apply {
            add {
                rule(OperationRule(RuleGroup.named("patch", RuleGroup.Category.GROUP, description, group)))
                    .validate(it?.patch)
            }
        }

    fun trace(description: String = "", rule: OperationRule.() -> OperationRule) =
        apply {
            add {
                rule(OperationRule(RuleGroup.named("trace", RuleGroup.Category.GROUP, description, group)))
                    .validate(it?.trace)
            }
        }

    fun servers(description: String = "", rule: ServersRule.() -> ServersRule) =
        apply {
            add {
                rule(ServersRule(RuleGroup.named("servers", RuleGroup.Category.GROUP, description, group)))
                    .validate(it?.servers)
            }
        }

    fun parameters(description: String = "", rule: ParametersRule.() -> ParametersRule) =
        apply {
            add {
                rule(ParametersRule(RuleGroup.named("parameters", RuleGroup.Category.GROUP, description, group)))
                    .validate(it?.parameters)
            }
        }
}
