package org.pcb.unit

data class Quintuple<out A : Unit<*>, out B : Unit<*>, out C : Unit<*>, out D : Unit<*>, out E: Unit<*>>(
        val first: A,
        val second: B,
        val third: C,
        val fourth: D,
        val fifth: E)
