package org.pcb.unit.solid

import kotlin.test.Test
import org.junit.jupiter.api.Nested
import org.pcb.unit.base.Meter
import org.pcb.unit.units.test.assertEquals
import org.pcb.unit.units.test.assertZero

class CubeTest
{
    @Nested
    inner class Positive
    {
        private val sut = Cube(Meter(2))

        @Test
        fun narrowest()
        {
            assertEquals(2, sut.narrowest.amount)
        }

        @Test
        fun widest()
        {
            assertEquals("3.4641016151377545870548926830117447338856105076207612561116139589039".toBigDecimal(), sut.widest.amount)
        }

        @Test
        fun area()
        {
            assertEquals(24, sut.area.amount)
        }

        @Test
        fun volume()
        {
            assertEquals(8, sut.volume.amount)
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