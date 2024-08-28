package org.pcb.units.amounts.finance

import org.pcb.units.amounts.generic.CurrencyAmount
import org.pcb.units.toBigDecimal
import org.pcb.units.unit.finance.Gbp
import org.pcb.units.unit.finance.Gbp.Companion.GBP

class Gbp(amount: Number)
    : CurrencyAmount<Gbp>(amount.toBigDecimal(), GBP)
{
}