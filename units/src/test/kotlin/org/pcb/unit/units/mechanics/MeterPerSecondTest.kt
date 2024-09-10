package org.pcb.unit.units.mechanics

import kotlin.test.Test
import kotlin.test.assertEquals
import org.pcb.unit.units.SpeedAmount
import org.pcb.unit.units.base.Meter
import org.pcb.unit.units.base.Second
import org.pcb.unit.units.mechanics.MeterPerSecond.MPS

class MeterPerSecondTest
{
    private infix fun MeterPerSecond.equivalent(other: SpeedAmount<*, *>) =
        assertEquals(this, MeterPerSecond(0) + other)

    @Test
    fun unit()
    {
        MeterPerSecond(1) equivalent Meter(2) / Second(2)
        MeterPerSecond(2) equivalent SpeedAmount(2, MPS)
    }

    @Test
    fun wattDivNewton() =
        assertEquals(
            expected = MeterPerSecond(6 / 3),
            actual = Watt(6) / Newton(3))
}