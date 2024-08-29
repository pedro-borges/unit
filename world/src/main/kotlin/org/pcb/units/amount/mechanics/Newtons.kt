package org.pcb.units.amount.mechanics

import org.pcb.units.amount.ForceAmount
import org.pcb.units.toBigBigDecimal
import org.pcb.units.unit.mechanics.Kilogram
import org.pcb.units.unit.metric.Meter
import org.pcb.units.unit.time.Second

@Suppress("unused")
class Newtons(amount: Number)
    : ForceAmount<Kilogram, Meter, Second, Second>(amount.toBigBigDecimal(), Kilogram, Meter, Second, Second)
{
    companion object
    {
        val NEWTONS = Newtons(0).unit
    }
}