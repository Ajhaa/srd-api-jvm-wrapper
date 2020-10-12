package fi.ajhaa.api

import com.fasterxml.jackson.databind.InjectableValues
import com.fasterxml.jackson.databind.json.JsonMapper
import com.fasterxml.jackson.module.kotlin.KotlinModule
import fi.ajhaa.data.ApiObject

/**
 *  Class for a specific resource in the SRD api, such as class or trait
 *  @param basePath relative path to the resource
 *  @param targetClass the JVM class for the resource
 *  @param api the base api that is used to send the requests
 */
class Api<T: ApiObject>(
    private val basePath: String,
    private val targetClass: Class<T>,
    private val api: SRDApi
) {
    private val jackson = JsonMapper.builder()
        .addModule(KotlinModule())
        .injectableValues(InjectableValues.Std().addValue("api", api))
        .build()

    private fun fromJson(json: String) : T {
        return jackson.readValue(json, targetClass)
    }

    /**
     * Returns a single object from the api
     * @param index used to identify resources
     * @return ApiObject
     */
    fun get(index: String) : T {
        val response = api.request(basePath + index)
        val result = fromJson(response)
        //result.initApi(api)
        return result
    }
}