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

    fun name(description: String = "", rule: StringRule.() -> StringRule): ParameterRule {
        add {
            rule(StringRule(RuleGroup.named("name", description, RuleGroupCategory.FIELD, group))).validate(it?.name)
        }
        return this
    }

    // TODO: check if a different name can used so we don't need quotes.
    fun `in`(description: String = "", rule: StringRule.() -> StringRule): ParameterRule {
        add {
            rule(StringRule(RuleGroup.named("name", description, RuleGroupCategory.FIELD, group))).validate(it?.name)
        }
        return this
    }

    fun description(description: String = "", rule: StringRule.() -> StringRule): ParameterRule {
        add {
            rule(StringRule(RuleGroup.named("description", description, RuleGroupCategory.FIELD, group))).validate(it?.description)
        }
        return this
    }

    fun required(description: String = "", rule: BooleanRule.() -> BooleanRule): ParameterRule {
        add {
            rule(BooleanRule(RuleGroup.named("required", description, RuleGroupCategory.FIELD, group))).validate(it?.required)
        }
        return this
    }

    fun deprecated(description: String = "", rule: BooleanRule.() -> BooleanRule): ParameterRule {
        add {
            rule(BooleanRule(RuleGroup.named("deprecated", description, RuleGroupCategory.FIELD, group))).validate(it?.deprecated)
        }
        return this
    }

    fun allowEmptyValue(description: String = "", rule: BooleanRule.() -> BooleanRule): ParameterRule {
        add {
            rule(BooleanRule(RuleGroup.named("allowEmptyValue", description, RuleGroupCategory.FIELD, group))).validate(it?.allowEmptyValue)
        }
        return this
    }

//    fun style(description: String = "", rule: BooleanRule.() -> BooleanRule): ParameterRule {
//        add {
//            rule(BooleanRule(RuleGroup.named("style", description, RuleGroupCategory.FIELD, group))).validate(it?.style)
//        }
//        return this
//    }

    fun explode(description: String = "", rule: BooleanRule.() -> BooleanRule): ParameterRule {
        add {
            rule(BooleanRule(RuleGroup.named("explode", description, RuleGroupCategory.FIELD, group))).validate(it?.explode)
        }
        return this
    }

    fun allowReserved(description: String = "", rule: BooleanRule.() -> BooleanRule): ParameterRule {
        add {
            rule(BooleanRule(RuleGroup.named("allowReserved", description, RuleGroupCategory.FIELD, group))).validate(it?.allowReserved)
        }
        return this
    }

    fun schema(description: String = "", rule: SchemaRule<Any>.() -> SchemaRule<Any>): ParameterRule {
        add {
            rule(SchemaRule(RuleGroup.named("schema", description, RuleGroupCategory.OBJECT, group))).validate(it?.schema)
        }
        return this
    }

    fun example(description: String = "", rule: AnyRule.() -> AnyRule): ParameterRule {
        add {
            rule(AnyRule(RuleGroup.named("example", description, RuleGroupCategory.FIELD, group))).validate(it?.example)
        }
        return this
    }

    fun examples(description: String = "", rule: ExamplesRule.() -> ExamplesRule): ParameterRule {
        add {
            rule(ExamplesRule(RuleGroup.named("examples", description, RuleGroupCategory.FIELD, group))).validate(it?.examples?.toList())
        }
        return this
    }

    fun content(description: String = "", rule: ContentRule.() -> ContentRule): ParameterRule {
        add {
            rule(ContentRule(RuleGroup.named("content", description, RuleGroupCategory.OBJECT, group))).validate(it?.content?.toList())
        }
        return this
    }
}