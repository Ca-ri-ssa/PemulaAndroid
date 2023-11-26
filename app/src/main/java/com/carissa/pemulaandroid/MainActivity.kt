package com.carissa.pemulaandroid

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {
    private lateinit var rvAnimal: RecyclerView
    private val list = ArrayList<Animal>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        rvAnimal = findViewById(R.id.rv_animal)
        rvAnimal.setHasFixedSize(true)

        list.addAll(getListAnimal())
        showRecyclerList()
    }

    @SuppressLint("Recycle")
    private fun getListAnimal(): ArrayList<Animal> {
        val animalDataName = resources.getStringArray(R.array.data_name_animal)
        val animalDataDesc = resources.getStringArray(R.array.data_desc_animal)
        val animalDataImg = resources.obtainTypedArray(R.array.data_img_animal)
        val animalDataExtra = resources.getStringArray(R.array.data_extra_animal)
        val animalDataTags = resources.getStringArray(R.array.data_tags_animal)
        val animalDataOrder = resources.getStringArray(R.array.data_order_animal)
        val animalDataClass = resources.getStringArray(R.array.data_class_animal)
        val animalDataFamily = resources.getStringArray(R.array.data_family_animal)
        val listAnimal = ArrayList<Animal>()
        for (i in animalDataName.indices) {
            val animal =
                Animal(animalDataName[i], animalDataDesc[i], animalDataImg.getResourceId(i, -1), animalDataExtra[i], animalDataTags[i].split(",").map { it.trim() }, animalDataClass[i], animalDataFamily[i], animalDataOrder[i])
            listAnimal.add(animal)
        }
        return listAnimal
    }

    private fun showRecyclerList() {
        rvAnimal.layoutManager = LinearLayoutManager(this)
        val listAnimalAdapter = ListAnimalAdapter(list)
        rvAnimal.adapter = listAnimalAdapter

        listAnimalAdapter.setOnItemClickCallback(object : ListAnimalAdapter.OnItemClickCallback {
            override fun onItemClicked(data: Animal) {
                showChosenAnimal(data)
                openDetailActivity(data)
            }
        })
    }

    private fun openDetailActivity(animal: Animal) {
        val intent = Intent(this, DetailActivity::class.java)
        intent.putExtra("key_animal", animal)
        startActivity(intent)
    }


    private fun showChosenAnimal(animal: Animal) {
        Toast.makeText(this, "You choose " + animal.name_animal, Toast.LENGTH_SHORT).show()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.action_list -> {
                rvAnimal.layoutManager = LinearLayoutManager(this)
            }
            R.id.action_grid -> {
                rvAnimal.layoutManager = GridLayoutManager(this, 2)
            }
            R.id.about_page -> {
                Thread {
                    val intent = Intent(this, AboutActivity::class.java)
                    startActivity(intent)
                }.start()
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }
}