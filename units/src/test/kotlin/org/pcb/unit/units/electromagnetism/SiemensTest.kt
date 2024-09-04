package org.pcb.unit.units.electromagnetism

import kotlin.test.Test
import kotlin.test.assertEquals
import org.pcb.unit.Unit
import org.pcb.unit.dimension.Mass
import org.pcb.unit.dimension.Time
import org.pcb.unit.units.ElectricConductivityAmount
import org.pcb.unit.units.base.Ampere
import org.pcb.unit.units.base.Ampere.AMPERE
import org.pcb.unit.units.base.Meter.METER
import org.pcb.unit.units.electromagnetism.Siemens.SIEMENS

class SiemensTest
{
    @Test
    fun unit()
    {
//        Siemens(0.25) equivalent Kilogram(2) * (Meter(2) / Second(2)) * (Meter(2) / Second(2)) / Second(2) / Ampere(2) / Ampere(2) TODO
        Siemens(2) equivalent ElectricConductivityAmount(2, SIEMENS)

        assertEquals<ElectricConductivityAmount<out Unit<Time>, AMPERE, out Unit<Mass>, METER>>(
            expected = Siemens(amount = 6 / 3),
            actual = Ampere(6) / Volt(3))
    }

    private infix fun Siemens.equivalent(other: ElectricConductivityAmount<*, *, *, *>) =
        assertEquals<ElectricConductivityAmount<out Unit<Time>, AMPERE, out Unit<Mass>, METER>>(this, Siemens(0) + other)
}