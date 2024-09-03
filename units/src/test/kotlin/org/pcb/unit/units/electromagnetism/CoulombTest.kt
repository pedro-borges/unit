package org.pcb.unit.units.electromagnetism

import kotlin.test.Test
import kotlin.test.assertEquals
import org.pcb.unit.Unit
import org.pcb.unit.dimension.ElectricCurrent
import org.pcb.unit.units.ElectricChargeAmount
import org.pcb.unit.units.base.Ampere
import org.pcb.unit.units.base.Second
import org.pcb.unit.units.base.Second.SECOND
import org.pcb.unit.units.electromagnetism.Coulomb.COULOMB

class CoulombTest
{
    @Test
    fun unit()
    {
        Coulomb(2 * 2) equivalent Ampere(2) * Second(2)
        Coulomb(2) equivalent ElectricChargeAmount(2, COULOMB)
    }

    private infix fun Coulomb.equivalent(other: ElectricChargeAmount<*, *>) =
        assertEquals<ElectricChargeAmount<out Unit<ElectricCurrent>, SECOND>>(this, Coulomb(0) + other)
}