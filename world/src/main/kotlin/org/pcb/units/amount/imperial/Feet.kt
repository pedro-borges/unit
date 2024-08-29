package org.pcb.units.amount.imperial

import org.pcb.units.amount.SpaceAmount
import org.pcb.units.toBigBigDecimal
import org.pcb.units.unit.imperial.Foot

@Suppress("unused")
class Feet(amount: Number)
    : SpaceAmount<Foot>(amount.toBigBigDecimal(), Foot)
