package org.pcb.unit.shape

import org.pcb.unit.Unit
import org.pcb.unit.amounts.DistanceAmount
import org.pcb.unit.dimensions.Distance

class Square<L : Unit<Distance>>(side: DistanceAmount<L>)
    : Rectangle<L>(side, side)
