package org.pcb.unit.units.electromagnetism

import org.pcb.unit.Magnitude
import org.pcb.unit.Magnitude.UNI
import org.pcb.unit.U2
import org.pcb.unit.Unit
import org.pcb.unit.dimension.ElectricCurrent
import org.pcb.unit.dimension.MagneticFluxDensity
import org.pcb.unit.dimension.Mass
import org.pcb.unit.dimension.Time
import org.pcb.unit.kilo
import org.pcb.unit.toBigDecimal
import org.pcb.unit.units.MagneticFluxDensityAmount
import org.pcb.unit.units.base.Ampere.AMPERE
import org.pcb.unit.units.base.Gram.GRAM
import org.pcb.unit.units.base.Second.SECOND

class Tesla(amount: Number,
            magnitude: Magnitude = UNI)
    : MagneticFluxDensityAmount<Unit<Mass>, Unit<Time>, Unit<ElectricCurrent>>(
    amount = amount.toBigDecimal().movePointRight(magnitude.power),
    unit = kilo(GRAM) to U2(SECOND, AMPERE))
{
    open class TeslaUnit(magnitude: Magnitude)
        : Unit<MagneticFluxDensity>(MagneticFluxDensity::class, "T", 1.toBigDecimal(), magnitude)

    object TESLA : TeslaUnit(UNI)
}
