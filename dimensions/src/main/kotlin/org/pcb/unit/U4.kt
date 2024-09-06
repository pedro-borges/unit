package org.pcb.unit

data class U4<out A : Unit<*>, out B : Unit<*>, out C : Unit<*>, out D : Unit<*>>(
        val first: A,
        val second: B,
        val third: C,
        val fourth: D)
