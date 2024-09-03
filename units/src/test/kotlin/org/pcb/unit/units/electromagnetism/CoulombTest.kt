package org.pcb.unit.units.electromagnetism

import kotlin.test.Test
import kotlin.test.assertEquals
import org.pcb.unit.units.ElectricChargeAmount
import org.pcb.unit.units.base.Ampere
import org.pcb.unit.units.base.Second

class CoulombTest
{
    @Test
    fun unit()
    {
        Coulomb(1) equivalent Ampere(1) * Second(1)
    }

    private infix fun Coulomb.equivalent(other: ElectricChargeAmount<*, *>) =
        assertEquals(this, Coulomb(0) + other)
}