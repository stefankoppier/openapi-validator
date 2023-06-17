package io.github.stefankoppier.openapi.validator.core.rules.openapi.collections

import io.github.stefankoppier.openapi.validator.core.rules.RuleGroup
import io.github.stefankoppier.openapi.validator.core.rules.openapi.SchemaRule
import io.github.stefankoppier.openapi.validator.core.rules.primitives.IterableValidationRule
import io.swagger.v3.oas.models.media.Schema

class SchemasRule(group: RuleGroup = RuleGroup.unknown()) : IterableValidationRule<Pair<String, Schema<*>>>(group) {

    fun all(description: String = "", rule: SchemaRule.() -> SchemaRule) =
        all { schema ->
            rule(SchemaRule(RuleGroup.named("schema '${schema.first}'", description, RuleGroup.Category.OBJECT, group)))
                .validate(schema.second)
        }

    fun schema(description: String = "", named: String, rule: SchemaRule.() -> SchemaRule)  =
        apply {
            add { schemas ->
                val schema = schemas?.find { it.first == named }
                rule(SchemaRule(RuleGroup.named("schema '$named'", description, RuleGroup.Category.OBJECT, group)))
                    .validate(schema?.second)
            }
        }
}