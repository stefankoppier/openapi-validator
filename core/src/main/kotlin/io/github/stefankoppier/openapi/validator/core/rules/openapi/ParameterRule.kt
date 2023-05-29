package io.github.stefankoppier.openapi.validator.core.rules.openapi

import io.github.stefankoppier.openapi.validator.core.rules.RuleGroup
import io.github.stefankoppier.openapi.validator.core.rules.RuleGroupCategory
import io.github.stefankoppier.openapi.validator.core.rules.ValidationRule
import io.github.stefankoppier.openapi.validator.core.rules.openapi.collections.ExamplesRule
import io.github.stefankoppier.openapi.validator.core.rules.primitives.AnyRule
import io.github.stefankoppier.openapi.validator.core.rules.primitives.BooleanRule
import io.github.stefankoppier.openapi.validator.core.rules.primitives.StringRule
import io.swagger.v3.oas.models.parameters.Parameter

class ParameterRule(group: RuleGroup) : ValidationRule<Parameter>(group) {

    fun name(description: String = "", rule: StringRule.() -> StringRule) =
        apply {
            add {
                rule(StringRule(RuleGroup.named("name", description, RuleGroupCategory.FIELD, group)))
                    .validate(it?.name)
            }
        }

    // TODO: check if a different name can used so we don't need quotes.
    fun `in`(description: String = "", rule: StringRule.() -> StringRule) =
        apply {
            add {
                rule(StringRule(RuleGroup.named("in", description, RuleGroupCategory.FIELD, group)))
                    .validate(it?.`in`)
            }
        }

    fun description(description: String = "", rule: StringRule.() -> StringRule) =
        apply {
            add {
                rule(StringRule(RuleGroup.named("description", description, RuleGroupCategory.FIELD, group)))
                    .validate(it?.description)
            }
        }

    fun required(description: String = "", rule: BooleanRule.() -> BooleanRule) =
        apply {
            add {
                rule(BooleanRule(RuleGroup.named("required", description, RuleGroupCategory.FIELD, group)))
                    .validate(it?.required)
            }
        }

    fun deprecated(description: String = "", rule: BooleanRule.() -> BooleanRule) =
        apply {
            add {
                rule(BooleanRule(RuleGroup.named("deprecated", description, RuleGroupCategory.FIELD, group)))
                    .validate(it?.deprecated)
            }
        }

    fun allowEmptyValue(description: String = "", rule: BooleanRule.() -> BooleanRule) =
        apply {
            add {
                rule(BooleanRule(RuleGroup.named("allowEmptyValue", description, RuleGroupCategory.FIELD, group)))
                    .validate(it?.allowEmptyValue)
            }
        }

//    fun style(description: String = "", rule: BooleanRule.() -> BooleanRule) =
//        apply {
//    add {
//            rule(BooleanRule(RuleGroup.named("style", description, RuleGroupCategory.FIELD, group)))
//            .validate(it?.style)
//        }
//        
//    }

    fun explode(description: String = "", rule: BooleanRule.() -> BooleanRule) =
        apply {
            add {
                rule(BooleanRule(RuleGroup.named("explode", description, RuleGroupCategory.FIELD, group)))
                    .validate(it?.explode)
            }
        }

    fun allowReserved(description: String = "", rule: BooleanRule.() -> BooleanRule) =
        apply {
            add {
                rule(BooleanRule(RuleGroup.named("allowReserved", description, RuleGroupCategory.FIELD, group)))
                    .validate(it?.allowReserved)
            }
        }

    fun schema(description: String = "", rule: SchemaRule<Any>.() -> SchemaRule<Any>) =
        apply {
            add {
                rule(SchemaRule(RuleGroup.named("schema", description, RuleGroupCategory.OBJECT, group)))
                    .validate(it?.schema)
            }
        }

    fun example(description: String = "", rule: AnyRule.() -> AnyRule) =
        apply {
            add {
                rule(AnyRule(RuleGroup.named("example", description, RuleGroupCategory.FIELD, group)))
                    .validate(it?.example)
        }
    }

    fun examples(description: String = "", rule: ExamplesRule.() -> ExamplesRule) =
        apply {
            add {
                rule(ExamplesRule(RuleGroup.named("examples", description, RuleGroupCategory.FIELD, group)))
                    .validate(it?.examples?.toList())
            }
        }

    fun content(description: String = "", rule: ContentRule.() -> ContentRule) =
        apply {
            add {
                rule(ContentRule(RuleGroup.named("content", description, RuleGroupCategory.OBJECT, group)))
                    .validate(it?.content?.toList())
            }
        }
}