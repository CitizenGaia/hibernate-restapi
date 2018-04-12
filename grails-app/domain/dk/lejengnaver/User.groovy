package dk.lejengnaver

import grails.compiler.GrailsCompileStatic
import groovy.transform.EqualsAndHashCode

@GrailsCompileStatic
@EqualsAndHashCode(includes='username')
class User implements Serializable {

    private static final long serialVersionUID = 1

    String username
    String password
    String location
    Date createDate = new Date()

    static constraints = {
        username size: 6..24, blank: false
        password size: 6..24, blank: false
        location size: 0..24, blank: false, nullable: true
    }

    @Override
    String toString() {
        StringBuilder builder = new StringBuilder()
        builder.append("User")
        builder.append("[")
        builder.append("username:${this.username}")
        builder.append(",location:${this.location}")
        builder.append("]")
        return builder.toString()
    }

    static UserMarshaller = { User domain ->
        return [
                id: domain.id,
                version: domain.version,
                username: domain.username,
                location: domain.location,
                createDate: domain.createDate
        ]
    }

}
