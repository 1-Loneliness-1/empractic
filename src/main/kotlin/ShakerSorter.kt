package org.example

fun ArrayList<Int?>.swap(index1: Int, index2: Int) {
    val temp = this[index1]
    this[index1] = this[index2]
    this[index2] = temp
}

fun doShakeSort(list: List<Int?>?) {
    if (list.isNullOrEmpty()) {
        println("List is null or empty. No sorting is required.")
        return
    }

    if (list.filterNotNull().isEmpty()) {
        println("Sorted list: $list")
        return
    }

    val arrayForSort = ArrayList(list)
    var lastNotNullElementIndex = 0

    for (i in arrayForSort.indices) {
        if (arrayForSort[i] != null) {
            arrayForSort.swap(i, lastNotNullElementIndex)
            lastNotNullElementIndex++
        }
    }

    var hasSwapInArray: Boolean
    var start = 0
    var end = lastNotNullElementIndex - 1

    do {
        hasSwapInArray = false

        for (i in start until end) {
            if (arrayForSort[i]!! > arrayForSort[i + 1]!!) {
                arrayForSort.swap(i, i+1)
                hasSwapInArray = true
            }
        }

        end--
        if (!hasSwapInArray) break

        for (j in end - 1 downTo start) {
            if (arrayForSort[j]!! > arrayForSort[j + 1]!!) {
                arrayForSort.swap(j, j+1)
                hasSwapInArray = true
            }
        }

        start++
    } while (hasSwapInArray)

    println("Sorted list: $arrayForSort")
}