package org.pcb.unit.units.electromagnetism

import org.pcb.unit.U2
import org.pcb.unit.Unit
import org.pcb.unit.dimension.Distance
import org.pcb.unit.dimension.ElectricCurrent
import org.pcb.unit.dimension.Inductance
import org.pcb.unit.dimension.Mass
import org.pcb.unit.dimension.Time
import org.pcb.unit.toBigBigDecimal
import org.pcb.unit.units.InductanceAmount
import org.pcb.unit.units.base.Ampere.AMPERE
import org.pcb.unit.units.base.Kilogram.KILOGRAM
import org.pcb.unit.units.base.Meter.METER
import org.pcb.unit.units.base.Second.SECOND

class Henry(amount: Number)
    : InductanceAmount<Unit<Mass>, Unit<Distance>, Unit<Time>, Unit<ElectricCurrent>>(
    amount = amount,
    unit = U2(KILOGRAM, METER) to U2(SECOND, AMPERE))
{
    object HENRY : Unit<Inductance>(Inductance::class)
    {
        override val symbol = "H"
        override val scale = 1.toBigBigDecimal()
    }
}
