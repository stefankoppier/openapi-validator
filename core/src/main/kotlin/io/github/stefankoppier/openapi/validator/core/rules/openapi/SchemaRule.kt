package io.github.stefankoppier.openapi.validator.core.rules.openapi

import io.github.stefankoppier.openapi.validator.core.rules.RuleGroup
import io.github.stefankoppier.openapi.validator.core.rules.ValidationRule
import io.github.stefankoppier.openapi.validator.core.rules.primitives.*
import io.swagger.v3.oas.models.media.Schema

class SchemaRule(group: RuleGroup) : ValidationRule<Schema<*>>(group) {

    fun name(description: String = "", rule: StringRule.() -> StringRule) =
        apply {
            add {
                rule(StringRule(RuleGroup.named("name", description, RuleGroup.Category.FIELD, group)))
                    .validate(it?.name)
            }
        }

    fun title(description: String = "", rule: StringRule.() -> StringRule) =
        apply {
            add {
                rule(StringRule(RuleGroup.named("title", description, RuleGroup.Category.FIELD, group)))
                    .validate(it?.title)
            }
        }

    fun multipleOf(description: String = "", rule: BigDecimalRule.() -> BigDecimalRule) =
        apply {
            add {
                rule(BigDecimalRule(RuleGroup.named("multipleOf", description, RuleGroup.Category.FIELD, group)))
                    .validate(it?.multipleOf)
            }
        }

    fun maximum(description: String = "", rule: BigDecimalRule.() -> BigDecimalRule) =
        apply {
            add {
                rule(BigDecimalRule(RuleGroup.named("maximum", description, RuleGroup.Category.FIELD, group)))
                    .validate(it?.maximum)
            }
        }

    fun exclusiveMaximum(description: String = "", rule: BooleanRule.() -> BooleanRule) =
        apply {
            add {
                rule(BooleanRule(RuleGroup.named("exclusiveMaximum", description, RuleGroup.Category.FIELD, group)))
                    .validate(it?.exclusiveMaximum)
            }
        }

    fun minimum(description: String = "", rule: BigDecimalRule.() -> BigDecimalRule) =
        apply {
            add {
                rule(BigDecimalRule(RuleGroup.named("minimum", description, RuleGroup.Category.FIELD, group)))
                    .validate(it?.minimum)
            }
        }

    fun exclusiveMinimum(description: String = "", rule: BooleanRule.() -> BooleanRule) =
        apply {
            add {
                rule(BooleanRule(RuleGroup.named("exclusiveMinimum", description, RuleGroup.Category.FIELD, group)))
                    .validate(it?.exclusiveMinimum)
            }
        }

    fun maxLength(description: String = "", rule: IntegerRule.() -> IntegerRule) =
        apply {
            add {
                rule(IntegerRule(RuleGroup.named("maxLength", description, RuleGroup.Category.FIELD, group)))
                    .validate(it?.maxLength)
            }
        }

    fun minLength(description: String = "", rule: IntegerRule.() -> IntegerRule) =
        apply {
            add {
                rule(IntegerRule(RuleGroup.named("minLength", description, RuleGroup.Category.FIELD, group)))
                    .validate(it?.minLength)
            }
        }

    fun pattern(description: String = "", rule: StringRule.() -> StringRule) =
        apply {
            add {
                rule(StringRule(RuleGroup.named("pattern", description, RuleGroup.Category.FIELD, group)))
                    .validate(it?.pattern)
            }
        }

    fun maxItems(description: String = "", rule: IntegerRule.() -> IntegerRule) =
        apply {
            add {
                rule(IntegerRule(RuleGroup.named("maxItems", description, RuleGroup.Category.FIELD, group)))
                    .validate(it?.maxItems)
            }
        }

    fun minItems(description: String = "", rule: IntegerRule.() -> IntegerRule) =
        apply {
            add {
                rule(IntegerRule(RuleGroup.named("minItems", description, RuleGroup.Category.FIELD, group)))
                    .validate(it?.minItems)
            }
        }

