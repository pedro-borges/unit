package org.pcb.units.unit

import java.math.BigDecimal.ONE
import org.pcb.units.domain.Scalar

class Scalar : Unit<Scalar>(Scalar::class)
{
    override val symbol = ""
    override val cannon = ONE
}