package io.github.stefankoppier.openapi.validator.core.rules.openapi

import io.github.stefankoppier.openapi.validator.core.rules.RuleGroup
import io.github.stefankoppier.openapi.validator.core.rules.ValidationRule
import io.github.stefankoppier.openapi.validator.core.rules.primitives.StringRule
import io.swagger.v3.oas.models.tags.Tag

class TagRule internal constructor(group: RuleGroup = RuleGroup.unknown()) : ValidationRule<Tag>(group) {

    init {
        name { required() }
    }

    fun name(description: String = "", rule: StringRule.() -> StringRule) =
        apply {
            add {
                rule(StringRule(RuleGroup.named("name", RuleGroup.Category.FIELD, description, group)))
                    .validate(it?.name)
            }
        }

    fun description(description: String = "", rule: StringRule.() -> StringRule) =
        apply {
            add {
                rule(StringRule(RuleGroup.named("description", RuleGroup.Category.FIELD, description, group)))
                    .validate(it?.description)
            }
        }

    fun externalDocs(description: String = "", rule: ExternalDocumentationRule.() -> ExternalDocumentationRule) =
        apply {
            add {
                rule(
                    ExternalDocumentationRule(
                        RuleGroup.named(
                            "externalDocs",
                            RuleGroup.Category.GROUP,
                            description,
                            group,
                        ),
                    ),
                )
                    .validate(it?.externalDocs)
            }
        }
}
