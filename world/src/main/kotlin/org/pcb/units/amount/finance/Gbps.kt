package org.pcb.units.amount.finance

import org.pcb.units.amount.CurrencyAmount
import org.pcb.units.toBigBigDecimal
import org.pcb.units.unit.finance.Gbp

@Suppress("unused")
class Gbps(amount: Number)
    : CurrencyAmount<Gbp>(amount.toBigBigDecimal(), Gbp)
