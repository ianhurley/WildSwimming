package ie.setu.wildswimming.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import ie.setu.wildswimming.R
import ie.setu.wildswimming.databinding.ActivitySwimspotBinding
import ie.setu.wildswimming.main.WildSwimmingApp
import ie.setu.wildswimming.models.SwimspotModel
import timber.log.Timber

class Swimspot : AppCompatActivity() {

    private lateinit var swimspotLayout: ActivitySwimspotBinding
    lateinit var app: WildSwimmingApp


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        app = this.application as WildSwimmingApp
        swimspotLayout = ActivitySwimspotBinding.inflate(layoutInflater)
        setContentView(swimspotLayout.root)

        swimspotLayout.buttonAddSwimspot.setOnClickListener() {
            val name = swimspotLayout.name.text.toString()
            val county = swimspotLayout.county.text.toString()
            val categorey = swimspotLayout.categorey.text.toString()


            val newSwimspot = SwimspotModel(
                name = name,
                county = county,
                categorey = categorey
            )

            app.swimspotsStore.create(newSwimspot)

            startActivity(Intent(this, SwimspotList::class.java))

        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {

        menuInflater.inflate(R.menu.menu_swimspot, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        return when (item.itemId) {
            R.id.action_swimspotlist -> { startActivity(Intent(this,
                SwimspotList::class.java))
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}