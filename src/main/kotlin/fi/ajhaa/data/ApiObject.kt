package fi.ajhaa.data

import fi.ajhaa.api.SRDApi

abstract class ApiObject {
    protected var api: SRDApi? = null

    open fun initApi(api: SRDApi) {
        this.api = api
    }
}

