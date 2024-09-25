package org.pcb.unit.amounts.scalar

import kotlin.test.Test
import kotlin.test.assertEquals
import org.pcb.unit.amounts.ScalarAmount
import org.pcb.unit.amounts.electromagnetism.Ohm
import org.pcb.unit.amounts.electromagnetism.Siemens
import org.pcb.unit.amounts.frequency.Hertz
import org.pcb.unit.amounts.mechanics.MeterPerSecond
import org.pcb.unit.amounts.mechanics.SecondPerMeter
import org.pcb.unit.base.Numeric
import org.pcb.unit.base.Numeric.NUMERIC
import org.pcb.unit.base.Second

class NumericTest
{
    private infix fun Numeric.equivalent(other: ScalarAmount<*>) =
        assertEquals(this, other)

    @Test
    fun unit()
    {
        Numeric(2) equivalent ScalarAmount(2, NUMERIC)
        assertEquals(Numeric(2), Numeric(1) + Numeric(1))
    }

    @Test
    fun hertzTimesSecond()
    {
        assertEquals(
            expected = ScalarAmount(2 * 3, NUMERIC),
            actual = Hertz(2) * Second(3))
    }

    @Test
    fun meterPerSecondTimesSecondPerMeter()
    {
        assertEquals(
            expected = ScalarAmount(2 * 3, NUMERIC),
            actual = MeterPerSecond(2) * SecondPerMeter(3))
    }

    @Test
    fun ohmTimesSiemens() =
        assertEquals(
            expected = ScalarAmount(2 * 3, NUMERIC),
            actual = Ohm(2) * Siemens(3))

    @Test
    fun secondTimesHertz()
    {
        assertEquals(
            expected = ScalarAmount(2 * 3, NUMERIC),
            actual = Second(2) * Hertz(3))
    }

    @Test
    fun secondPerMeterTimesMeterPerSecond() =
        assertEquals(
            expected = ScalarAmount(2 * 3, NUMERIC),
            actual = SecondPerMeter(2) * MeterPerSecond(3))

    @Test
    fun siemensTimesOhm()
    {
        assertEquals(
            expected = ScalarAmount(2 * 3, NUMERIC),
            actual = Siemens(2) * Ohm(3))
    }
}