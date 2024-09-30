package org.pcb.unit.shape

import kotlin.test.Test
import kotlin.test.assertEquals
import org.junit.jupiter.api.Nested
import org.pcb.unit.base.Meter
import org.pcb.unit.config.Configuration.calculationMathContext
import org.pcb.unit.units.test.assertZero

class SquareTest
{
    @Nested
    inner class Positive
    {
        private val side = Meter(2)
        private val sut = Square(side)

        @Test
        fun narrowest()
        {
            assertEquals(side, sut.narrowest)
        }

        @Test
        fun widest()
        {
            assertEquals(side * 2.toBigDecimal().sqrt(calculationMathContext), sut.widest)
        }

        @Test
        fun perimeter()
        {
            assertEquals(8.toBigDecimal(), sut.perimeter.amount)
        }

        @Test
        fun area()
        {
            assertEquals(4.toBigDecimal(), sut.area.amount)
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