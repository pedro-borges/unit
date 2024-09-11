package org.pcb.unit.units.electromagnetism

import org.pcb.unit.Magnitude
import org.pcb.unit.Magnitude.UNI
import org.pcb.unit.U2
import org.pcb.unit.Unit
import org.pcb.unit.dimension.Distance
import org.pcb.unit.dimension.ElectricCurrent
import org.pcb.unit.dimension.MagneticFlux
import org.pcb.unit.dimension.Mass
import org.pcb.unit.dimension.Time
import org.pcb.unit.kilo
import org.pcb.unit.toBigBigDecimal
import org.pcb.unit.units.MagneticFluxAmount
import org.pcb.unit.units.base.Ampere.AMPERE
import org.pcb.unit.units.base.Gram.GramUnit
import org.pcb.unit.units.base.Meter.METER
import org.pcb.unit.units.base.Second.SECOND

class Weber(amount: Number,
            magnitude: Magnitude = UNI)
    : MagneticFluxAmount<Unit<Mass>, Unit<Distance>, Unit<Time>, Unit<ElectricCurrent>>(
    amount = amount,
    unit = U2(kilo(GramUnit(magnitude)), METER) to U2(SECOND, AMPERE))
{
    open class WeberUnit(magnitude: Magnitude)
        : Unit<MagneticFlux>(MagneticFlux::class, "Wb", magnitude, 1.toBigBigDecimal())

    object WEBER : WeberUnit(UNI)
}
