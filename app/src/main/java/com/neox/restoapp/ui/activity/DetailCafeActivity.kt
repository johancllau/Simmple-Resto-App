package com.neox.restoapp.ui.activity

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import com.google.android.material.snackbar.Snackbar
import com.neox.restoapp.R
import com.neox.restoapp.model.Value
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_detail_cafe.*

class DetailCafeActivity : AppCompatActivity() {

    companion object {
        const val EXTRA_CAFE = "extra_cafe"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_cafe)

        setSupportActionBar(toolbarDetailCafe)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        if (intent.hasExtra(EXTRA_CAFE)) {
            val dataCafe = intent.getParcelableExtra<Value>(EXTRA_CAFE)

            dataCafe?.let { item ->
                Picasso.get().load(item.spacePhoto)
                    .placeholder(R.drawable.ic_load)
                    .error(R.drawable.ic_image_error)
                    .into(imageViewDetailCafe)

                textViewDetailCafeName.text = item.spaceName
                textViewDetailCafeOpen.text = item.spaceHours
                textViewDetailCafeAddress.text = item.spaceAddress
                textViewDetailCafeDescription.text = item.spaceDescription

                imageViewDetailCafeLocation.setOnClickListener {
                    if (item.spaceLocation != "") {
                        val intent = Intent(Intent.ACTION_VIEW)
                        intent.data = Uri.parse(item.spaceLocation)
                        startActivity(intent)
                    } else {
                        Snackbar.make(
                            findViewById(android.R.id.content),
                            "Link address is empty",
                            Snackbar.LENGTH_LONG
                        ).show()
                    }
                }
            }
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) this.finish()
        return super.onOptionsItemSelected(item)
    }
}
