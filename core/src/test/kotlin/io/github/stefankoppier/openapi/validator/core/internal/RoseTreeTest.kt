package io.github.stefankoppier.openapi.validator.core.internal

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.assertThrows
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

    @Test
    fun `merge two nodes with different roots`() {
        val left = RoseTree(1)
        val right = RoseTree(2)

        assertThrows<IllegalArgumentException> { left.merge(right) }
    }

    @Test
    fun `fold simple tree`() {
        val tree = RoseTree(
            1,
            mutableListOf(
                RoseTree(2, mutableListOf()),
                RoseTree(3, mutableListOf()),
            ),
        )

        assertThat(tree.fold(0) { _, acc, it -> acc + it })
            .isEqualTo(1 + 2 + 3)
    }

    @Test
    fun `fold is left-associative`() {
        val tree = RoseTree(
            1,
            mutableListOf(
                RoseTree(2, mutableListOf()),
                RoseTree(3, mutableListOf()),
            ),
        )

        assertThat(tree.fold(0) { _, acc, it -> acc - it })
            .isEqualTo(((0 - 1) - 2) - 3)
    }
}
