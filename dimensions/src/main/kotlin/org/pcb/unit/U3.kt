package org.pcb.unit

data class U3<out A : Unit<*>, out B : Unit<*>, out C : Unit<*>>(
        val first: A,
        val second: B,
        val third: C)
