package org.example

import org.example.model.*
import org.example.utils.TimeConverter


fun main() {
    val appStartTime: Long by AppStartTimeDelegate()
    val listWithManyTypes: List<Any> = listOf(
        Student("13235", "Peter", 22),
        "Some string",
        666L,
        Key(5, "some"),
        'c',
        22,
        1.23,
        false,
        14L
    )

    // Task 1 data класс может быть использован, но это не безопасно, потому что свойство field2 можно изменить
    // что приведет к тому, что мы не сможем больше получать значение по ключу
    val key1 = Key(11, "key1")
    val key2 = Key(22, "key2")
    val someMap: HashMap<Key, String> = hashMapOf(key1 to "value1", key2 to "value2")
    println(someMap.get(key1))
    key1.field2 = "changedKey1"
    println(someMap.get(key1))
    // Task 2
    // println("App was launched in: ${TimeConverter.timestampToHhmmss(appStartTime)}")
    // Thread.sleep(10000)
    // Task 3
    // println("Press Enter to view the first Int in the list.")
    // readlnOrNull()
    // println("First Int in list is: ${listWithManyTypes.searchInt() ?: "nothing"}")
    // Task 4
    //val someListWithInts: ArrayList<Int?> = arrayListOf(66, null, 1, -50, 120, 1, null, null)
    //doShakeSort(someListWithInts)
}