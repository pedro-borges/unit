package org.pcb.unit.amounts.mechanics

import kotlin.test.Test
import kotlin.test.assertEquals
import org.pcb.unit.amounts.base.Meter
import org.pcb.unit.amounts.base.Meter.METER
import org.pcb.unit.amounts.base.Second
import org.pcb.unit.amounts.base.Second.SECOND
import org.pcb.unit.amounts.mechanics.MeterPerSecond.MPS
import org.pcb.unit.units.SpeedAmount

class MeterPerSecondTest
{
    private infix fun MeterPerSecond.equivalent(other: SpeedAmount<*, *>) =
        assertEquals(this, MeterPerSecond(0) + other)

    @Test
    fun unit()
    {
        MeterPerSecond(1) equivalent Meter(2) / Second(2)
        MeterPerSecond(2) equivalent SpeedAmount(2, METER to SECOND)
        MeterPerSecond(2) equivalent SpeedAmount(2, MPS)
    }

    @Test
    fun wattDivNewton() =
        assertEquals(
            expected = MeterPerSecond(6 / 3),
            actual = Watt(6) / Newton(3))
}