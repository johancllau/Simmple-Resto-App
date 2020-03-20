package com.neox.restoapp.ui.activity

import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.core.content.ContextCompat
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.neox.restoapp.R
import com.neox.restoapp.model.Value
import com.neox.restoapp.service.helper.RecyclerViewItemClickSupport
import com.neox.restoapp.ui.adapter.ListCafeAdapter
import com.neox.restoapp.viewmodel.MainActivityViewModel
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private lateinit var mainActivityViewModel: MainActivityViewModel
    private lateinit var listCafeAdapter: ListCafeAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setSupportActionBar(toolbarHome)
        progressBarHome.visibility = View.VISIBLE
        collapsingToolbarHome.title = "Home"

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            collapsingToolbarHome.setCollapsedTitleTextColor(resources.getColor(R.color.black, theme))
            collapsingToolbarHome.setExpandedTitleColor(resources.getColor(R.color.homeTitle,  theme))
        } else {
            collapsingToolbarHome.setCollapsedTitleTextColor(ContextCompat.getColor(this, R.color.black))
            collapsingToolbarHome.setExpandedTitleColor(ContextCompat.getColor(this, R.color.homeTitle))
        }

        mainActivityViewModel = ViewModelProvider(this).get(MainActivityViewModel::class.java)
        mainActivityViewModel.fetchDataCafe()

        recyclerViewListCafe.apply {
            layoutManager = LinearLayoutManager(this@MainActivity)
            setHasFixedSize(true)
        }

        mainActivityViewModel.cafe.observe(this, Observer { itemCafe ->
                progressBarHome.visibility = View.GONE

                itemCafe?.let {
                    listCafeAdapter = ListCafeAdapter(it.values)
                    listCafeAdapter.notifyDataSetChanged()
                    recyclerViewListCafe.adapter = listCafeAdapter

                    listCafeAdapter.setOnItemClickCallback(object : RecyclerViewItemClickSupport {
                        override fun onItemClick(data: Any) {
                            when (data) {
                                is Value -> {
                                    startActivity(
                                        Intent(this@MainActivity, DetailCafeActivity::class.java).apply {
                                                putExtra(DetailCafeActivity.EXTRA_CAFE, data)
                                        }
                                    )
                                }
                                else -> {
                                    Throwable("Illegal position")
                                }
                            }
                        }

                    })
                }
        })
    }
}
