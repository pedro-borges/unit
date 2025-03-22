package org.pcb.unit.amounts.optics

import org.pcb.unit.Magnitude
import org.pcb.unit.SIScale.UNI
import org.pcb.unit.U2
import org.pcb.unit.Unit
import org.pcb.unit.amounts.IlluminanceAmount
import org.pcb.unit.base.Candela.CANDELA
import org.pcb.unit.base.Meter.METER
import org.pcb.unit.base.Radian.RADIAN
import org.pcb.unit.dimensions.Angle
import org.pcb.unit.dimensions.Distance
import org.pcb.unit.dimensions.Illuminance
import org.pcb.unit.dimensions.LuminousIntensity
import org.pcb.unit.toBigDecimal

class Lux(amount: Number,
          magnitude: Magnitude = UNI)
    : IlluminanceAmount<Unit<Angle>, Unit<Distance>, Unit<LuminousIntensity>>(
    amount = amount.toBigDecimal().movePointRight(magnitude.power),
    unit = U2(RADIAN, CANDELA) to METER)
{
    open class LuxUnit(magnitude: Magnitude)
        : Unit<Illuminance>(Illuminance::class, "lx", 1.toBigDecimal(), magnitude)

    object LUX : LuxUnit(UNI)
}