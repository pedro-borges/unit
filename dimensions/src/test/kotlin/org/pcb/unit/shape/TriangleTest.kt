package org.pcb.unit.shape

import kotlin.test.Test
import kotlin.test.assertEquals
import org.junit.jupiter.api.Nested
import org.pcb.unit.base.Meter
import org.pcb.unit.units.test.assertZero

class TriangleTest
{
    @Nested
    inner class Positive
    {
        private val a = Meter(3)
        private val b = Meter(4)
        private val c = Meter(5)
        private val sut = Triangle(a, b, c)

        @Test
        fun narrowest()
        {
            assertEquals(a, sut.narrowest)
        }

        @Test
        fun widest()
        {
            assertEquals(c, sut.widest)
        }

        @Test
        fun perimeter()
        {
            assertEquals(12.toBigDecimal(), sut.perimeter.amount)
        }

        @Test
        fun area()
        {
            assertEquals(6.toBigDecimal(), sut.area.amount)
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