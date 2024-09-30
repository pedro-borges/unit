package org.pcb.unit.shape

import kotlin.test.Test
import kotlin.test.assertEquals
import org.junit.jupiter.api.Nested
import org.pcb.unit.base.Meter
import org.pcb.unit.units.test.assertZero

class CircleTest
{
    @Nested
    inner class Positive
    {
        private val radius = Meter(2)
        private val sut = Circle(radius)

        @Test
        fun narrowest()
        {
            assertEquals(radius * 2, sut.narrowest)
        }

        @Test
        fun widest()
        {
            assertEquals(radius * 2, sut.widest)
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