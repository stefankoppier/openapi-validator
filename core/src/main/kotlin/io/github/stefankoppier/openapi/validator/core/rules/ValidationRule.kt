package io.github.stefankoppier.openapi.validator.core.rules

import io.github.stefankoppier.openapi.validator.core.ValidationFailure
import io.github.stefankoppier.openapi.validator.core.ValidationResult
import java.time.LocalDate

abstract class ValidationRule<T : Any> protected constructor(protected val group: RuleGroup = RuleGroup.unknown()) {

    private val rules: MutableList<Pair<(T?) -> Boolean, (T?) -> ValidationResult>> = mutableListOf()

    /**
     * Execute the given rule(s) on the [fixture].
     *
     * @param fixture The object to validate the rule(s) against.
     *
     * @return The [ValidationResult] possibly containing failures.
     */
    fun validate(fixture: T?) =
        rules.fold(unit()(fixture)) { acc, rule ->
            acc merge if (rule.first(fixture)) rule.second.invoke(fixture) else unit()(fixture)
        }

    /**
     * Validate [rule] only if [precondition] evaluates to true.
     *
     * @param precondition The predicate which should hold for [rule] to be validated.
     * @param rule The rule which should hold when [precondition] evaluates to true.
     *
     * @return The original rule on which this method has been invoked.
     */
    fun <R : ValidationRule<T>> R.given(precondition: (T?) -> Boolean, rule: R.() -> R): R =
        rule().apply {
            val last = rules.last()
            rules[rules.size - 1] = last.copy(first = { precondition(it) && last.first(it) })
        }

    /**
     * Validate that [predicate] evaluates to true.
     *
     * @param message The error message to display should [predicate] not be valid.
     * @param predicate The predicate which should be validated.
     *
     * @return The original rule on which this method has been invoked.
     */
    fun <R : ValidationRule<T>> R.holds(message: (T?) -> String = { "Was supposed to hold for '$it' but did not" }, predicate: (T?) -> Boolean) =
        apply {
            add {
                ValidationResult.condition(ValidationFailure(group, message(it))) { predicate(it) }
            }
        }

    /**
     * Validate [rule] only if [date] has been in the past.
     *
     * @param date The date after which the [rule] should be valid.
     * @param rule The rule which should hold after [date].
     *
     * @return The original rule on which this method has been invoked.
     */
    fun <R : ValidationRule<T>> R.since(date: LocalDate, rule: R.() -> R): R =
        given({ LocalDate.now().isAfter(date) }, rule)

    /**
     * Validate [rule] only if the evaluated result of [value] is not null.
     *
     * @param value The value which, if not null, determines if [rule] should be valid.
     * @param rule The rule which should hold after [date].
     *
     * @return The original rule on which this method has been invoked.
     */
    fun <V, R : ValidationRule<T>> R.optional(value: (T?) -> (V?), rule: R.() -> R) =
        given({ value(it) != null }, rule)

    /**
     * Validate that the element is set.
     *
     * @return The original rule on which this method has been invoked.
     */
    fun <R : ValidationRule<T>> R.required() =
        holds({ "Was required but is not given" }) {
            it != null
        }

    /**
     * Validate that the element should be equal to [value] *given that it is set*.
     *
     * @return The original rule on which this method has been invoked.
     */
    fun <R : ValidationRule<T>> R.exactly(value: T) =
        holds({ "Was supposed to be '$value' but is '$it'" }) { it == value }

    protected fun add(rule: (T?) -> ValidationResult): ValidationRule<T> {
        rules.add({ _: T? -> true } to { fixture -> rule(fixture) })
        return this
    }

    private fun unit(): (T?) -> ValidationResult = { ValidationResult.success() }
}
