package org.pcb.units

import java.math.BigDecimal

fun Number.toBigDecimal() =
    when
    {
        this is BigDecimal -> this
        else               -> BigDecimal(toString())
    }
