package ie.setu.wildswimming.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.recyclerview.widget.LinearLayoutManager
import ie.setu.swimspot.adapters.SwimspotAdapter
import ie.setu.wildswimming.R
import ie.setu.wildswimming.databinding.ActivitySwimspotListBinding
import ie.setu.wildswimming.main.WildSwimmingApp


class SwimspotList : AppCompatActivity() {

    lateinit var app: WildSwimmingApp
    lateinit var swimspotListLayout : ActivitySwimspotListBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        swimspotListLayout = ActivitySwimspotListBinding.inflate(layoutInflater)
        setContentView(swimspotListLayout.root)

        app = this.application as WildSwimmingApp
        swimspotListLayout.recyclerView.layoutManager = LinearLayoutManager(this)
        swimspotListLayout.recyclerView.adapter = SwimspotAdapter(app.swimspotsStore.findAll())
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {

        menuInflater.inflate(R.menu.menu_swimspotlist, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        return when (item.itemId) {
            R.id.action_swimspot -> { startActivity(
                Intent(this,
                    Swimspot::class.java)
            )
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}