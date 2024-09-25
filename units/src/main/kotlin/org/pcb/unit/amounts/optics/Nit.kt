package org.pcb.unit.amounts.optics

import org.pcb.unit.Magnitude
import org.pcb.unit.Magnitude.Companion.UNI
import org.pcb.unit.Unit
import org.pcb.unit.amounts.LuminanceAmount
import org.pcb.unit.base.Candela.CANDELA
import org.pcb.unit.base.Meter.METER
import org.pcb.unit.dimensions.Distance
import org.pcb.unit.dimensions.Luminance
import org.pcb.unit.dimensions.LuminousIntensity
import org.pcb.unit.toBigDecimal

class Nit(amount: Number,
          magnitude: Magnitude = UNI)
    : LuminanceAmount<Unit<Distance>, Unit<LuminousIntensity>>(
    amount = amount.toBigDecimal().movePointRight(magnitude.power),
    unit = CANDELA to METER)
{
    open class NitUnit(magnitude: Magnitude)
        : Unit<Luminance>(Luminance::class, "nit", 1.toBigDecimal(), magnitude)

    object NIT : NitUnit(UNI)
}