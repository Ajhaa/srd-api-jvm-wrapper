package fi.ajhaa.data

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonProperty

@JsonIgnoreProperties(ignoreUnknown = true)
class Equipment private constructor(
        val index: String,
        val name: String,
        @JsonProperty("equipment_category")
        private val equipmentCategoryRef: ApiReference<EquipmentCategory>,
        val cost: Cost?,
        private val url: String
) : ApiObject() {
    val equipmentCategory: EquipmentCategory by lazy {
        api.equipmentCategories.get(equipmentCategoryRef.index)
    }
}

@JsonIgnoreProperties(ignoreUnknown = true)
class EquipmentCategory private constructor(
        val index: String,
        val name: String,
        @JsonProperty("equipment")
        private val equipmentRef: List<ApiReference<Equipment>>,
        private val url: String
) : ApiObject() {
    val equipment: List<Equipment> by lazy {
        val list = mutableListOf<Equipment>()
        for (ref in equipmentRef) {
            if (ref.url.contains("magic-items")) {
                list.add(api.magicItems.get(ref.index))
            } else {
                list.add(api.equipment.get(ref.index))
            }
        }

        list
    }
}

@JsonIgnoreProperties(ignoreUnknown = true)
class Cost private constructor(
        val quantity: Int,
        val unit: String
) : ApiObject() {
}
