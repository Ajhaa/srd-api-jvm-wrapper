package fi.ajhaa.api

import com.fasterxml.jackson.databind.InjectableValues
import com.fasterxml.jackson.databind.json.JsonMapper
import com.fasterxml.jackson.module.kotlin.KotlinModule
import fi.ajhaa.data.ApiObject
import fi.ajhaa.data.ApiRoot

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

    private val cache = hashMapOf<String, T>()

    private fun fromJson(json: String) : T {
        return jackson.readValue(json, targetClass)
    }

    private fun throughCache(index: String) : T {
        val cachedValue = cache[index]
        if (cachedValue != null) {
            return cachedValue
        }
        val response = api.request(basePath + index)
        val value = fromJson(response)
        cache[index] = value
        return value
    }

    private fun parseApiRoot(json: String) : List<T> {
        val root = jackson.readValue(json, ApiRoot::class.java)
        if (api.cache) return root.results.map { throughCache(it.index) }
        return root.results.map { fromJson(api.request(it.url)) }
    }

    /**
     * Returns a single object from the api
     * @param index used to identify resources
     * @return ApiObject
     */
    fun get(index: String) : T {
        if (api.cache) return throughCache(index)
        return fromJson(api.request(basePath + index))
    }

    fun getEager(index: String, init: T.() -> Unit) : T {
        val target = this.get(index)
        target.init()
        return target
    }

    fun list() : List<T> {
        val response = api.request(basePath)
        return parseApiRoot(response)
    }
}