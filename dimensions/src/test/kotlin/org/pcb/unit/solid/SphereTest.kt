package org.pcb.unit.solid

import kotlin.test.Test
import org.junit.jupiter.api.Nested
import org.pcb.unit.base.Meter
import org.pcb.unit.units.test.assertEquals
import org.pcb.unit.units.test.assertZero

class SphereTest
{
    @Nested
    inner class Positive
    {
        private val radius = Meter(2)
        private val sut = Sphere(radius)

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
        fun area()
        {
            assertEquals("50.265482457436691815402294132472046147154710390001693135599113476924".toBigDecimal(), sut.area.amount)
        }

        @Test
        fun volume()
        {
            assertEquals("33.510321638291127876934862754981364098103140260001128757066075651283".toBigDecimal(), sut.volume.amount)
        }
    }

    @Nested
    inner class Spot
    {
        private val sut = Sphere(Meter(0))

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