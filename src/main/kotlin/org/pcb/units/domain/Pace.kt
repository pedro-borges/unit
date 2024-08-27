package org.pcb.units.domain

interface Pace : Domain
{
    operator fun times(space: Space): Time
}