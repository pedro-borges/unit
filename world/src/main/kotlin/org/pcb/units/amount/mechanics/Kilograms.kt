package org.pcb.units.amount.mechanics

import org.pcb.units.amount.MassAmount
import org.pcb.units.toBigBigDecimal
import org.pcb.units.unit.mechanics.Kilogram

@Suppress("unused")
class Kilograms(amount: Number)
    : MassAmount<Kilogram>(amount.toBigBigDecimal(), Kilogram)
