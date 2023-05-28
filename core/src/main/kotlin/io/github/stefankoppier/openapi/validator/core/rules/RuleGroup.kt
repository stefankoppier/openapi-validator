package io.github.stefankoppier.openapi.validator.core.rules

enum class RuleGroupCategory {
    OBJECT,
    FIELD,
    UNKNOWN,
    MESSAGE,
}

data class RuleGroup internal constructor(
    val parent: RuleGroup?,
    val name: String,
    val description: String,
    val category: RuleGroupCategory)
{

    companion object {
        fun named(name: String, description: String, category: RuleGroupCategory, parent: RuleGroup? = null): RuleGroup {
            return RuleGroup(parent, name, description, category)
        }

        fun unknown(): RuleGroup {
            return RuleGroup(null, "", "", RuleGroupCategory.UNKNOWN)
        }
    }
}