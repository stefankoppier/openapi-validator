package io.github.stefankoppier.openapi.validator.core.rules.openapi

import io.github.stefankoppier.openapi.validator.core.rules.RuleGroup
import io.github.stefankoppier.openapi.validator.core.rules.ValidationRule
import io.github.stefankoppier.openapi.validator.core.rules.openapi.collections.ParametersRule
import io.github.stefankoppier.openapi.validator.core.rules.openapi.collections.ResponsesRule
import io.github.stefankoppier.openapi.validator.core.rules.openapi.collections.SecurityRequirementsRule
import io.github.stefankoppier.openapi.validator.core.rules.openapi.collections.ServersRule
import io.github.stefankoppier.openapi.validator.core.rules.primitives.BooleanRule
import io.github.stefankoppier.openapi.validator.core.rules.primitives.IterableStringRule
import io.github.stefankoppier.openapi.validator.core.rules.primitives.StringRule
import io.swagger.v3.oas.models.Operation

class OperationRule internal constructor(group: RuleGroup = RuleGroup.unknown()) : ValidationRule<Operation>(group) {

    fun tags(description: String = "", rule: IterableStringRule.() -> IterableStringRule) =
        apply {
            add {
                rule(IterableStringRule(RuleGroup.named("tags", description, RuleGroup.Category.FIELD, group)))
                    .validate(it?.tags)
            }
        }

    fun summary(description: String = "", rule: StringRule.() -> StringRule) =
        apply {
            add { rule(StringRule(RuleGroup.named("summary", description, RuleGroup.Category.FIELD, group)))
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

    fun externalDocs(description: String = "", rule: ExternalDocumentationRule.() -> ExternalDocumentationRule) =
        apply {
            add {
                rule(ExternalDocumentationRule(RuleGroup.named("externalDocs", description, RuleGroup.Category.OBJECT, group)))
                    .validate(it?.externalDocs)
            }
        }

    fun operationId(description: String = "", rule: StringRule.() -> StringRule) =
        apply {
            add {
                rule(StringRule(RuleGroup.named("operationId", description, RuleGroup.Category.FIELD, group)))
                    .validate(it?.operationId)
            }
        }

    fun parameters(description: String = "", rule: ParametersRule.() -> ParametersRule) =
        apply {
            add {
                rule(ParametersRule(RuleGroup.named("parameters", description, RuleGroup.Category.OBJECT, group)))
                    .validate(it?.parameters)
            }
        }

    fun requestBody(description: String = "", rule: RequestBodyRule.() -> RequestBodyRule) =
        apply {
            add {
                rule(RequestBodyRule(RuleGroup.named("requestBody", description, RuleGroup.Category.OBJECT, group)))
                    .validate(it?.requestBody)
            }
        }

    fun responses(description: String = "", rule: ResponsesRule.() -> ResponsesRule) =
        apply {
            add {
                rule(ResponsesRule(RuleGroup.named("responses", description, RuleGroup.Category.OBJECT, group)))
                    .validate(it?.responses?.toList())
            }
        }

    // callbacks

    fun deprecated(description: String = "", rule: BooleanRule.() -> BooleanRule) =
        apply {
            add {
                rule(BooleanRule(RuleGroup.named("deprecated", description, RuleGroup.Category.FIELD, group)))
                    .validate(it?.deprecated)
            }
        }

    fun security(description: String = "", rule: SecurityRequirementsRule.() -> SecurityRequirementsRule) =
        apply {
            add {
                rule(SecurityRequirementsRule(RuleGroup.named("security", description, RuleGroup.Category.OBJECT, group)))
                    .validate(it?.security)
            }
        }

    fun servers(description: String = "", rule: ServersRule.() -> ServersRule) =
        apply {
            add {
                rule(ServersRule(RuleGroup.named("servers", description, RuleGroup.Category.OBJECT, group)))
                    .validate(it?.servers)
            }
        }
}
