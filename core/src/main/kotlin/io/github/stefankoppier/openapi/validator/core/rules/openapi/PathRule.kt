package io.github.stefankoppier.openapi.validator.core.rules.openapi

import io.github.stefankoppier.openapi.validator.core.rules.RuleGroup
import io.github.stefankoppier.openapi.validator.core.rules.ValidationRule
import io.github.stefankoppier.openapi.validator.core.rules.openapi.collections.ParametersRule
import io.github.stefankoppier.openapi.validator.core.rules.openapi.collections.ServersRule
import io.github.stefankoppier.openapi.validator.core.rules.primitives.StringRule
import io.swagger.v3.oas.models.PathItem

class PathRule internal constructor(group: RuleGroup = RuleGroup.unknown()) : ValidationRule<Pair<String, PathItem>>(group) {

    fun path(rule: OperationPathRule.() -> OperationPathRule) =
        add {
            rule(OperationPathRule(group))
                .validate(it?.first)
        }

    fun summary(description: String = "", rule: StringRule.() -> StringRule) =
        add {
            rule(StringRule(RuleGroup.named("summary", RuleGroup.Category.FIELD, description, group)))
                .validate(it?.second?.summary)
        }

    fun description(description: String = "", rule: StringRule.() -> StringRule) =
        add {
            rule(StringRule(RuleGroup.named("description", RuleGroup.Category.FIELD, description, group)))
                .validate(it?.second?.description)
        }

    fun operations(description: String = "", rule: OperationRule.() -> OperationRule) =
        apply {
            optional({ it?.second?.get }) { get(description, rule) }
            optional({ it?.second?.put }) { put(description, rule) }
            optional({ it?.second?.post }) { put(description, rule) }
            optional({ it?.second?.delete }) { delete(description, rule) }
            optional({ it?.second?.options }) { options(description, rule) }
            optional({ it?.second?.head }) { head(description, rule) }
            optional({ it?.second?.patch }) { patch(description, rule) }
            optional({ it?.second?.trace }) { trace(description, rule) }
        }

fun get(description: String = "", rule: OperationRule.() -> OperationRule) =
        add {
            rule(OperationRule(RuleGroup.named("get", RuleGroup.Category.GROUP, description, group)))
                .validate(it?.second?.get)
        }

    fun put(description: String = "", rule: OperationRule.() -> OperationRule) =
        add {
            rule(OperationRule(RuleGroup.named("put", RuleGroup.Category.GROUP, description, group)))
                .validate(it?.second?.put)
        }

    fun post(description: String = "", rule: OperationRule.() -> OperationRule) =
        add {
            rule(OperationRule(RuleGroup.named("post", RuleGroup.Category.GROUP, description, group)))
                .validate(it?.second?.post)
        }

    fun delete(description: String = "", rule: OperationRule.() -> OperationRule) =
        add {
            rule(OperationRule(RuleGroup.named("delete", RuleGroup.Category.GROUP, description, group)))
                .validate(it?.second?.delete)
        }

    fun options(description: String = "", rule: OperationRule.() -> OperationRule) =
        add {
            rule(OperationRule(RuleGroup.named("options", RuleGroup.Category.GROUP, description, group)))
                .validate(it?.second?.options)
        }

    fun head(description: String = "", rule: OperationRule.() -> OperationRule) =
        add {
            rule(OperationRule(RuleGroup.named("head", RuleGroup.Category.GROUP, description, group)))
                .validate(it?.second?.head)
        }

    fun patch(description: String = "", rule: OperationRule.() -> OperationRule) =
        add {
            rule(OperationRule(RuleGroup.named("patch", RuleGroup.Category.GROUP, description, group)))
                .validate(it?.second?.patch)
        }

    fun trace(description: String = "", rule: OperationRule.() -> OperationRule) =
        add {
            rule(OperationRule(RuleGroup.named("trace", RuleGroup.Category.GROUP, description, group)))
                .validate(it?.second?.trace)
        }

    fun servers(description: String = "", rule: ServersRule.() -> ServersRule) =
        add {
            rule(ServersRule(RuleGroup.named("servers", RuleGroup.Category.GROUP, description, group)))
                .validate(it?.second?.servers)
        }

    fun parameters(description: String = "", rule: ParametersRule.() -> ParametersRule) =
        add {
            rule(ParametersRule(RuleGroup.named("parameters", RuleGroup.Category.GROUP, description, group)))
                .validate(it?.second?.parameters)
        }
}
