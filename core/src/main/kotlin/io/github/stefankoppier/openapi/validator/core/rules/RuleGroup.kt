package io.github.stefankoppier.openapi.validator.core.rules

data class RuleGroup internal constructor(
    val parent: RuleGroup?,
    val name: String,
    val description: String,
    val category: Category,
) {

    enum class Category {
        EXCEPTION,
        OBJECT,
        FIELD,
        UNKNOWN,
        MESSAGE,
    }

    companion object {
        fun named(name: String, description: String, category: Category, parent: RuleGroup? = null): RuleGroup {
            return RuleGroup(parent, name, description, category)
        }

        fun unknown(): RuleGroup {
            return RuleGroup(null, "", "", Category.UNKNOWN)
        }
    }
}
