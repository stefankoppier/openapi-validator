package io.github.stefankoppier.openapi.validator.core.rules.openapi

import io.github.stefankoppier.openapi.validator.core.rules.RuleGroup
import io.github.stefankoppier.openapi.validator.core.rules.RuleGroupCategory
import io.github.stefankoppier.openapi.validator.core.rules.ValidationRule
import io.github.stefankoppier.openapi.validator.core.rules.primitives.BooleanRule
import io.github.stefankoppier.openapi.validator.core.rules.primitives.StringRule
import io.swagger.v3.oas.models.Operation

class OperationRule(group: RuleGroup) : ValidationRule<Operation>(group) {

    fun summary(rule: StringRule.() -> StringRule): OperationRule {
        add {
            rule(StringRule(RuleGroup.named("summary", RuleGroupCategory.FIELD, group))).validate(it.summary)
        }
        return this
    }

    fun description(rule: StringRule.() -> StringRule): OperationRule {
        add {
            rule(StringRule(RuleGroup.named("description", RuleGroupCategory.FIELD, group))).validate(it.description)
        }
        return this
    }

    // externalDocs

    fun operationId(rule: StringRule.() -> StringRule): OperationRule {
        add {
            rule(StringRule(RuleGroup.named("operationId", RuleGroupCategory.FIELD, group))).validate(it.operationId)
        }
        return this
    }

    fun parameters(rule: ParametersRule.() -> ParametersRule): OperationRule {
        add {
            rule(ParametersRule(RuleGroup.named("parameters", RuleGroupCategory.FIELD, group))).validate(it.parameters)
        }
        return this
    }

    // requestBody

    fun responses(rule: ResponsesRule.() -> ResponsesRule): OperationRule {
        add {
            rule(ResponsesRule(RuleGroup.named("responses", RuleGroupCategory.FIELD, group))).validate(it.responses)
        }
        return this
    }

    // callbacks

    fun deprecated(rule: BooleanRule.() -> BooleanRule): OperationRule {
        add {
            rule(BooleanRule(RuleGroup.named("deprecated", RuleGroupCategory.FIELD, group))).validate(it.deprecated)
        }
        return this
    }

    // security

    fun servers(rule: ServersRule.() -> ServersRule): OperationRule {
        add {
            rule(ServersRule(RuleGroup.named("servers", RuleGroupCategory.FIELD, group))).validate(it.servers)
        }
        return this
    }
}
