package org.pcb.unit

import kotlin.test.Test
import kotlin.test.assertEquals
import org.pcb.unit.dimensions.Dimension
import org.pcb.unit.units.test.DISTANCE1
import org.pcb.unit.units.test.DISTANCE2
import org.pcb.unit.units.test.TIME1
import org.pcb.unit.units.test.TIME2

class ComparableAmountTest
{
    @Test
    fun testSmallerSame()
    {
        assertEquals(
            expected = -1,
            actual = Amount(amount = 99.toBigDecimal(),
                            unit = CompositeUnit(Dimension::class,
                                                 mapOf(DISTANCE1 to 1,
                                                       TIME1 to -2)))
                .compareTo(Amount(amount = 100.toBigDecimal(),
                                  CompositeUnit(Dimension::class,
                                                mapOf(DISTANCE1 to 1,
                                                      TIME1 to -2)))))
    }

    @Test
    fun testEqualSame()
    {
        assertEquals(
            expected = 0,
            actual = Amount(amount = 100.toBigDecimal(),
                            unit = CompositeUnit(Dimension::class,
                                                 mapOf(DISTANCE1 to 1,
                                                       TIME1 to -2)))
                .compareTo(Amount(amount = 100.toBigDecimal(),
                                  CompositeUnit(Dimension::class,
                                                mapOf(DISTANCE1 to 1,
                                                      TIME1 to -2)))))
    }

    @Test
    fun testGreaterSame()
    {
        assertEquals(
            expected = 1,
            actual = Amount(amount = 101.toBigDecimal(),
                            unit = CompositeUnit(Dimension::class,
                                                 mapOf(DISTANCE1 to 1,
                                                       TIME1 to -2)))
                .compareTo(Amount(amount = 100.toBigDecimal(),
                                  CompositeUnit(Dimension::class,
                                                mapOf(DISTANCE1 to 1,
                                                      TIME1 to -2)))))
    }

    @Test
    fun testSmallerOther()
    {
        assertEquals(
            expected = -1,
            actual = Amount(amount = 99.toBigDecimal(),
                            unit = CompositeUnit(Dimension::class,
                                                 mapOf(DISTANCE2 to 1,
                                                       TIME1 to -2)))
                .compareTo(Amount(amount = 100000.toBigDecimal(),
                                  unit = CompositeUnit(Dimension::class,
                                                       mapOf(DISTANCE1 to 1,
                                                             TIME2 to -2)))))
    }

    @Test
    fun testEqualOther()
    {
        assertEquals(
            expected = 0,
            actual = Amount(amount = 100.toBigDecimal(),
                            unit = CompositeUnit(Dimension::class,
                                                 mapOf(DISTANCE2 to 1,
                                                       TIME1 to -2)))
                .compareTo(Amount(amount = 100000.toBigDecimal(),
                                  unit = CompositeUnit(Dimension::class,
                                                       mapOf(DISTANCE1 to 1,
                                                             TIME2 to -2)))))
    }

    @Test
    fun testGreaterOther()
    {
        assertEquals(
            expected = 1,
            actual = Amount(amount = 101.toBigDecimal(),
                            unit = CompositeUnit(Dimension::class,
                                                 mapOf(DISTANCE2 to 1,
                                                       TIME1 to -2)))
                .compareTo(Amount(amount = 100000.toBigDecimal(),
                                  unit = CompositeUnit(Dimension::class,
                                                       mapOf(DISTANCE1 to 1,
                                                             TIME2 to -2)))))
    }
}