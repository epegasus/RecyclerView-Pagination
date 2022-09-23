# RecyclerView-Pagination
[![](https://jitpack.io/v/epegasus/RecyclerView-Pagination.svg)](https://jitpack.io/#epegasus/RecyclerView-Pagination)

Library for pagination of any Type of List by following few easy steps.

## Getting Started

### Step 1

Add maven repository in project level build.gradle or in latest project setting.gradle file
```
    repositories {
        google()
        mavenCentral()
        maven { url "https://jitpack.io" }
    }
```  

### Step 2

Add RecyclerView-Pagination dependencies in App level build.gradle.
```
    dependencies {
           implementation 'com.github.epegasus:RecyclerView-Pagination:LATEST_RELEASE'
    }
```  
### Step 3

#### Do following steps
##### Declare Pagination Class
```
    private lateinit var recyclerViewPagination: RecyclerViewPagination<String>
```
##### Replace '20' with number of ranges.
##### Provide reference of recyclerView
##### Submit complete list and implement callbacks
```
    recyclerViewPagination = RecyclerViewPagination<String>(20).apply {
            setView(binding.recyclerViewMain)
            submitCompleteList(titleList, object : PaginationCallbacks<String> {
                override fun onPreload() {
                    // Show ProgressBar
                }

                override fun onLoaded(subList: List<String>) {
                    // Hide ProgressBar
                    // Update Adapter (Notify)
                }

                override fun onCompleted() {
                    // Hide ProgressBar
                    // List has been reached to end
                }
            })
        }
```
