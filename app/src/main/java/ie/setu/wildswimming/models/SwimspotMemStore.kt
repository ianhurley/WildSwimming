package ie.setu.wildswimming.models

import timber.log.Timber.Forest.i

var lastId = 0L

internal fun getId(): Long {
    return lastId++
}

class SwimspotMemStore : SwimspotStore {

    val swimspots = ArrayList<SwimspotModel>()

    override fun findAll(): List<SwimspotModel> {
        return swimspots
    }

    override fun create(swimspot: SwimspotModel) {
        swimspot.id = getId()
        swimspots.add(swimspot)
        logAll()
    }

    /*override fun update(swimspot: SwimspotModel) {
        var foundSwimspot: SwimspotModel? = swimspots.find { p -> p.id == swimspot.id }
        if (foundSwimspot != null) {
            foundSwimspot.name = swimspot.name
            foundSwimspot.county = swimspot.county
            foundSwimspot.categorey = swimspot.categorey
            foundSwimspot.photo = swimspot.photo
            foundSwimspot.lat = swimspot.lat
            foundSwimspot.lng = swimspot.lng
            foundSwimspot.zoom = swimspot.zoom
            logAll()
        }
    }

    override fun delete(swimspot: SwimspotModel) {
        swimspots.remove(swimspot)
    }*/

    override fun findById(id:Long) : SwimspotModel? {
        val foundSwimspot: SwimspotModel? = swimspots.find { it.id == id }
        return foundSwimspot
    }

    fun logAll() {
        i("** Swimspot List **")
        swimspots.forEach { i("Swimspot ${it}") }
        //swimspots.forEach{ i("${it}") }
    }
}