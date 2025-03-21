package org.pcb.unit.shape

import kotlin.test.Test
import org.junit.jupiter.api.Nested
import org.pcb.unit.base.Meter
import org.pcb.unit.units.test.assertEquals
import org.pcb.unit.units.test.assertZero

class TriangleTest
{
    @Nested
    inner class Positive
    {
        private val sut = Triangle(a = Meter(3),
                                   b = Meter(4),
                                   c = Meter(5))

        @Test
        fun narrowest()
        {
            assertEquals(3, sut.narrowest.amount)
        }

        @Test
        fun widest()
        {
            assertEquals(5, sut.widest.amount)
        }

        @Test
        fun perimeter()
        {
            assertEquals(12, sut.perimeter.amount)
        }

        @Test
        fun area()
        {
            assertEquals(6, sut.area.amount)
        }
    }

    @Nested
    inner class Spot
    {
        private val sut = Triangle(Meter(0))

        @Test
        fun narrowest()
        {
            assertZero(sut.narrowest)
        }

        @Test
        fun widest()
        {
            assertZero(sut.widest)
        }

        @Test
        fun perimeter()
        {
            assertZero(sut.perimeter)
        }

        @Test
        fun area()
        {
            assertZero(sut.area)
        }
    }
}