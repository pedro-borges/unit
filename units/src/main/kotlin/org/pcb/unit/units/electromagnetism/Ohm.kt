package org.pcb.unit.units.electromagnetism

import org.pcb.unit.T2
import org.pcb.unit.Unit
import org.pcb.unit.dimension.ElectricCurrent
import org.pcb.unit.dimension.ElectricResistance
import org.pcb.unit.dimension.Length
import org.pcb.unit.dimension.Mass
import org.pcb.unit.dimension.Time
import org.pcb.unit.toBigBigDecimal
import org.pcb.unit.units.ElectricResistanceAmount
import org.pcb.unit.units.base.Ampere.AMPERE
import org.pcb.unit.units.base.Kilogram.KILOGRAM
import org.pcb.unit.units.base.Meter.METER
import org.pcb.unit.units.base.Second.SECOND

class Ohm(amount: Number)
    : ElectricResistanceAmount<Unit<Mass>, Unit<Length>, Unit<Time>, Unit<ElectricCurrent>>(
    amount = amount,
    unit = T2(KILOGRAM, METER) / T2(SECOND, AMPERE))
{
    object OHM : Unit<ElectricResistance>(ElectricResistance::class)
    {
        override val symbol = "Ω"
        override val scale = 1.toBigBigDecimal()
    }
}
