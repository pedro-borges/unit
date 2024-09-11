package org.pcb.unit.units.space

import org.pcb.unit.Magnitude
import org.pcb.unit.Magnitude.UNI
import org.pcb.unit.Unit
import org.pcb.unit.dimension.Distance
import org.pcb.unit.toBigBigDecimal
import org.pcb.unit.units.DistanceAmount

class League(amount: Number,
             magnitude: Magnitude = UNI)
    : DistanceAmount<Unit<Distance>>(
    amount = amount,
    unit = LeagueUnit(magnitude))
{
    open class LeagueUnit(magnitude: Magnitude)
        : Unit<Distance>(Distance::class, magnitude.symbol + "lea", magnitude.scale * 4828.032.toBigBigDecimal())

    object LEAGUE : LeagueUnit(UNI)
}