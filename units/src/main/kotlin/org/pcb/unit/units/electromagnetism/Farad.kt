package org.pcb.unit.units.electromagnetism

import org.pcb.unit.Tuple
import org.pcb.unit.Unit
import org.pcb.unit.dimension.ElectricCapacitance
import org.pcb.unit.dimension.ElectricCurrent
import org.pcb.unit.dimension.Length
import org.pcb.unit.dimension.Mass
import org.pcb.unit.dimension.Time
import org.pcb.unit.toBigBigDecimal
import org.pcb.unit.units.ElectricCapacitanceAmount
import org.pcb.unit.units.base.Ampere.AMPERE
import org.pcb.unit.units.base.Kilogram.KILOGRAM
import org.pcb.unit.units.base.Meter.METER
import org.pcb.unit.units.base.Second.SECOND

class Farad(amount: Number)
    : ElectricCapacitanceAmount<Unit<Time>, Unit<ElectricCurrent>, Unit<Mass>, Unit<Length>>(
    amount = amount,
    unit = Tuple(SECOND, AMPERE) / Tuple(KILOGRAM, METER))
{
    object FARAD : Unit<ElectricCapacitance>(ElectricCapacitance::class)
    {
        override val symbol = "F"
        override val scale = 1.toBigBigDecimal()
    }
}
