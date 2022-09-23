package dev.epegasus.recyclerview_pagination.ui

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import dev.epegasus.recyclerview_pagination.helper.adapters.AdapterMainFeature
import dev.epegasus.recyclerview_pagination.helper.dataProviders.DPMainFeatures
import dev.epegasus.recyclerview_pagination.databinding.ActivityMainBinding
import dev.epegasus.recyclerviewpagination.RecyclerViewPagination
import dev.epegasus.recyclerviewpagination.interfaces.PaginationCallbacks

class MainActivity : AppCompatActivity() {

    private val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }
    private val titleList by lazy { DPMainFeatures().getMainFeaturesList() }
    private val adapterMainFeature by lazy { AdapterMainFeature() }

    private lateinit var recyclerViewPagination: RecyclerViewPagination<String>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        initRecyclerView()
        initRecyclerViewManager()
    }

    private fun initRecyclerView() {
        binding.recyclerViewMain.adapter = adapterMainFeature
    }

    private fun initRecyclerViewManager() {
        recyclerViewPagination = RecyclerViewPagination<String>(20).apply {
            setView(binding.recyclerViewMain)
            submitCompleteList(titleList, object : PaginationCallbacks<String> {
                override fun onPreload() {
                    binding.progressBarMain.visibility = View.VISIBLE
                }

                override fun onLoaded(subList: List<String>) {
                    binding.progressBarMain.visibility = View.GONE
                    adapterMainFeature.submitList(subList)
                }

                override fun onCompleted() {
                    binding.progressBarMain.visibility = View.GONE
                    Toast.makeText(this@MainActivity, "Reached to End", Toast.LENGTH_SHORT).show()
                }
            })
        }
    }
}