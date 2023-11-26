package com.carissa.pemulaandroid

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import com.google.android.material.floatingactionbutton.FloatingActionButton

class DetailActivity : AppCompatActivity() {
    private lateinit var dataAnimal: Animal

    @Suppress("DEPRECATION")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)
        dataAnimal = intent.getParcelableExtra("key_animal")!!

        val tvDetailName = findViewById<TextView>(R.id.tv_detail_name)
        val tvDetailDescription = findViewById<TextView>(R.id.tv_detail_desc)
        val ivDetailImg = findViewById<ImageView>(R.id.iv_detail_img)
        val tvadditionalinfo = findViewById<TextView>(R.id.tv_additional_info)
        val tags = dataAnimal.tag_animal.joinToString(", ")
        val tvTags = findViewById<TextView>(R.id.tv_tags)
        tvTags.text = tags
        val tvClassAnimal = findViewById<TextView>(R.id.tv_class_animal)
        val tvFamilyAnimal = findViewById<TextView>(R.id.tv_family_animal)
        val tvOrderAnimal = findViewById<TextView>(R.id.tv_order_animal)

        val actionShare = findViewById<FloatingActionButton>(R.id.action_share)
        actionShare.setOnClickListener {
            shareAnimalDetails()
        }

        dataAnimal.let {
            tvDetailName.text = it.name_animal
            tvDetailDescription.text = it.desc_animal
            tvadditionalinfo.text = it.extra_info
            tvClassAnimal.text = it.class_animal
            tvFamilyAnimal.text = it.family_animal
            tvOrderAnimal.text = it.order_animal

            if (it.img_animal != -1) {
                ivDetailImg.setImageResource(it.img_animal)
            } else {
                ivDetailImg.setImageResource(R.drawable.default_img)
            }
        }
    }

    private fun shareAnimalDetails() {
        val shareText = "Check out this animal:\n" +
                "Name: ${dataAnimal.name_animal}\n" +
                "Description: ${dataAnimal.desc_animal}"

        val sendIntent = Intent().apply {
            action = Intent.ACTION_SEND
            putExtra(Intent.EXTRA_TEXT, shareText)
            type = "text/plain"
        }

        val shareIntent = Intent.createChooser(sendIntent, null)
        startActivity(shareIntent)
    }

}