package org.pcb.unit.shape

import kotlin.test.Test
import org.junit.jupiter.api.Nested
import org.pcb.unit.base.Meter
import org.pcb.unit.units.test.assertEquals
import org.pcb.unit.units.test.assertZero

class RectangleTest
{
    @Nested
    inner class Positive
    {
        private val sut = Rectangle(length = Meter(3),
                                    width = Meter(4))

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
            assertEquals(14, sut.perimeter.amount)
        }

        @Test
        fun area()
        {
            assertEquals(12, sut.area.amount)
        }
    }

    @Nested
    inner class Spot
    {
        private val sut = Rectangle(length = Meter(0),
                                    width = Meter(0))

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