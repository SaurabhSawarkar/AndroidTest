package com.assignment.kindred.network.model

class Categories(list: List<String>?) {

    private val categoriesList = list

    override fun toString(): String {
        if (null != categoriesList) {
            val builder = StringBuilder()
            for (value: String in categoriesList) {
                if (builder.isEmpty()) {
                    builder.append(value)
                } else {
                    builder.append(",")
                    builder.append(value)
                }
            }
            return builder.toString()
        }
        return super.toString()
    }

}