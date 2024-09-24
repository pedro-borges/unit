package org.pcb.unit.dimensions

interface Dimension
{
    val composite: Map<out Dimension, Int>
}