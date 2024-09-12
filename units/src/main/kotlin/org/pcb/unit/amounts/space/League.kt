package org.pcb.unit.amounts.space

import org.pcb.unit.Magnitude
import org.pcb.unit.Magnitude.UNI
import org.pcb.unit.Unit
import org.pcb.unit.amounts.DistanceAmount
import org.pcb.unit.dimensions.Distance
import org.pcb.unit.toBigDecimal

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
