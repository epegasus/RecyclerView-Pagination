package dev.epegasus.recyclerviewpagination

import androidx.recyclerview.widget.RecyclerView
import dev.epegasus.recyclerviewpagination.interfaces.PaginationCallbacks
import dev.epegasus.recyclerviewpagination.utils.addOnScrolledToEnd
import kotlinx.coroutines.*

/**
 *  @property T: Type of Model Class
 *  @param range: Set the range of fetching list
 */
class RecyclerViewPagination<T : Any>(private val range: Int = 15) {

    private lateinit var originalList: List<T>

    private var recyclerView: RecyclerView? = null
    private var paginationCallbacks: PaginationCallbacks<T>? = null

    private var bisectedArrayList = ArrayList<T>()
    private val bisectedList: List<T> get() = bisectedArrayList.toList()

    private var start = 0
    private var end = 0

    /**
     *  Set RecyclerView's View
     */

    fun setView(recyclerView: RecyclerView): RecyclerViewPagination<T> {
        this.recyclerView = recyclerView
        return this
    }

    /**
     * @param originalList: Submit the original list of Any Type <T>
     * @param paginationCallbacks: Use this callbacks to listen the behavior of Pagination's
     */

    fun submitCompleteList(originalList: List<T>, paginationCallbacks: PaginationCallbacks<T>) {
        this.originalList = originalList
        this.paginationCallbacks = paginationCallbacks
        initialList()
    }

    private fun initialList() {
        paginationCallbacks?.onPreload()
        // First ranged Items
        end += range
        bisectedArrayList.clear()
        bisectedArrayList.addAll(originalList.take(end))
        addOnScrollListener()
        paginationCallbacks?.onLoaded(bisectedList)
    }

    private fun addOnScrollListener() {
        recyclerView?.let {
            it.addOnScrolledToEnd {
                paginationCallbacks?.onPreload()
                CoroutineScope(Dispatchers.Main).launch {
                    CoroutineScope(Dispatchers.Default).launch {
                        delay(1000)
                        start = end
                        if (originalList.size > bisectedList.size) {
                            end += range
                            if (originalList.size > end) {
                                bisectedArrayList.addAll(originalList.subList(start, end).toList())
                            } else {
                                end = originalList.size
                                bisectedArrayList.addAll(originalList.subList(start, end).toList())
                            }
                        } else {
                            withContext(Dispatchers.Main) {
                                paginationCallbacks?.onCompleted()
                            }
                        }
                    }.join()
                    paginationCallbacks?.onLoaded(bisectedList)
                }
            }
        } ?: kotlin.run {
            throw NullPointerException("RecyclerView not Found! Use 'setView()' method to set RecyclerView")
        }
    }
}