package io.github.stefankoppier.openapi.validator.core.rules.openapi

import io.github.stefankoppier.openapi.validator.core.rules.RuleGroup
import io.github.stefankoppier.openapi.validator.core.rules.RuleGroupCategory
import io.github.stefankoppier.openapi.validator.core.rules.ValidationRule
import io.github.stefankoppier.openapi.validator.core.rules.openapi.collections.ParametersRule
import io.github.stefankoppier.openapi.validator.core.rules.openapi.collections.ResponsesRule
import io.github.stefankoppier.openapi.validator.core.rules.openapi.collections.ServersRule
import io.github.stefankoppier.openapi.validator.core.rules.primitives.BooleanRule
import io.github.stefankoppier.openapi.validator.core.rules.primitives.IterableStringRule
import io.github.stefankoppier.openapi.validator.core.rules.primitives.StringRule
import io.swagger.v3.oas.models.Operation

class OperationRule(group: RuleGroup) : ValidationRule<Operation>(group) {

    fun tags(description: String = "", rule: IterableStringRule.() -> IterableStringRule) =
        apply {
            add {
                rule(IterableStringRule(RuleGroup.named("tags", description, RuleGroupCategory.FIELD, group)))
                    .validate(it?.tags)
            }
        }

    fun summary(description: String = "", rule: StringRule.() -> StringRule) =
        apply {
            add {
            rule(StringRule(RuleGroup.named("summary", description, RuleGroupCategory.FIELD, group)))
                .validate(it?.summary)
        }
    }

    fun description(description: String = "", rule: StringRule.() -> StringRule) =
        apply {
            add {
                rule(StringRule(RuleGroup.named("description", description, RuleGroupCategory.FIELD, group)))
                    .validate(it?.description)
            }
        }

    fun externalDocs(description: String = "", rule: ExternalDocumentationRule.() -> ExternalDocumentationRule) =
        apply {
            add {
                rule(ExternalDocumentationRule(RuleGroup.named("externalDocs", description, RuleGroupCategory.FIELD, group)))
                    .validate(it?.externalDocs)
            }
        }

    fun operationId(description: String = "", rule: StringRule.() -> StringRule) =
        apply {
            add {
                rule(StringRule(RuleGroup.named("operationId", description, RuleGroupCategory.FIELD, group)))
                    .validate(it?.operationId)
            }
        }

    fun parameters(description: String = "", rule: ParametersRule.() -> ParametersRule) =
        apply {
            add {
                rule(ParametersRule(RuleGroup.named("parameters", description, RuleGroupCategory.FIELD, group)))
                    .validate(it?.parameters)
            }
        }

    // requestBody

    fun responses(description: String = "", rule: ResponsesRule.() -> ResponsesRule) =
        apply {
            add {
                rule(ResponsesRule(RuleGroup.named("responses", description, RuleGroupCategory.FIELD, group)))
                    .validate(it?.responses?.toList())
            }
        }

    // callbacks

    fun deprecated(description: String = "", rule: BooleanRule.() -> BooleanRule) =
        apply {
            add {
                rule(BooleanRule(RuleGroup.named("deprecated", description, RuleGroupCategory.FIELD, group)))
                    .validate(it?.deprecated)
            }
        }

    // security

    fun servers(description: String = "", rule: ServersRule.() -> ServersRule) =
        apply {
            add {
                rule(ServersRule(RuleGroup.named("servers", description, RuleGroupCategory.FIELD, group)))
                    .validate(it?.servers)
            }
        }
}
