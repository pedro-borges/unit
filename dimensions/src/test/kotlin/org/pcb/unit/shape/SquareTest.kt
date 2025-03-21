package org.pcb.unit.shape

import kotlin.test.Test
import org.junit.jupiter.api.Nested
import org.pcb.unit.base.Meter
import org.pcb.unit.units.test.assertEquals
import org.pcb.unit.units.test.assertZero

class SquareTest
{
    @Nested
    inner class Positive
    {
        private val sut = Square(Meter(2))

        @Test
        fun narrowest()
        {
            assertEquals(2, sut.narrowest.amount)
        }

        @Test
        fun widest()
        {
            assertEquals("2.8284271247461900976033774484193961571393437507538961463533594759815".toBigDecimal(), sut.widest.amount)
        }

        @Test
        fun perimeter()
        {
            assertEquals(8, sut.perimeter.amount)
        }

        @Test
        fun area()
        {
            assertEquals(4, sut.area.amount)
        }
    }

    @Nested
    inner class Spot
    {
        private val sut = Square(Meter(0))

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