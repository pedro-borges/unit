package org.pcb.units.amounts.imperial

import org.pcb.units.amounts.generic.SpaceAmount
import org.pcb.units.toBigDecimal
import org.pcb.units.unit.imperial.Foot
import org.pcb.units.unit.imperial.Foot.Companion.FOOT

class Feet(amount: Number)
    : SpaceAmount<Foot>(amount.toBigDecimal(), FOOT)
{
}