package org.pcb.unit.config

import kotlin.test.Test
import kotlin.test.assertEquals
import org.pcb.unit.U2
import org.pcb.unit.Unit
import org.pcb.unit.dimension.Dimension
import org.pcb.unit.units.ElectricResistanceAmount
import org.pcb.unit.units.test.DISTANCE1
import org.pcb.unit.units.test.ELECTRICCURRENT1
import org.pcb.unit.units.test.MASS1
import org.pcb.unit.units.test.TIME1

class PlainRendererTest
{
    val sut = PlainRenderer()

    @Test
    fun renderAmount()
    {
        assertEquals(
            expected = "10 D² M EC⁻² T⁻³",
            actual = sut.render(ElectricResistanceAmount(amount = 10,
                                                         unit = U2(MASS1, DISTANCE1) to U2(TIME1, ELECTRICCURRENT1))))
    }

    @Test
    fun renderUnitPowers()
    {
        assertEquals(
            expected = "D² M EC⁻² T⁻³",
            actual = sut.render(mapOf<Unit<out Dimension>, Int>(MASS1 to 1,
                                                                DISTANCE1 to 2,
                                                                TIME1 to -3,
                                                                ELECTRICCURRENT1 to -2)))
    }

    @Test
    fun renderUnit()
    {
        assertEquals(
            expected = "EC",
            actual = sut.render(ELECTRICCURRENT1))
    }
}