package io.github.stefankoppier.openapi.validator.rules.openapi

import io.github.stefankoppier.openapi.validator.rules.RuleGroup
import io.github.stefankoppier.openapi.validator.rules.ValidationRule
import io.swagger.v3.oas.models.servers.Server

class ServersRule(group: RuleGroup) : ValidationRule<List<Server>>(group) {

}
