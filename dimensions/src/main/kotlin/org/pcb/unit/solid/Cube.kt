package org.pcb.unit.solid

import org.pcb.unit.Unit
import org.pcb.unit.amounts.DistanceAmount
import org.pcb.unit.config.Configuration.calculationMathContext
import org.pcb.unit.dimensions.Distance

class Cube<L : Unit<Distance>>(val side: DistanceAmount<L>)
    : Solid<L>
{
    override val narrowest = side
    override val widest = side * 3.toBigDecimal().sqrt(calculationMathContext)
    override val area = side * side * 6
    override val volume = side * side * side
}