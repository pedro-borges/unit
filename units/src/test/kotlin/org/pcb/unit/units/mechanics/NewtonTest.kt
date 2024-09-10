package org.pcb.unit.units.mechanics

import kotlin.test.Test
import kotlin.test.assertEquals
import org.pcb.unit.U2
import org.pcb.unit.units.ForceAmount
import org.pcb.unit.units.base.Kilogram
import org.pcb.unit.units.base.Kilogram.KILOGRAM
import org.pcb.unit.units.base.Meter
import org.pcb.unit.units.base.Meter.METER
import org.pcb.unit.units.base.Second
import org.pcb.unit.units.base.Second.SECOND
import org.pcb.unit.units.mechanics.Newton.NEWTON

class NewtonTest
{
    private infix fun Newton.equivalent(other: ForceAmount<*, *, *>) =
        assertEquals(this, Newton(0) + other)

    @Test
    fun unit()
    {
        Newton(1) equivalent Kilogram(2) * (Meter(2) / Second(2)) / Second(2)
        Newton(2) equivalent ForceAmount(2, U2(KILOGRAM, METER) to SECOND)
        Newton(2) equivalent ForceAmount(2, NEWTON)
    }

    @Test
    fun jouleDivMeter() =
        assertEquals(
            expected = Newton(6 / 3),
            actual = Joule(6) / Meter(3))

    @Test
    fun wattDivMeterPerSecond() =
        assertEquals(
            expected = Newton(6 / 3),
            actual = Watt(6) / MeterPerSecond(3))
}