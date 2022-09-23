package dev.epegasus.recyclerviewpagination.interfaces

/**
 * @property T: Type of Object for pagination
 */

interface PaginationCallbacks<T> {
    fun onPreload()
    fun onLoaded(subList: List<T>)
    fun onCompleted()
}