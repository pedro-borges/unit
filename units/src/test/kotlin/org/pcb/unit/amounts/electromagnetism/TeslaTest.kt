package org.pcb.unit.amounts.electromagnetism

import kotlin.test.Test
import kotlin.test.assertEquals
import org.pcb.unit.U2
import org.pcb.unit.amounts.MagneticFluxDensityAmount
import org.pcb.unit.amounts.base.Ampere.AMPERE
import org.pcb.unit.amounts.base.Gram.GRAM
import org.pcb.unit.amounts.base.Second.SECOND
import org.pcb.unit.amounts.electromagnetism.Tesla.TESLA
import org.pcb.unit.amounts.space.SquareMeter
import org.pcb.unit.kilo

class TeslaTest
{
    private infix fun Tesla.equivalent(other: MagneticFluxDensityAmount<*, *, *>) =
        assertEquals(this, Tesla(0) + other)

    @Test
    fun unit()
    {
        Tesla(2) equivalent MagneticFluxDensityAmount(2, kilo(GRAM) to U2(SECOND, AMPERE))
        Tesla(2) equivalent MagneticFluxDensityAmount(2, TESLA)
    }

    @Test
    fun weberDivSquareMeter() =
        assertEquals(
            expected = Tesla(6 / 3),
            actual = Weber(6) / SquareMeter(3))
}