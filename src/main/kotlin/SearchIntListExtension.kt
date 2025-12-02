package org.example

fun List<Any>.searchInt(): Int? {
    return this.firstOrNull { it is Int } as Int?
}