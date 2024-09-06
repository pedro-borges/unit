package org.pcb.unit.units.space

import kotlin.test.Test
import kotlin.test.assertEquals
import org.pcb.unit.units.AreaAmount
import org.pcb.unit.units.base.Meter
import org.pcb.unit.units.electromagnetism.Tesla
import org.pcb.unit.units.electromagnetism.Weber
import org.pcb.unit.units.space.SquareMeter.SQUARE_METER

class SquareMeterTest
{
    private infix fun SquareMeter.equivalent(other: AreaAmount<*>) =
        assertEquals(this, SquareMeter(0) + other)

    @Test
    fun scales()
    {
        SquareMeter(4) equivalent Meter(2) * Meter(2)
        val areaAmount = DeciMeter(20) * DeciMeter(20)
        SquareMeter(4) equivalent areaAmount
        SquareMeter(2) equivalent AreaAmount(2, SQUARE_METER)
    }

    @Test
    fun weberDivSquareMeter() =
        assertEquals(
            expected = SquareMeter(6 / 3),
            actual = Weber(6) / Tesla(3))
}
