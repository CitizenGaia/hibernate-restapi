package hibernate.restapi

import dk.lejengnaver.Game
import dk.lejengnaver.User
import grails.converters.JSON

class JsonMarshallers {

    static init() {
        JSON.registerObjectMarshaller(Date) {
            return it?.format("dd-MM-yyyy")
        }
        JSON.registerObjectMarshaller(Map) {
            def returnArray = [:]
            it.asImmutable().each { key, value ->
                "${key} : ${value}"
                return [
                        game: returnArray
                ]
            }
        }
        JSON.registerObjectMarshaller(User, User.UserMarshaller)
        JSON.registerObjectMarshaller(Game, Game.GameMarshaller)
    }

}
