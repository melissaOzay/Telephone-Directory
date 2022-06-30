package com.example.atildeneme

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import android.widget.SearchView
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton


class MainActivity : AppCompatActivity() {
    val REQUEST_CODE = 501
    var landmarklis: ArrayList<Landmark> = arrayListOf()

    val adapter = LandmarkAdapter(landmarklis)
    lateinit var recyclerView: RecyclerView
    lateinit var text:TextView
    lateinit var searchView: SearchView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        recyclerView = findViewById<RecyclerView>(R.id.recyclerView)
        text = findViewById<TextView>(R.id.empty_view)
        searchView = findViewById<SearchView>(R.id.searchView2)
        performSearch()
    }


    fun search(text: String?) {

        var resultList = ArrayList<Landmark>()

        for (Lan in landmarklis) {
            if (Lan.name.toString().contains(text.toString(), true) || Lan.surname.toString()
                    .contains(text.toString(), true)
            ) {
                resultList.add(Lan)

            }
        }
        adapter.setData(resultList)
        if (landmarklis.isEmpty()) {
            Toast.makeText(this, "No match found!", Toast.LENGTH_SHORT).show()
        }
    }

    fun performSearch() {

        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                search(query)
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                search(newText)
                return true
            }
        })




        recyclerView.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        recyclerView.adapter = adapter



        findViewById<FloatingActionButton>(R.id.button).setOnClickListener {
            val intent = (Intent(this, DetailsActivity::class.java))
            startActivityForResult(intent, REQUEST_CODE)


            if (landmarklis.isEmpty()) {
                recyclerView.visibility = (View.VISIBLE)
                text.visibility = (View.GONE)


            }

        }

        val itemTouchHelperCallback =
            object :
                ItemTouchHelper.SimpleCallback(
                    0,
                    ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT
                ) {
                override fun onMove(
                    recyclerView: RecyclerView,
                    viewHolder: RecyclerView.ViewHolder,
                    target: RecyclerView.ViewHolder
                ): Boolean {

                    return false
                }

                override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                    val position = viewHolder.adapterPosition
                    val alert = AlertDialog.Builder(this@MainActivity)
                    alert.setTitle("Rehber silme")
                    alert.setMessage("Are you sure")
                    alert.setPositiveButton("yes") { dialog, id ->
                        recyclerView.adapter!!.notifyItemRemoved(position)
                        landmarklis.removeAt(position)
                        Toast.makeText(this@MainActivity, "Bir veri silindi", Toast.LENGTH_LONG)
                            .show()

                        adapter.notifyDataSetChanged()


                    }

                    alert.setNegativeButton("no") { dialog, id ->
                        Toast.makeText(
                            this@MainActivity,
                            "Bir veri silinmedi",
                            Toast.LENGTH_LONG
                        ).show()
                        recyclerView.adapter!!.notifyItemRangeChanged(0, landmarklis.size)
                        dialog.cancel()

                    }
                    val build = alert.create()
                    build.show()

                }


            }
        val itemTouchHelper = ItemTouchHelper(itemTouchHelperCallback)
        itemTouchHelper.attachToRecyclerView(recyclerView)
    }


    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == 501 && data != null) {
            val kullaniciAdi = data?.getStringExtra("kullanici_adi")
            val soyad = data?.getStringExtra("kullanici_soyadi")
            val telefon = data?.getStringExtra("kullanici_telefon")
            val landMark = Landmark(kullaniciAdi, soyad, telefon)
            landmarklis.add(landMark)


        }

    }
}




