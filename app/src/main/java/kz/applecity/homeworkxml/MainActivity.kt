package kz.applecity.homeworkxml

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import kz.applecity.homeworkxml.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root

        setContentView(view)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val adapter = PopularAdapter()
        binding.rvPopular.adapter = adapter
        binding.rvPopular.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        adapter.submitList(
            listOf(
                PopularItem(
                    "Satoru Gedgi",
                    "203",
                    R.drawable.avatar
                ),
                PopularItem(
                    "Yougi Itadori",
                    "28",
                    R.drawable.item04
                ),
                PopularItem(
                    "Cool story",
                    "78",
                    R.drawable.item03
                ),
                PopularItem(
                    "Cool story",
                    "78",
                    R.drawable.item03
                ),
                PopularItem(
                    "Yougi Itadori",
                    "28",
                    R.drawable.item04
                ),
                PopularItem(
                    "Satoru Gedgi",
                    "203",
                    R.drawable.avatar
                ),
            )
        )

        val adapterReading = ReadingAdapter()
        binding.rvReadingList.adapter = adapterReading
        binding.rvReadingList.layoutManager = LinearLayoutManager(this)
        adapterReading.submitList(
            listOf(
                ReadingItem(
                    "How to promote business",
                    "Bella Gonza . 12 mins",
                    R.drawable.avatar,
                     "Enterpreuner"
                ),
                ReadingItem(
                    "right away in instagrami",
                    "Taker Carlson . 24 min",
                    R.drawable.item04,
                    "Enterpreuner"
                ),
                ReadingItem(
                    "How to promote businessright away in instagram",
                    "Taker Carlson . 24 min",
                    R.drawable.item03,
                    "Enterpreuner"
                ),
                ReadingItem(
                    "How to promote businessright away in instagram",
                    "Taker Carlson . 24 min",
                    R.drawable.item03,
                    "Enterpreuner"
                ),
                ReadingItem(
                    "How to promote businessright away in instagram",
                    "Taker Carlson . 24 min",
                    R.drawable.item04,
                    "Enterpreuner"
                ),
                ReadingItem(
                    "How to promote businessright away in instagram",
                    "Taker Carlson . 24 min",
                    R.drawable.avatar,
                    "Enterpreuner"
                ),
            )
        )
    }
}