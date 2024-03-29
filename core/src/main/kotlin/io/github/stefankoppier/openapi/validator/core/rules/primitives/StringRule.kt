package io.github.stefankoppier.openapi.validator.core.rules.primitives

import io.github.stefankoppier.openapi.validator.core.rules.RuleGroup
import io.github.stefankoppier.openapi.validator.core.rules.ValidationRule

open class StringRule internal constructor(group: RuleGroup = RuleGroup.unknown()) : ValidationRule<String>(group) {

    /**
     * Validate that the element matches [regex].
     *
     * @return The rule on which this method has been invoked.
     */
    fun matches(regex: Regex) =
        holds({ "Was supposed to match '$regex' but is '$it'" }) {
            it == null || regex.matches(it)
        }

    /**
     * Validate that the element is lowercase.
     *
     * @return The rule on which this method has been invoked.
     */
    fun lowercase() =
        holds({ "Was supposed to be lowercase but is '$it'" }) {
            it == null || it.all { c -> (c.isLetter() && c.isLowerCase()) || !c.isLetter() }
        }

    /**
     * Validate that the element is uppercase.
     *
     * @return The rule on which this method has been invoked.
     */
    fun uppercase() =
        holds({ "Was supposed to be uppercase but is '$it'" }) {
            it == null || it.all { c -> (c.isLetter() && c.isUpperCase()) || !c.isLetter() }
        }

    /**
     * Validate that the element consists only of letters.
     *
     * @return The rule on which this method has been invoked.
     */
    fun alpha() =
        holds({ "Was supposed to be alphabetic but is '$it'" }) {
            it == null || it.all { c -> c.isLetter() }
        }

    /**
     * Validate that the element consists only of numbers.
     *
     * @return The rule on which this method has been invoked.
     */
    fun numeric() =
        holds({ "Was supposed to be numeric but is '$it'" }) {
            it == null || it.all { c -> c.isDigit() }
        }

    /**
     * Validate that the element consists only of letters or numbers.
     *
     * @return The rule on which this method has been invoked.
     */
    fun alphanumeric() =
        holds({ "Was supposed to be alphanumeric but is '$it'" }) {
            it == null || it.all { c -> c.isLetterOrDigit() }
        }

    /**
     * Validate that the element is kebab case.
     *
     * @return The rule on which this method has been invoked.
     */
    fun kebabcase() =
        holds({ "Was supposed to be kebab case but is '$it'" }) {
            it == null || it.all { c -> c.isLetterOrDigit() || c == '-' }
        }

    /**
     * Validate that the element is snake case.
     *
     * @return The rule on which this method has been invoked.
     */
    fun snakecase() =
        holds({ "Was supposed to be snake case but is '$it'" }) {
            it == null || it.all { c -> c.isLetterOrDigit() || c == '_' }
        }

    /**
     * Validate that the element starts with a given [prefix].
     *
     * @param prefix The suffix the element should end with.
     * @param ignoreCase Ignore distinction between upper- and lowercase if true.
     *
     * @return The rule on which this method has been invoked.
     */
    fun startsWith(prefix: String, ignoreCase: Boolean = false) =
        holds({ "Was supposed to start with '$prefix' but is '$it'" }) {
            it == null || it.startsWith(prefix, ignoreCase)
        }

    /**
     * Validate that the element ends with a given [suffix].
     *
     * @param suffix The suffix the element should end with.
     * @param ignoreCase Ignore distinction between upper- and lowercase if true.
     *
     * @return The rule on which this method has been invoked.
     */
    fun endsWith(suffix: String, ignoreCase: Boolean = false) =
        holds({ "Was supposed to end with '$suffix' but is '$it'" }) {
            it == null || it.endsWith(suffix, ignoreCase)
        }
}
