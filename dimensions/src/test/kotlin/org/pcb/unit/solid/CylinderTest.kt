package org.pcb.unit.solid

import kotlin.test.Test
import org.junit.jupiter.api.Nested
import org.pcb.unit.base.Meter
import org.pcb.unit.shape.Circle
import org.pcb.unit.units.test.assertEquals
import org.pcb.unit.units.test.assertZero

class CylinderTest
{
    @Nested
    inner class Positive
    {
        private val sut = Cylinder(radius = Meter(1.5),
                                   height = Meter(4))

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
        fun area()
        {
            assertEquals("51.8362787842315884346336158241117975892532950896892460460865857730792".toBigDecimal(), sut.area.amount)
        }

        @Test
        fun volume()
        {
            assertEquals("28.27433388230813914616379044951552595777452459437595238877450133077".toBigDecimal(), sut.volume.amount)
        }
    }

    @Nested
    inner class Spot
    {
        private val sut = Cylinder(Circle(Meter(0)), Meter(0))

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