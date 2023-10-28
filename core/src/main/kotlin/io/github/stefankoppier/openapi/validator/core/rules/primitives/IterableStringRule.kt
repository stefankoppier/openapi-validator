package io.github.stefankoppier.openapi.validator.core.rules.primitives

import io.github.stefankoppier.openapi.validator.core.rules.RuleGroup

class IterableStringRule internal constructor(group: RuleGroup = RuleGroup.unknown()) : IterableRule<String>(group)
