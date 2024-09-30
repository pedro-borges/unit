package org.pcb.unit.solid

import kotlin.test.Test
import kotlin.test.assertEquals
import org.junit.jupiter.api.Nested
import org.pcb.unit.base.Meter
import org.pcb.unit.config.Configuration.calculationMathContext
import org.pcb.unit.units.test.assertZero

class CubeTest
{
    @Nested
    inner class Positive
    {
        private val side = Meter(2)
        private val sut = Cube(side)

        @Test
        fun narrowest()
        {
            assertEquals(side, sut.narrowest)
        }

        @Test
        fun widest()
        {
            assertEquals(side * 3.toBigDecimal().sqrt(calculationMathContext), sut.widest)
        }

        @Test
        fun area()
        {
            assertEquals(24.toBigDecimal(), sut.area.amount)
        }

        @Test
        fun volume()
        {
            assertEquals(8.toBigDecimal(), sut.volume.amount)
        }
    }

    @Nested
    inner class Spot
    {
        private val sut = Cube(Meter(0))

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
        fun area()
        {
            assertZero(sut.area)
        }

        @Test
        fun volume()
        {
            assertZero(sut.volume)
        }
    }
}