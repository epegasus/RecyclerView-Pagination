package dev.epegasus.recyclerviewpagination.utils

import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

fun RecyclerView.addOnScrolledToEnd(onScrolledToEnd: () -> Unit) {

    this.addOnScrollListener(object : RecyclerView.OnScrollListener() {

        private val visibleThreshold = 5
        private var loading = true
        private var previousTotal = 0

        override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {

            with(layoutManager as LinearLayoutManager) {
                val visibleItemCount = childCount
                val totalItemCount = itemCount
                val firstVisibleItem = findFirstVisibleItemPosition()

                if (loading && totalItemCount > previousTotal) {
                    loading = false
                    previousTotal = totalItemCount
                }

                if (!loading && (totalItemCount - visibleItemCount) <= (firstVisibleItem + visibleThreshold)) {
                    onScrolledToEnd()
                    loading = true
                }
            }
        }
    })
}