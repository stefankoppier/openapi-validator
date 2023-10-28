package io.github.stefankoppier.openapi.validator.core.rules.openapi

import io.github.stefankoppier.openapi.validator.core.rules.RuleGroup
import io.github.stefankoppier.openapi.validator.core.rules.ValidationRule
import io.github.stefankoppier.openapi.validator.core.rules.primitives.AnyRule
import io.github.stefankoppier.openapi.validator.core.rules.primitives.StringRule
import io.swagger.v3.oas.models.links.Link

class LinkRule(group: RuleGroup = RuleGroup.unknown()) : ValidationRule<Link>(group) {

    fun operationRef(description: String = "", rule: StringRule.() -> StringRule) =
        apply {
            add {
                rule(StringRule(RuleGroup.named("operationRef", RuleGroup.Category.FIELD, description, group)))
                    .validate(it?.operationRef)
            }
        }

    fun operationId(description: String = "", rule: StringRule.() -> StringRule) =
        apply {
            add {
                rule(StringRule(RuleGroup.named("operationId", RuleGroup.Category.FIELD, description, group)))
                    .validate(it?.operationId)
            }
        }

//    fun parameters(description: String = "", rule: MapValidationRule<String, String>.() -> MapValidationRule<String, String>) =
//        apply {
//            add {
//                rule(MapValidationRule(RuleGroup.named("parameters", RuleGroup.Category.GROUP, description, group)))
//                    .validate(it?.parameters)
//            }
//        }

    fun requestBody(description: String = "", rule: AnyRule.() -> AnyRule) =
        apply {
            add {
                rule(AnyRule(RuleGroup.named("requestBody", RuleGroup.Category.FIELD, description, group)))
                    .validate(it?.requestBody)
            }
        }

    fun description(description: String = "", rule: StringRule.() -> StringRule) =
        apply {
            add {
                rule(StringRule(RuleGroup.named("description", RuleGroup.Category.FIELD, description, group)))
                    .validate(it?.description)
            }
        }

    fun server(description: String = "", rule: ServerRule.() -> ServerRule) =
        apply {
            add {
                rule(ServerRule(RuleGroup.named("server", RuleGroup.Category.GROUP, description, group)))
                    .validate(it?.server)
            }
        }
}
