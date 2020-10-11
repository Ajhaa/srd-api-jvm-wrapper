package fi.ajhaa.data

import fi.ajhaa.api.SRDApi

abstract class ApiObject {
    protected var api: SRDApi? = null

    fun initApi(api: SRDApi) {
        this.api = api
    }
}

