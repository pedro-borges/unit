package org.pcb.units.amounts.imperial

import org.pcb.units.amounts.domain.SpaceAmount
import org.pcb.units.toBigDecimal
import org.pcb.units.unit.imperial.Foot.Companion.FEET

class Feet(amount: Number)
    : SpaceAmount(amount.toBigDecimal(), FEET)
{
}