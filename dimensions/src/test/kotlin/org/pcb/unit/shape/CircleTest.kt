package org.pcb.unit.shape

import kotlin.test.Test
import org.junit.jupiter.api.Nested
import org.pcb.unit.base.Meter
import org.pcb.unit.units.test.assertEquals
import org.pcb.unit.units.test.assertZero

class CircleTest
{
    @Nested
    inner class Positive
    {
        private val sut = Circle(Meter(2))

        @Test
        fun narrowest()
        {
            assertEquals(4, sut.narrowest.amount)
        }

        @Test
        fun widest()
        {
            assertEquals(4, sut.widest.amount)
        }

        @Test
        fun perimeter()
        {
            assertEquals("12.566370614359172953850573533118011536788677597500423283899778369231".toBigDecimal(), sut.perimeter.amount)
        }

        @Test
        fun area()
        {
            assertEquals("12.566370614359172953850573533118011536788677597500423283899778369231".toBigDecimal(), sut.area.amount)
        }
    }

    @Nested
    inner class Spot
    {
        private val sut = Circle(Meter(0))

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