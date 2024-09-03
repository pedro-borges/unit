package org.pcb.unit.units.electromagnetism

import kotlin.test.Test
import kotlin.test.assertEquals
import org.pcb.unit.Unit
import org.pcb.unit.dimension.Mass
import org.pcb.unit.dimension.Time
import org.pcb.unit.units.ElectricResistanceAmount
import org.pcb.unit.units.base.Ampere
import org.pcb.unit.units.base.Ampere.AMPERE
import org.pcb.unit.units.base.Kilogram
import org.pcb.unit.units.base.Meter
import org.pcb.unit.units.base.Meter.METER
import org.pcb.unit.units.base.Second
import org.pcb.unit.units.electromagnetism.Ohm.OHM

class OhmTest
{
    @Test
    fun unit()
    {
        Ohm(0.25) equivalent Kilogram(2) * (Meter(2) / Second(2)) * (Meter(2) / Second(2)) / Second(2) / Ampere(2) / Ampere(2)
        Ohm(2) equivalent ElectricResistanceAmount(2, OHM)

        assertEquals<ElectricResistanceAmount<out Unit<Mass>, METER, out Unit<Time>, AMPERE>>(
            expected = Ohm(amount = 6 / 3),
            actual = Volt(6) / Ampere(3))
    }

    private infix fun Ohm.equivalent(other: ElectricResistanceAmount<*, *, *, *>) =
        assertEquals<ElectricResistanceAmount<out Unit<Mass>, METER, out Unit<Time>, AMPERE>>(this, Ohm(0) + other)
}