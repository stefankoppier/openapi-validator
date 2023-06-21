package io.github.stefankoppier.openapi.validator.core.internal

import org.assertj.core.api.Assertions.assertThat
import kotlin.test.Test

class RoseTreeTest {

    @Test
    fun `merge a node into another tree`() {
        val left = RoseTree(
            1,
            mutableListOf(
                RoseTree(2, mutableListOf()),
                RoseTree(3, mutableListOf()),
            ),
        )

        val right = RoseTree(1, mutableListOf(RoseTree(4, mutableListOf())))

        assertThat(left.merge(right))
            .isEqualTo(
                RoseTree(
                    1,
                    mutableListOf(
                        RoseTree(2, mutableListOf()),
                        RoseTree(3, mutableListOf()),
                        RoseTree(4, mutableListOf()),
                    ),
                ),
            )
    }

    @Test
    fun `merge a node of a node node into another tree`() {
        val left = RoseTree(
            1,
            mutableListOf(
                RoseTree(2, mutableListOf()),
                RoseTree(3, mutableListOf()),
            ),
        )

        val right = RoseTree(
            1,
            mutableListOf(
                RoseTree(
                    4,
                    mutableListOf(
                        RoseTree(
                            5,
                            mutableListOf(),
                        ),
                    ),
                ),
            ),
        )

        assertThat(left.merge(right))
            .isEqualTo(
                RoseTree(
                    1,
                    mutableListOf(
                        RoseTree(2, mutableListOf()),
                        RoseTree(3, mutableListOf()),
                        RoseTree(
                            4,
                            mutableListOf(
                                RoseTree(5, mutableListOf()),
                            ),
                        ),
                    ),
                ),
            )
    }
}