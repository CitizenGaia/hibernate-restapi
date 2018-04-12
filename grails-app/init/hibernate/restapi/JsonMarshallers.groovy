package hibernate.restapi

import dk.lejengnaver.Game
import grails.converters.JSON

class JsonMarshallers {

    static init() {
        JSON.registerObjectMarshaller(Map) {
            def returnArray = [:]
            it.asImmutable().each { key, value ->
                "${key} : ${value}"
                return [
                        game: returnArray
                ]
            }
        }
        JSON.registerObjectMarshaller(Game, Game.GameMarshaller)
    }

}
