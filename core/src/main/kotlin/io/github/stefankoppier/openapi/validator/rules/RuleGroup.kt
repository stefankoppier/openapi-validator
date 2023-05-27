package io.github.stefankoppier.openapi.validator.rules

data class RuleGroup internal constructor(val parent: RuleGroup?, val name: String) {

    companion object {
        fun named(name: String, parent: RuleGroup? = null): RuleGroup {
            return RuleGroup(parent, name)
        }

        fun unknown(): RuleGroup {
            return RuleGroup(null, "")
        }
    }
}