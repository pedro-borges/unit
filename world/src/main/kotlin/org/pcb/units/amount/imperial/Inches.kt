package org.pcb.units.amount.imperial

import org.pcb.units.amount.SpaceAmount
import org.pcb.units.toBigBigDecimal
import org.pcb.units.unit.imperial.Inch

@Suppress("unused")
class Inches(amount: Number)
    : SpaceAmount<Inch>(amount.toBigBigDecimal(), Inch)
