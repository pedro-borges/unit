package org.pcb.unit.units.space

import org.pcb.unit.Magnitude
import org.pcb.unit.Magnitude.UNI
import org.pcb.unit.Unit
import org.pcb.unit.dimension.Distance
import org.pcb.unit.toBigDecimal
import org.pcb.unit.units.DistanceAmount

class League(amount: Number,
             magnitude: Magnitude = UNI)
    : DistanceAmount<Unit<Distance>>(
    amount = amount.toBigDecimal().movePointRight(magnitude.power),
    unit = LEAGUE)
{
    open class LeagueUnit(magnitude: Magnitude)
        : Unit<Distance>(Distance::class, "lea", 4828.032.toBigDecimal(), magnitude)

    object LEAGUE : LeagueUnit(UNI)
}
