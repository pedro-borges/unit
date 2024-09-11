package org.pcb.unit.units.electromagnetism

import org.pcb.unit.U2
import org.pcb.unit.Unit
import org.pcb.unit.dimension.ElectricCurrent
import org.pcb.unit.dimension.MagneticFluxDensity
import org.pcb.unit.dimension.Mass
import org.pcb.unit.dimension.Time
import org.pcb.unit.kilo
import org.pcb.unit.toBigBigDecimal
import org.pcb.unit.units.MagneticFluxDensityAmount
import org.pcb.unit.units.base.Ampere.AMPERE
import org.pcb.unit.units.base.Gram.GRAM
import org.pcb.unit.units.base.Second.SECOND

class Tesla(amount: Number)
    : MagneticFluxDensityAmount<Unit<Mass>, Unit<Time>, Unit<ElectricCurrent>>(
    amount = amount,
    unit = kilo(GRAM) to U2(SECOND, AMPERE))
{
    object TESLA : Unit<MagneticFluxDensity>(MagneticFluxDensity::class, "Wb", 1.toBigBigDecimal())
}
