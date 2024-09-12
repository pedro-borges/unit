package org.pcb.unit.amounts.mechanics

import kotlin.test.Test
import kotlin.test.assertEquals
import org.pcb.unit.Magnitude.KILO
import org.pcb.unit.U2
import org.pcb.unit.amounts.ForceAmount
import org.pcb.unit.amounts.base.Gram
import org.pcb.unit.amounts.base.Gram.GRAM
import org.pcb.unit.amounts.base.Meter
import org.pcb.unit.amounts.base.Meter.METER
import org.pcb.unit.amounts.base.Second
import org.pcb.unit.amounts.base.Second.SECOND
import org.pcb.unit.amounts.mechanics.Newton.NEWTON
import org.pcb.unit.amounts.space.SquareMeter
import org.pcb.unit.amounts.thermodynamics.Pascal
import org.pcb.unit.kilo

class NewtonTest
{
    private infix fun Newton.equivalent(other: ForceAmount<*, *, *>) =
        assertEquals(this, Newton(0) + other)

    @Test
    fun unit()
    {
        Newton(1) equivalent Gram(2, KILO) * (Meter(2) / Second(2)) / Second(2)
        Newton(2) equivalent ForceAmount(2, U2(kilo(GRAM), METER) to SECOND)
        Newton(2) equivalent ForceAmount(2, NEWTON)
    }

    @Test
    fun jouleDivMeter() =
        assertEquals(
            expected = Newton(6 / 3),
            actual = Joule(6) / Meter(3))

    @Test
    fun pascalTimesSquareMeter() =
        assertEquals(
            expected = Newton(2 * 3),
            actual = Pascal(2) * SquareMeter(3))

    @Test
    fun squareMeterTimesPascal() =
        assertEquals(
            expected = Newton(2 * 3),
            actual = SquareMeter(2) * Pascal(3))

    @Test
    fun wattDivMeterPerSecond() =
        assertEquals(
            expected = Newton(6 / 3),
            actual = Watt(6) / MeterPerSecond(3))
}