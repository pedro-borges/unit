package org.pcb.unit

import kotlin.test.Test
import kotlin.test.assertEquals
import org.pcb.unit.dimensions.Dimension
import org.pcb.unit.units.test.DISTANCE1
import org.pcb.unit.units.test.DISTANCE2
import org.pcb.unit.units.test.MASS1
import org.pcb.unit.units.test.MASS2
import org.pcb.unit.units.test.TEMPERATURE1
import org.pcb.unit.units.test.TEMPERATURE2
import org.pcb.unit.units.test.TIME1
import org.pcb.unit.units.test.TIME2

class CompositeUnitTest
{
    @Test
    fun plusSame()
    {
        val amount1 = Amount(0.toBigDecimal(), CompositeUnit(Dimension::class, mapOf(DISTANCE1 to 2,
                                                                                     MASS1 to 1,
                                                                                     TEMPERATURE1 to -1,
                                                                                     TIME1 to -2)))
        val amount2 = Amount(1.toBigDecimal(), CompositeUnit(Dimension::class, mapOf(DISTANCE1 to 2,
                                                                                     MASS1 to 1,
                                                                                     TEMPERATURE1 to -1,
                                                                                     TIME1 to -2)))

        assertEquals(expected = 1.toBigDecimal(),
                     actual = (amount1 + amount2).amount)
        assertEquals(expected = mapOf(DISTANCE1 to 2,
                                      MASS1 to 1,
                                      TEMPERATURE1 to -1,
                                      TIME1 to -2),
                     actual = (amount1 + amount2).unit.units)
    }

    @Test
    fun plusOther()
    {
        val amount1 = Amount(0.toBigDecimal(), CompositeUnit(Dimension::class, mapOf(DISTANCE2 to 2,
                                                                                     MASS2 to 1,
                                                                                     TEMPERATURE1 to -1,
                                                                                     TIME1 to -2)))
        val amount2 = Amount(1000000.toBigDecimal(), CompositeUnit(Dimension::class, mapOf(DISTANCE1 to 2,
                                                                                           MASS1 to 1,
                                                                                           TEMPERATURE2 to -1,
                                                                                           TIME2 to -2)))

        assertEquals(expected = 1.toBigDecimal(),
                     actual = (amount1 + amount2).amount)
        assertEquals(expected = mapOf(DISTANCE2 to 2,
                                      MASS2 to 1,
                                      TEMPERATURE1 to -1,
                                      TIME1 to -2),
                     actual = (amount1 + amount2).unit.units)
    }

    @Test
    fun minusSame()
    {
        val amount1 = Amount(1.toBigDecimal(), CompositeUnit(Dimension::class, mapOf(DISTANCE1 to 2,
                                                                                     MASS1 to 1,
                                                                                     TEMPERATURE1 to -1,
                                                                                     TIME1 to -2)))
        val amount2 = Amount(1.toBigDecimal(), CompositeUnit(Dimension::class, mapOf(DISTANCE1 to 2,
                                                                                     MASS1 to 1,
                                                                                     TEMPERATURE1 to -1,
                                                                                     TIME1 to -2)))

        assertEquals(expected = 0.toBigDecimal(),
                     actual = (amount1 - amount2).amount)
        assertEquals(expected = mapOf(DISTANCE1 to 2,
                                      MASS1 to 1,
                                      TEMPERATURE1 to -1,
                                      TIME1 to -2),
                     actual = (amount1 - amount2).unit.units)
    }

    @Test
    fun minusOther()
    {
        val amount1 = Amount(1.toBigDecimal(), CompositeUnit(Dimension::class, mapOf(DISTANCE2 to 2,
                                                                                     MASS2 to 1,
                                                                                     TEMPERATURE1 to -1,
                                                                                     TIME1 to -2)))
        val amount2 = Amount(1000000.toBigDecimal(), CompositeUnit(Dimension::class, mapOf(DISTANCE1 to 2,
                                                                                           MASS1 to 1,
                                                                                           TEMPERATURE2 to -1,
                                                                                           TIME2 to -2)))

        assertEquals(expected = 0.toBigDecimal(),
                     actual = (amount1 - amount2).amount)
        assertEquals(expected = mapOf(DISTANCE2 to 2,
                                      MASS2 to 1,
                                      TEMPERATURE1 to -1,
                                      TIME1 to -2),
                     actual = (amount1 - amount2).unit.units)
    }

    @Test
    fun divSelf()
    {
        val amount1 = Amount(2.toBigDecimal(), CompositeUnit(Dimension::class, mapOf(DISTANCE2 to 2,
                                                                                     MASS2 to 1,
                                                                                     TEMPERATURE1 to -1,
                                                                                     TIME1 to -2)))
        val amount2 = Amount(2000000.toBigDecimal(), CompositeUnit(Dimension::class, mapOf(DISTANCE1 to 2,
                                                                                           MASS1 to 1,
                                                                                           TEMPERATURE2 to -1,
                                                                                           TIME2 to -2)))

        assertEquals(expected = 1.toBigDecimal(),
                     actual = (amount1 / amount2).amount)
        assertEquals(expected = mapOf(),
                     actual = (amount1 / amount2).unit.units)
    }

    @Test
    fun timesSelf()
    {
        val amount1 = Amount(2.toBigDecimal(), CompositeUnit(Dimension::class, mapOf(DISTANCE2 to 2,
                                                                                     MASS2 to 1,
                                                                                     TEMPERATURE1 to -1,
                                                                                     TIME1 to -2)))
        val amount2 = Amount(2000000.toBigDecimal(), CompositeUnit(Dimension::class, mapOf(DISTANCE1 to 2,
                                                                                           MASS1 to 1,
                                                                                           TEMPERATURE2 to -1,
                                                                                           TIME2 to -2)))

        assertEquals(expected = 4.toBigDecimal(),
                     actual = (amount1 * amount2).amount)
        assertEquals(expected = mapOf(DISTANCE2 to 4,
                                      MASS2 to 2,
                                      TEMPERATURE1 to -2,
                                      TIME1 to -4),
                     actual = (amount1 * amount2).unit.units)
    }

    @Test
    fun divDistinct()
    {
        val amount1 = Amount(2.toBigDecimal(), CompositeUnit(Dimension::class, mapOf(DISTANCE2 to 2,
                                                                                     MASS2 to -1)))
        val amount2 = Amount(2.toBigDecimal(), CompositeUnit(Dimension::class, mapOf(TEMPERATURE2 to 1,
                                                                                     TIME2 to -2)))

        assertEquals(expected = 1.toBigDecimal(),
                     actual = (amount1 / amount2).amount)
        assertEquals(expected = mapOf(DISTANCE2 to 2,
                                      MASS2 to -1,
                                      TEMPERATURE2 to -1,
                                      TIME2 to 2),
                     actual = (amount1 / amount2).unit.units)
    }

    @Test
    fun timesDistinct()
    {
        val amount1 = Amount(2.toBigDecimal(), CompositeUnit(Dimension::class, mapOf(DISTANCE2 to 2,
                                                                                     MASS2 to -1)))
        val amount2 = Amount(2.toBigDecimal(), CompositeUnit(Dimension::class, mapOf(TEMPERATURE2 to 1,
                                                                                     TIME2 to -2)))

        assertEquals(expected = 4.toBigDecimal(),
                     actual = (amount1 * amount2).amount)
        assertEquals(expected = mapOf(DISTANCE2 to 2,
                                      MASS2 to -1,
                                      TEMPERATURE2 to 1,
                                      TIME2 to -2),
                     actual = (amount1 * amount2).unit.units)
    }
}