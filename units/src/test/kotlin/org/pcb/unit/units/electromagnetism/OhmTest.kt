package org.pcb.unit.units.electromagnetism

import kotlin.test.Test
import kotlin.test.assertEquals
import org.pcb.unit.Magnitude.KILO
import org.pcb.unit.U2
import org.pcb.unit.kilo
import org.pcb.unit.units.ElectricResistanceAmount
import org.pcb.unit.units.base.Ampere
import org.pcb.unit.units.base.Ampere.AMPERE
import org.pcb.unit.units.base.Gram
import org.pcb.unit.units.base.Gram.GRAM
import org.pcb.unit.units.base.Meter
import org.pcb.unit.units.base.Meter.METER
import org.pcb.unit.units.base.Second
import org.pcb.unit.units.base.Second.SECOND
import org.pcb.unit.units.electromagnetism.Ohm.OHM
import org.pcb.unit.units.scalar.Numeric

class OhmTest
{
    private infix fun Ohm.equivalent(other: ElectricResistanceAmount<*, *, *, *>) =
        assertEquals(this, Ohm(0) + other)

    @Test
    fun unit()
    {
        Ohm(0.25) equivalent Gram(2, KILO) * (Meter(2) / Second(2)) * (Meter(2) / Second(2)) / Second(2) / Ampere(2) / Ampere(2)
        Ohm(2) equivalent ElectricResistanceAmount(2, U2(kilo(GRAM), METER) to U2(SECOND, AMPERE))
        Ohm(2) equivalent ElectricResistanceAmount(2, OHM)
    }

    @Test
    fun oneDivSiemens() =
        assertEquals(
            expected = Ohm(2 * 3),
            actual = Numeric(2) / Siemens(3))

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