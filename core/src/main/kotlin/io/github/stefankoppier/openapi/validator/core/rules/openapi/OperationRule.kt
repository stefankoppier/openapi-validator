package io.github.stefankoppier.openapi.validator.core.rules.openapi

import io.github.stefankoppier.openapi.validator.core.rules.RuleGroup
import io.github.stefankoppier.openapi.validator.core.rules.RuleGroupCategory
import io.github.stefankoppier.openapi.validator.core.rules.ValidationRule
import io.github.stefankoppier.openapi.validator.core.rules.primitives.BooleanRule
import io.github.stefankoppier.openapi.validator.core.rules.primitives.StringRule
import io.swagger.v3.oas.models.Operation

class OperationRule(group: RuleGroup) : ValidationRule<Operation>(group) {

    fun summary(description: String = "", rule: StringRule.() -> StringRule): OperationRule {
        add {
            rule(StringRule(RuleGroup.named("summary", description, RuleGroupCategory.FIELD, group))).validate(it.summary)
        }
        return this
    }

    fun description(description: String = "", rule: StringRule.() -> StringRule): OperationRule {
        add {
            rule(StringRule(RuleGroup.named("description", description, RuleGroupCategory.FIELD, group))).validate(it.description)
        }
        return this
    }

    // externalDocs

    fun operationId(description: String = "", rule: StringRule.() -> StringRule): OperationRule {
        add {
            rule(StringRule(RuleGroup.named("operationId", description, RuleGroupCategory.FIELD, group))).validate(it.operationId)
        }
        return this
    }

    fun parameters(description: String = "", rule: ParametersRule.() -> ParametersRule): OperationRule {
        add {
            rule(ParametersRule(RuleGroup.named("parameters", description, RuleGroupCategory.FIELD, group))).validate(it.parameters)
        }
        return this
    }

    // requestBody

    fun responses(description: String = "", rule: ResponsesRule.() -> ResponsesRule): OperationRule {
        add {
            rule(ResponsesRule(RuleGroup.named("responses", description, RuleGroupCategory.FIELD, group))).validate(it.responses)
        }
        return this
    }

    // callbacks

    fun deprecated(description: String = "", rule: BooleanRule.() -> BooleanRule): OperationRule {
        add {
            rule(BooleanRule(RuleGroup.named("deprecated", description, RuleGroupCategory.FIELD, group))).validate(it.deprecated)
        }
        return this
    }

    // security

    fun servers(description: String = "", rule: ServersRule.() -> ServersRule): OperationRule {
        add {
            rule(ServersRule(RuleGroup.named("servers", description, RuleGroupCategory.FIELD, group))).validate(it.servers)
        }
        return this
    }
}
