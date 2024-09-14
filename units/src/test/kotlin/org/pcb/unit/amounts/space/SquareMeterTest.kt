package org.pcb.unit.amounts.space

import kotlin.test.Test
import kotlin.test.assertEquals
import org.pcb.unit.Magnitude.DECI
import org.pcb.unit.Magnitude.HECTO
import org.pcb.unit.amounts.AreaAmount
import org.pcb.unit.amounts.base.Candela
import org.pcb.unit.amounts.base.Meter
import org.pcb.unit.amounts.base.Meter.METER
import org.pcb.unit.amounts.electromagnetism.Tesla
import org.pcb.unit.amounts.electromagnetism.Weber
import org.pcb.unit.amounts.mechanics.Newton
import org.pcb.unit.amounts.optics.Lumen
import org.pcb.unit.amounts.optics.Lux
import org.pcb.unit.amounts.optics.Nit
import org.pcb.unit.amounts.space.SquareMeter.SQM
import org.pcb.unit.amounts.thermodynamics.Pascal

class SquareMeterTest
{
    private infix fun SquareMeter.equivalent(other: AreaAmount<*>) =
        assertEquals(this, SquareMeter(0) + other)

    @Test
    fun scales()
    {
        SquareMeter(4) equivalent Meter(2) * Meter(2)
        SquareMeter(4) equivalent Meter(20, DECI) * Meter(20, DECI)
        SquareMeter(2) equivalent AreaAmount(2, METER)
        SquareMeter(2) equivalent AreaAmount(2, SQM)

        SquareMeter(1, HECTO) equivalent SquareMeter(100)
        SquareMeter(1, HECTO) equivalent SquareMeter(1, HECTO)
    }

    @Test
    fun candelaDivNit() =
        assertEquals(
            expected = SquareMeter(6 / 3),
            actual = Candela(6) / Nit(3))

    @Test
    fun lumenDivLux() =
        assertEquals(
            expected = SquareMeter(6 / 3),
            actual = Lumen(6) / Lux(3))

    @Test
    fun newtonDivPascal() =
        assertEquals(
            expected = SquareMeter(6 / 3),
            actual = Newton(6) / Pascal(3))

    @Test
    fun weberDivTesla() =
        assertEquals(
            expected = SquareMeter(6 / 3),
            actual = Weber(6) / Tesla(3))
}
