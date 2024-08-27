package org.pcb.units.amounts.mechanics

import org.pcb.units.amounts.generic.ForceAmount
import org.pcb.units.toBigDecimal
import org.pcb.units.unit.mechanics.Newton.Companion.NEWTON

class Newtons(amount: Number)
    : ForceAmount(amount.toBigDecimal(), NEWTON)
{
    companion object
    {
        val NEWTONS = Newtons(0).unit
    }
}