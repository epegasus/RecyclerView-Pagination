package dev.epegasus.recyclerview_pagination.helper.dataProviders

class DPMainFeatures {

    fun getMainFeaturesList(): List<String> {
        val arrayList = ArrayList<String>()
        for (i in 1..90) {
            arrayList.add("Number # $i")
        }
        return arrayList.toList()
    }
}