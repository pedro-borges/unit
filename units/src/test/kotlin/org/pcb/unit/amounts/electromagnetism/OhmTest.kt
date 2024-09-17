package org.pcb.unit.amounts.electromagnetism

import kotlin.test.Test
import kotlin.test.assertEquals
import org.pcb.unit.SIScale.KILO
import org.pcb.unit.U2
import org.pcb.unit.amounts.ElectricResistanceAmount
import org.pcb.unit.amounts.base.Ampere
import org.pcb.unit.amounts.base.Ampere.AMPERE
import org.pcb.unit.amounts.base.Gram
import org.pcb.unit.amounts.base.Gram.GRAM
import org.pcb.unit.amounts.base.Meter
import org.pcb.unit.amounts.base.Meter.METER
import org.pcb.unit.amounts.base.Numeric
import org.pcb.unit.amounts.base.Second
import org.pcb.unit.amounts.base.Second.SECOND
import org.pcb.unit.amounts.electromagnetism.Ohm.OHM
import org.pcb.unit.kilo

class OhmTest
{
    private infix fun Ohm.equivalent(other: ElectricResistanceAmount<*, *, *, *>) =
        assertEquals(this, other)

    @Test
    fun unit()
    {
        Ohm(0.25) equivalent Gram(2, KILO) * (Meter(2) / Second(2)) * (Meter(2) / Second(2)) / Second(2) / Ampere(2) / Ampere(2)
        Ohm(2) equivalent ElectricResistanceAmount(2, U2(METER, kilo(GRAM)) to U2(AMPERE, SECOND))
        Ohm(2) equivalent ElectricResistanceAmount(2, OHM)
    }

    @Test
    fun oneDivSiemens() =
        assertEquals(
            expected = Ohm(6 / 3),
            actual = Numeric(6) / Siemens(3))

    @Test
    fun secondDivFarad() =
        assertEquals(
            expected = Ohm(6 / 3),
            actual = Second(6) / Farad(3))

    @Test
    fun voltDivAmpere() =
        assertEquals(
            expected = Ohm(6 / 3),
            actual = Volt(6) / Ampere(3))

    @Test
    fun weberDivCoulomb() =
        assertEquals(
            expected = Ohm(6 / 3),
            actual = Weber(6) / Coulomb(3))
}