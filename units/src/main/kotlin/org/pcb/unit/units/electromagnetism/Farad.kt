package org.pcb.unit.units.electromagnetism

import org.pcb.unit.U2
import org.pcb.unit.Unit
import org.pcb.unit.dimension.Capacitance
import org.pcb.unit.dimension.Distance
import org.pcb.unit.dimension.ElectricCurrent
import org.pcb.unit.dimension.Mass
import org.pcb.unit.dimension.Time
import org.pcb.unit.toBigBigDecimal
import org.pcb.unit.units.CapacitanceAmount
import org.pcb.unit.units.base.Ampere.AMPERE
import org.pcb.unit.units.base.Kilogram.KILOGRAM
import org.pcb.unit.units.base.Meter.METER
import org.pcb.unit.units.base.Second.SECOND

class Farad(amount: Number)
    : CapacitanceAmount<Unit<Time>, Unit<ElectricCurrent>, Unit<Mass>, Unit<Distance>>(
    amount = amount,
    unit = U2(SECOND, AMPERE) to U2(KILOGRAM, METER))
{
    object FARAD : Unit<Capacitance>(Capacitance::class)
    {
        override val symbol = "F"
        override val scale = 1.toBigBigDecimal()
    }
}
