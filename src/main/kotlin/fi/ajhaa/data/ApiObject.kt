package fi.ajhaa.data

import com.fasterxml.jackson.annotation.JacksonInject
import fi.ajhaa.api.SRDApi

abstract class ApiObject {
    @JacksonInject("api")
    protected lateinit var api: SRDApi
}

