package io.github.stefankoppier.openapi.validator.core.rules.openapi

import io.github.stefankoppier.openapi.validator.core.rules.RuleGroup
import io.github.stefankoppier.openapi.validator.core.rules.RuleGroupCategory
import io.github.stefankoppier.openapi.validator.core.rules.ValidationRule
import io.github.stefankoppier.openapi.validator.core.rules.primitives.StringRule
import io.swagger.v3.oas.models.PathItem

class PathItemRule(group: RuleGroup) : ValidationRule<PathItem>(group) {

    fun summary(rule: StringRule.() -> StringRule): PathItemRule {
        add {
            rule(StringRule(RuleGroup.named("summary", RuleGroupCategory.FIELD, group))).validate(it.summary)
        }
        return this
    }

    fun description(rule: StringRule.() -> StringRule): PathItemRule {
        add {
            rule(StringRule(RuleGroup.named("description", RuleGroupCategory.FIELD, group))).validate(it.description)
        }
        return this
    }

    fun get(rule: OperationRule.() -> OperationRule): PathItemRule {
        add {
            rule(OperationRule(RuleGroup.named("get", RuleGroupCategory.OBJECT, group))).validate(it.get)
        }
        return this
    }

    fun put(rule: OperationRule.() -> OperationRule): PathItemRule {
        add {
            rule(OperationRule(RuleGroup.named("put", RuleGroupCategory.OBJECT, group))).validate(it.put)
        }
        return this
    }

    fun post(rule: OperationRule.() -> OperationRule): PathItemRule {
        add {
            rule(OperationRule(RuleGroup.named("post", RuleGroupCategory.OBJECT, group))).validate(it.post)
        }
        return this
    }

    fun delete(rule: OperationRule.() -> OperationRule): PathItemRule {
        add {
            rule(OperationRule(RuleGroup.named("delete", RuleGroupCategory.OBJECT, group))).validate(it.delete)
        }
        return this
    }

    fun options(rule: OperationRule.() -> OperationRule): PathItemRule {
        add {
            rule(OperationRule(RuleGroup.named("options", RuleGroupCategory.OBJECT, group))).validate(it.options)
        }
        return this
    }

    fun head(rule: OperationRule.() -> OperationRule): PathItemRule {
        add {
            rule(OperationRule(RuleGroup.named("head", RuleGroupCategory.OBJECT, group))).validate(it.head)
        }
        return this
    }

    fun patch(rule: OperationRule.() -> OperationRule): PathItemRule {
        add {
            rule(OperationRule(RuleGroup.named("patch", RuleGroupCategory.OBJECT, group))).validate(it.patch)
        }
        return this
    }

    fun trace(rule: OperationRule.() -> OperationRule): PathItemRule {
        add {
            rule(OperationRule(RuleGroup.named("trace", RuleGroupCategory.OBJECT, group))).validate(it.trace)
        }
        return this
    }

    fun servers(rule: ServersRule.() -> ServersRule): PathItemRule {
        add {
            rule(ServersRule(RuleGroup.named("servers", RuleGroupCategory.OBJECT, group))).validate(it.servers)
        }
        return this
    }

    fun parameters(rule: ParametersRule.() -> ParametersRule): PathItemRule {
        add {
            rule(ParametersRule(RuleGroup.named("parameters", RuleGroupCategory.OBJECT, group))).validate(it.parameters)
        }
        return this
    }
}

fun PathsRule.path(rule: PathItemRule.() -> PathItemRule): PathItemRule {
    return rule(PathItemRule(RuleGroup.named("path", RuleGroupCategory.OBJECT, group)))
}