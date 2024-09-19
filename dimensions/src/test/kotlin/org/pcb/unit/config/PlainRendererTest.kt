package org.pcb.unit.config

import kotlin.test.Test
import kotlin.test.assertEquals
import org.pcb.unit.SIScale.KILO
import org.pcb.unit.U2
import org.pcb.unit.amounts.ElectricResistanceAmount
import org.pcb.unit.units.test.DISTANCE1
import org.pcb.unit.units.test.ELECTRICCURRENT
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
            expected = "10 D²M¹EC⁻²T⁻³",
            actual = sut.render(ElectricResistanceAmount(amount = 10,
                                                         unit = U2(DISTANCE1, MASS1) to
                                                                 U2(ELECTRICCURRENT1, TIME1))))
    }

    @Test
    fun renderUnitPowers()
    {
        assertEquals(
            expected = "D²M¹EC⁻²T⁻³",
            actual = sut.render(mapOf(MASS1 to 1,
                                      DISTANCE1 to 2,
                                      TIME1 to -3,
                                      ELECTRICCURRENT1 to -2)))
    }

    @Test
    fun renderUnit()
    {
        assertEquals(
            expected = "kEC",
            actual = sut.render(ELECTRICCURRENT(KILO)))
    }
}
