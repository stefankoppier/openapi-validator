package io.github.stefankoppier.openapi.validator.core.rules.openapi

import io.github.stefankoppier.openapi.validator.core.rules.RuleGroup
import io.github.stefankoppier.openapi.validator.core.rules.ValidationRule
import io.swagger.v3.oas.models.media.Schema

class SchemaRule<T>(group: RuleGroup) : ValidationRule<Schema<T>>(group) {

}
