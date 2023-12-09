package ie.setu.wildswimming.models

interface SwimspotStore {
    fun findAll(): List<SwimspotModel>
    fun findById(id:Long) : SwimspotModel?
    fun create(swimspot: SwimspotModel)

    //fun update(swimspot: SwimspotModel)
    //fun delete(swimspot: SwimspotModel)

}