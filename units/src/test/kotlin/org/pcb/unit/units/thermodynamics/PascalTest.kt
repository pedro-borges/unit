package org.pcb.unit.units.thermodynamics

import kotlin.test.Test
import kotlin.test.assertEquals
import org.pcb.unit.U2
import org.pcb.unit.units.PressureAmount
import org.pcb.unit.units.base.Kilogram.KILOGRAM
import org.pcb.unit.units.base.Meter.METER
import org.pcb.unit.units.base.Second.SECOND
import org.pcb.unit.units.mechanics.Newton
import org.pcb.unit.units.space.SquareMeter
import org.pcb.unit.units.thermodynamics.Pascal.PASCAL

class PascalTest
{
    private infix fun Pascal.equivalent(other: PressureAmount<*, *, *>) =
        assertEquals(this, Pascal(0) + other)

    @Test
    fun unit()
    {
        Pascal(2) equivalent PressureAmount(2, KILOGRAM to U2(METER, SECOND))
        Pascal(2) equivalent PressureAmount(2, PASCAL)
    }

    @Test
    fun newtonDivSquareMeter() =
        assertEquals(
            expected = Pascal(6 / 3),
            actual = Newton(6) / SquareMeter(3))
}