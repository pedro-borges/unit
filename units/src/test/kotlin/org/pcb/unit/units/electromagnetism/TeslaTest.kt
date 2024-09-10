package org.pcb.unit.units.electromagnetism

import kotlin.test.Test
import kotlin.test.assertEquals
import org.pcb.unit.U2
import org.pcb.unit.units.MagneticFluxDensityAmount
import org.pcb.unit.units.base.Ampere.AMPERE
import org.pcb.unit.units.base.Kilogram.KILOGRAM
import org.pcb.unit.units.base.Second.SECOND
import org.pcb.unit.units.electromagnetism.Tesla.TESLA
import org.pcb.unit.units.space.SquareMeter

class TeslaTest
{
    private infix fun Tesla.equivalent(other: MagneticFluxDensityAmount<*, *, *>) =
        assertEquals(this, Tesla(0) + other)

    @Test
    fun unit()
    {
        Tesla(2) equivalent MagneticFluxDensityAmount(2, KILOGRAM to U2(SECOND, AMPERE))
        Tesla(2) equivalent MagneticFluxDensityAmount(2, TESLA)
    }

    @Test
    fun weberDivSquareMeter() =
        assertEquals(
            expected = Tesla(6 / 3),
            actual = Weber(6) / SquareMeter(3))
}