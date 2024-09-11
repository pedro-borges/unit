package org.pcb.unit.units.electromagnetism

import org.pcb.unit.Magnitude
import org.pcb.unit.Magnitude.UNI
import org.pcb.unit.U2
import org.pcb.unit.Unit
import org.pcb.unit.dimension.Distance
import org.pcb.unit.dimension.ElectricCurrent
import org.pcb.unit.dimension.Inductance
import org.pcb.unit.dimension.Mass
import org.pcb.unit.dimension.Time
import org.pcb.unit.kilo
import org.pcb.unit.units.InductanceAmount
import org.pcb.unit.units.base.Ampere.AMPERE
import org.pcb.unit.units.base.Gram.GramUnit
import org.pcb.unit.units.base.Meter.METER
import org.pcb.unit.units.base.Second.SECOND

class Henry(amount: Number,
            magnitude: Magnitude = UNI)
    : InductanceAmount<Unit<Mass>, Unit<Distance>, Unit<Time>, Unit<ElectricCurrent>>(
    amount = amount,
    unit = U2(kilo(GramUnit(magnitude)), METER) to U2(SECOND, AMPERE))
{
    open class HenryUnit(magnitude: Magnitude)
        : Unit<Inductance>(Inductance::class, magnitude.symbol + "H", magnitude.scale)

    object HENRY : HenryUnit(UNI)
}
