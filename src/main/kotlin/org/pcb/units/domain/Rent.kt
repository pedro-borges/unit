package org.pcb.units.domain

interface Rent : Domain
{
    operator fun times(time: Time): Currency
}