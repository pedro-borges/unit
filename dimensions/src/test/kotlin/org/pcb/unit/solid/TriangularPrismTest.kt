package org.pcb.unit.solid

import kotlin.test.Test
import org.junit.jupiter.api.Nested
import org.pcb.unit.base.Meter
import org.pcb.unit.shape.Triangle
import org.pcb.unit.units.test.assertEquals
import org.pcb.unit.units.test.assertZero

class TriangularPrismTest
{
    @Nested
    inner class Positive
    {
        private val sut = TriangularPrism(a = Meter(3),
                                          b = Meter(4),
                                          c = Meter(5),
                                          height = Meter(12))

        @Test
        fun narrowest()
        {
            assertEquals(3, sut.narrowest.amount)
        }

        @Test
        fun widest()
        {
            assertEquals(13, sut.widest.amount)
        }

        @Test
        fun area()
        {
            assertEquals(156, sut.area.amount)
        }

        @Test
        fun volume()
        {
            assertEquals(72, sut.volume.amount)
        }
    }

    @Nested
    inner class Spot
    {
        private val sut = TriangularPrism(Triangle(Meter(0)),
                                          Meter(0))

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