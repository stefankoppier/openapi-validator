package io.github.stefankoppier.openapi.validator.core.rules.openapi

import io.github.stefankoppier.openapi.validator.core.rules.RuleGroup
import io.github.stefankoppier.openapi.validator.core.rules.RuleGroupCategory
import io.github.stefankoppier.openapi.validator.core.rules.ValidationRule
import io.github.stefankoppier.openapi.validator.core.rules.primitives.StringRule
import io.swagger.v3.oas.models.PathItem

class PathItemRule(group: RuleGroup = RuleGroup.unknown()) : ValidationRule<PathItem>(group) {

    fun summary(description: String = "", rule: StringRule.() -> StringRule): PathItemRule {
        add {
            rule(StringRule(RuleGroup.named("summary", description, RuleGroupCategory.FIELD, group))).validate(it.summary)
        }
        return this
    }

    fun description(description: String = "", rule: StringRule.() -> StringRule): PathItemRule {
        add {
            rule(StringRule(RuleGroup.named("description", description, RuleGroupCategory.FIELD, group))).validate(it.description)
        }
        return this
    }

    fun get(description: String = "", rule: OperationRule.() -> OperationRule): PathItemRule {
        add {
            rule(OperationRule(RuleGroup.named("get", description, RuleGroupCategory.OBJECT, group))).validate(it.get)
        }
        return this
    }

    fun put(description: String = "", rule: OperationRule.() -> OperationRule): PathItemRule {
        add {
            rule(OperationRule(RuleGroup.named("put", description, RuleGroupCategory.OBJECT, group))).validate(it.put)
        }
        return this
    }

    fun post(description: String = "", rule: OperationRule.() -> OperationRule): PathItemRule {
        add {
            rule(OperationRule(RuleGroup.named("post", description, RuleGroupCategory.OBJECT, group))).validate(it.post)
        }
        return this
    }

    fun delete(description: String = "", rule: OperationRule.() -> OperationRule): PathItemRule {
        add {
            rule(OperationRule(RuleGroup.named("delete", description, RuleGroupCategory.OBJECT, group))).validate(it.delete)
        }
        return this
    }

    fun options(description: String = "", rule: OperationRule.() -> OperationRule): PathItemRule {
        add {
            rule(OperationRule(RuleGroup.named("options", description, RuleGroupCategory.OBJECT, group))).validate(it.options)
        }
        return this
    }

    fun head(description: String = "", rule: OperationRule.() -> OperationRule): PathItemRule {
        add {
            rule(OperationRule(RuleGroup.named("head", description, RuleGroupCategory.OBJECT, group))).validate(it.head)
        }
        return this
    }

    fun patch(description: String = "", rule: OperationRule.() -> OperationRule): PathItemRule {
        add {
            rule(OperationRule(RuleGroup.named("patch", description, RuleGroupCategory.OBJECT, group))).validate(it.patch)
        }
        return this
    }

    fun trace(description: String = "", rule: OperationRule.() -> OperationRule): PathItemRule {
        add {
            rule(OperationRule(RuleGroup.named("trace", description, RuleGroupCategory.OBJECT, group))).validate(it.trace)
        }
        return this
    }

    fun servers(description: String = "", rule: ServersRule.() -> ServersRule): PathItemRule {
        add {
            rule(ServersRule(RuleGroup.named("servers", description, RuleGroupCategory.OBJECT, group))).validate(it.servers)
        }
        return this
    }

    fun parameters(description: String = "", rule: ParametersRule.() -> ParametersRule): PathItemRule {
        add {
            rule(ParametersRule(RuleGroup.named("parameters", description, RuleGroupCategory.OBJECT, group))).validate(it.parameters)
        }
        return this
    }
}

fun PathsRule.path(description: String = "", rule: PathItemRule.() -> PathItemRule): PathItemRule {
    return rule(PathItemRule(RuleGroup.named("path", description, RuleGroupCategory.OBJECT, group)))
}