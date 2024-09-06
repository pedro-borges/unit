package org.pcb.unit

data class U2<out A : Unit<*>, out B : Unit<*>>(
        val first: A,
        val second: B)
