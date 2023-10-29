package io.github.stefankoppier.openapi.validator.core.rules.openapi

import io.github.stefankoppier.openapi.validator.core.assertThatResult
import kotlin.test.Test

class OperationPathRuleTest {

    @Test
    fun `segments selects correct segments`() {
        val rule = OperationPathRule()
            .segments { all { holds { it in arrayOf("v1", "resource", "{resource_name}") } } }

        assertThatResult(rule.validate(fixture))
            .isSuccess()
    }

    @Test
    fun `fixed selects correct segments`() {
        val rule = OperationPathRule()
            .fixed { all { holds { it in arrayOf("v1", "resource") } } }

        assertThatResult(rule.validate(fixture))
            .isSuccess()
    }

    @Test
    fun `templates selects correct segments`() {
        val rule = OperationPathRule()
            .templates { all { exactly("{resource_name}") } }

        assertThatResult(rule.validate(fixture))
            .isSuccess()
    }

    @Test
    fun `first selects correct segment`() {
        val rule = OperationPathRule()
            .segments { first { exactly("v1") } }

        assertThatResult(rule.validate(fixture))
            .isSuccess()
    }

    @Test
    fun `last selects correct segment`() {
        val rule = OperationPathRule()
            .segments { last { exactly("{resource_name}") } }

        assertThatResult(rule.validate(fixture))
            .isSuccess()
    }

    companion object {
        val fixture = "v1/resource/{resource_name}"
    }
}