    fun uniqueItems(description: String = "", rule: BooleanRule.() -> BooleanRule) =
        apply {
            add {
                rule(BooleanRule(RuleGroup.named("uniqueItems", description, RuleGroup.Category.FIELD, group)))
                    .validate(it?.uniqueItems)
            }
        }

    fun maxProperties(description: String = "", rule: IntegerRule.() -> IntegerRule) =
        apply {
            add {
                rule(IntegerRule(RuleGroup.named("maxProperties", description, RuleGroup.Category.FIELD, group)))
                    .validate(it?.maxProperties)
            }
        }

    fun minProperties(description: String = "", rule: IntegerRule.() -> IntegerRule) =
        apply {
            add {
                rule(IntegerRule(RuleGroup.named("minProperties", description, RuleGroup.Category.FIELD, group)))
                    .validate(it?.minProperties)
            }
        }

    fun required(description: String = "", rule: IterableStringRule.() -> IterableStringRule) =
        apply {
            add {
                rule(IterableStringRule(RuleGroup.named("required", description, RuleGroup.Category.FIELD, group)))
                    .validate(it?.required)
            }
        }

    fun type(description: String = "", rule: StringRule.() -> StringRule) =
        apply {
            add {
                rule(StringRule(RuleGroup.named("type", description, RuleGroup.Category.FIELD, group)))
                    .validate(it?.type)
            }
        }

//    fun enum(description: String = "", rule: IterableValidationRule<*>.() -> IterableValidationRule<*>) =
//        apply {
//            add {
//                rule(IterableValidationRule<*>(RuleGroup.named("enum", description, RuleGroup.Category.FIELD, group)))
//                    .validate(it?.enum)
//            }
//        }

    // not

    // properties

    // additionalProperties


    fun description(description: String = "", rule: StringRule.() -> StringRule) =
        apply {
            add {
                rule(StringRule(RuleGroup.named("description", description, RuleGroup.Category.FIELD, group)))
                    .validate(it?.description)
            }
        }

    fun format(description: String = "", rule: StringRule.() -> StringRule) =
        apply {
            add {
                rule(StringRule(RuleGroup.named("format", description, RuleGroup.Category.FIELD, group)))
                    .validate(it?.format)
            }
        }

    fun ref(description: String = "", rule: StringRule.() -> StringRule) =
        apply {
            add {
                rule(StringRule(RuleGroup.named("title", description, RuleGroup.Category.FIELD, group)))
                    .validate(it?.`$ref`)
            }
        }

    fun nullable(description: String = "", rule: BooleanRule.() -> BooleanRule) =
        apply {
            add {
                rule(BooleanRule(RuleGroup.named("nullable", description, RuleGroup.Category.FIELD, group)))
                    .validate(it?.nullable)
            }
        }

    fun readOnly(description: String = "", rule: BooleanRule.() -> BooleanRule) =
        apply {
            add {
                rule(BooleanRule(RuleGroup.named("readOnly", description, RuleGroup.Category.FIELD, group)))
                    .validate(it?.readOnly)
            }
        }

    fun writeOnly(description: String = "", rule: BooleanRule.() -> BooleanRule) =
        apply {
            add {
                rule(BooleanRule(RuleGroup.named("writeOnly", description, RuleGroup.Category.FIELD, group)))
                    .validate(it?.writeOnly)
            }
        }

    fun externalDocs(description: String = "", rule: ExternalDocumentationRule.() -> ExternalDocumentationRule) =
        apply {
            add {
                rule(ExternalDocumentationRule(RuleGroup.named("externalDocs", description, RuleGroup.Category.OBJECT, group)))
                    .validate(it?.externalDocs)
            }
        }

    fun deprecated(description: String = "", rule: BooleanRule.() -> BooleanRule) =
        apply {
            add {
                rule(BooleanRule(RuleGroup.named("deprecated", description, RuleGroup.Category.FIELD, group)))
                    .validate(it?.deprecated)
            }
        }

    fun items(description: String = "", rule: SchemaRule.() -> SchemaRule) =
        apply {
            add {
                rule(SchemaRule(RuleGroup.named("items", description, RuleGroup.Category.FIELD, group)))
                    .validate(it?.items)
            }
        }

    // xml

    // extensions

    // _enum

    // descriminator

}
