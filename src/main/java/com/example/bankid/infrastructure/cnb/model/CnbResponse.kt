package com.example.bankid.infrastructure.cnb.model

import jakarta.xml.bind.annotation.XmlAccessType
import jakarta.xml.bind.annotation.XmlAccessorType
import jakarta.xml.bind.annotation.XmlAttribute
import jakarta.xml.bind.annotation.XmlElement
import jakarta.xml.bind.annotation.XmlRootElement

@XmlRootElement(name = "kurzy")
@XmlAccessorType(XmlAccessType.FIELD)
data class CnbResponse(
    @field:XmlElement(name = "tabulka")
    var tabulka: Tabulka? = null
) {
    constructor() : this(null)
}

@XmlAccessorType(XmlAccessType.FIELD)
data class Tabulka(
    @field:XmlElement(name = "radek")
    var radky: List<Radek>? = null
) {
    constructor() : this(null)
}

@XmlAccessorType(XmlAccessType.FIELD)
data class Radek(
    @field:XmlAttribute(name = "kod")
    var kod: String = "",

    @field:XmlAttribute(name = "mena")
    var mena: String = "",

    @field:XmlAttribute(name = "mnozstvi")
    var mnozstvi: Int = 1,

    @field:XmlAttribute(name = "kurz")
    var kurz: String = "",

    @field:XmlAttribute(name = "zeme")
    var zeme: String = ""
) {
    constructor() : this("", "", 1, "", "")
}