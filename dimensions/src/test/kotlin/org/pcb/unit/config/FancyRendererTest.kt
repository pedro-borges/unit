package org.pcb.unit.config

import kotlin.test.Test
import kotlin.test.assertEquals
import org.pcb.unit.U2
import org.pcb.unit.amounts.ElectricResistanceAmount
import org.pcb.unit.units.test.DISTANCE1
import org.pcb.unit.units.test.ELECTRICCURRENT1
import org.pcb.unit.units.test.MASS1
import org.pcb.unit.units.test.TIME1

class FancyRendererTest
{

    @Test
    fun renderAmount()
    {
        assertEquals(
            expected = "10 \u001B[36m\u001B[0mL\u001B[31m²\u001B[0m﹒\u001B[36m\u001B[0mM\u001B[31m\u001B[0m﹒\u001B[36m\u001B[0mI\u001B[31m⁻²\u001B[0m﹒\u001B[36m\u001B[0mT\u001B[31m⁻³\u001B[0m",
            actual = FancyRenderer().render(ElectricResistanceAmount(amount = 10,
                                                                     unit = U2(DISTANCE1, MASS1) to U2(ELECTRICCURRENT1, TIME1))))
    }

    @Test
    fun renderUnitPowers()
    {
        assertEquals(
            expected = "\u001B[36m\u001B[0mL\u001B[31m²\u001B[0m﹒\u001B[36m\u001B[0mM\u001B[31m\u001B[0m﹒\u001B[36m\u001B[0mI\u001B[31m⁻²\u001B[0m﹒\u001B[36m\u001B[0mT\u001B[31m⁻³\u001B[0m",
            actual = FancyRenderer().render(mapOf(MASS1 to 1,
                                                  DISTANCE1 to 2,
                                                  TIME1 to -3,
                                                  ELECTRICCURRENT1 to -2)))
    }

    @Test
    fun renderUnit()
    {
        assertEquals(
            expected = "\u001B[36m\u001B[0mI",
            actual = FancyRenderer().render(ELECTRICCURRENT1))
    }
}
