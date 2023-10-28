package io.github.stefankoppier.openapi.validator.core.rules.openapi

import io.github.stefankoppier.openapi.validator.core.rules.RuleGroup
import io.github.stefankoppier.openapi.validator.core.rules.ValidationRule
import io.github.stefankoppier.openapi.validator.core.rules.openapi.collections.ContentRule
import io.github.stefankoppier.openapi.validator.core.rules.openapi.collections.ExamplesRule
import io.github.stefankoppier.openapi.validator.core.rules.primitives.AnyRule
import io.github.stefankoppier.openapi.validator.core.rules.primitives.BooleanRule
import io.github.stefankoppier.openapi.validator.core.rules.primitives.EnumRule
import io.github.stefankoppier.openapi.validator.core.rules.primitives.StringRule
import io.swagger.v3.oas.models.parameters.Parameter

class ParameterRule internal constructor(group: RuleGroup = RuleGroup.unknown()) : ValidationRule<Parameter>(group) {

    init {
        given({ it != null }) {
            name { required() }
            `in` { required() }
            given({ it != null && it.`in` == "path" }) {
                required { required(); isTrue() }
            }
        }
    }

    fun name(description: String = "", rule: StringRule.() -> StringRule) =
        apply {
            add {
                rule(StringRule(RuleGroup.named("name", RuleGroup.Category.FIELD, description, group)))
                    .validate(it?.name)
            }
        }

    fun `in`(description: String = "", rule: StringRule.() -> StringRule) =
        apply {
            add {
                rule(StringRule(RuleGroup.named("in", RuleGroup.Category.FIELD, description, group)))
                    .validate(it?.`in`)
            }
        }

    fun description(description: String = "", rule: StringRule.() -> StringRule) =
        apply {
            add {
                rule(StringRule(RuleGroup.named("description", RuleGroup.Category.FIELD, description, group)))
                    .validate(it?.description)
            }
        }

    fun required(description: String = "", rule: BooleanRule.() -> BooleanRule) =
        apply {
            add {
                rule(BooleanRule(RuleGroup.named("required", RuleGroup.Category.FIELD, description, group)))
                    .validate(it?.required)
            }
        }

    fun deprecated(description: String = "", rule: BooleanRule.() -> BooleanRule) =
        apply {
            add {
                rule(BooleanRule(RuleGroup.named("deprecated", RuleGroup.Category.FIELD, description, group)))
                    .validate(it?.deprecated)
            }
        }

    fun allowEmptyValue(description: String = "", rule: BooleanRule.() -> BooleanRule) =
        apply {
            add {
                rule(BooleanRule(RuleGroup.named("allowEmptyValue", RuleGroup.Category.FIELD, description, group)))
                    .validate(it?.allowEmptyValue)
            }
        }

    fun style(description: String = "", rule: EnumRule<Parameter.StyleEnum>.() -> EnumRule<Parameter.StyleEnum>) =
        apply {
            add {
                rule(EnumRule(RuleGroup.named("style", RuleGroup.Category.FIELD, description, group)))
                    .validate(it?.style)
            }
        }

    fun explode(description: String = "", rule: BooleanRule.() -> BooleanRule) =
        apply {
            add {
                rule(BooleanRule(RuleGroup.named("explode", RuleGroup.Category.FIELD, description, group)))
                    .validate(it?.explode)
            }
        }

    fun allowReserved(description: String = "", rule: BooleanRule.() -> BooleanRule) =
        apply {
            add {
                rule(BooleanRule(RuleGroup.named("allowReserved", RuleGroup.Category.FIELD, description, group)))
                    .validate(it?.allowReserved)
            }
        }

    fun schema(description: String = "", rule: SchemaRule.() -> SchemaRule) =
        apply {
            add {
                rule(SchemaRule(RuleGroup.named("schema", RuleGroup.Category.GROUP, description, group)))
                    .validate(it?.schema)
            }
        }

    fun example(description: String = "", rule: AnyRule.() -> AnyRule) =
        apply {
            add {
                rule(AnyRule(RuleGroup.named("example", RuleGroup.Category.FIELD, description, group)))
                    .validate(it?.example)
            }
        }

    fun examples(description: String = "", rule: ExamplesRule.() -> ExamplesRule) =
        apply {
            add {
                rule(ExamplesRule(RuleGroup.named("examples", RuleGroup.Category.GROUP, description, group)))
                    .validate(it?.examples?.toList())
            }
        }

    fun content(description: String = "", rule: ContentRule.() -> ContentRule) =
        apply {
            add {
                rule(ContentRule(RuleGroup.named("content", RuleGroup.Category.GROUP, description, group)))
                    .validate(it?.content?.toList())
            }
        }
}
