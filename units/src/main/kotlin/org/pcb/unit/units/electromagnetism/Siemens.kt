package org.pcb.unit.units.electromagnetism

import org.pcb.unit.U2
import org.pcb.unit.Unit
import org.pcb.unit.dimension.Distance
import org.pcb.unit.dimension.ElectricConductance
import org.pcb.unit.dimension.ElectricCurrent
import org.pcb.unit.dimension.Mass
import org.pcb.unit.dimension.Time
import org.pcb.unit.toBigBigDecimal
import org.pcb.unit.units.ElectricConductanceAmount
import org.pcb.unit.units.base.Ampere.AMPERE
import org.pcb.unit.units.base.Kilogram.KILOGRAM
import org.pcb.unit.units.base.Meter.METER
import org.pcb.unit.units.base.Second.SECOND

class Siemens(amount: Number)
    : ElectricConductanceAmount<Unit<Time>, Unit<ElectricCurrent>, Unit<Mass>, Unit<Distance>>(
    amount = amount,
    unit = U2(SECOND, AMPERE) to U2(KILOGRAM, METER))
{
    object SIEMENS : Unit<ElectricConductance>(ElectricConductance::class)
    {
        override val symbol = "S"
        override val scale = 1.toBigBigDecimal()
    }
}
