package org.pcb.unit.units.electromagnetism

import kotlin.test.Test
import kotlin.test.assertEquals
import org.pcb.unit.Unit
import org.pcb.unit.dimension.Mass
import org.pcb.unit.dimension.Time
import org.pcb.unit.units.ElectricCapacitanceAmount
import org.pcb.unit.units.base.Ampere.AMPERE
import org.pcb.unit.units.base.Meter.METER
import org.pcb.unit.units.base.Second
import org.pcb.unit.units.electromagnetism.Farad.FARAD

class FaradTest
{
    private infix fun Farad.equivalent(other: ElectricCapacitanceAmount<*, *, *, *>) =
        assertEquals<ElectricCapacitanceAmount<out Unit<Time>, AMPERE, out Unit<Mass>, METER>>(this, Farad(0) + other)

    @Test
    fun unit()
    {
//        Farad(2) equivalent Kilogram(2) TODO finish
        Farad(2) equivalent ElectricCapacitanceAmount(2, FARAD)
    }

    @Test
    fun coulombDivVolt() =
        assertEquals<ElectricCapacitanceAmount<out Unit<Time>, AMPERE, out Unit<Mass>, METER>>(
            expected = Farad(6 / 3),
            actual = Coulomb(6) / Volt(3))

    @Test
    fun secondDivOhm() =
        assertEquals<ElectricCapacitanceAmount<out Unit<Time>, AMPERE, out Unit<Mass>, METER>>(
            expected = Farad(6 / 3),
            actual = Second(6) / Ohm(3))
}