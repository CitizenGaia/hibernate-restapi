package dk.lejengnaver

import grails.compiler.GrailsCompileStatic

@GrailsCompileStatic
class Game {

    String title
    String author
    String description
    Map<String, String> content
    Date createDate = new Date()

    static constraints = {
        title size: 5..15, blank: false
        author size: 3..15, blank: false
        description nullable: true, blank: false, size: 0..100
        content nullable: false
    }

    @Override
    String toString() {
        StringBuilder builder = new StringBuilder()
        builder.append("Game")
        builder.append("[")
        builder.append("title:${this.title}")
        builder.append(",author,${this.author}")
        builder.append(",description,${this.description}")
        builder.append(",cubes,${this.content?.size()}")
        builder.append("]")
        return builder.toString()
    }

    static GameMarshaller = { Game domain ->
        def dataContent = [:]
        dataContent.putAll(domain.content)
        return [
                id: domain.id,
                version: domain.version,
                title: domain.title,
                auhor: domain.author,
                description: domain.description,
                content: dataContent,
                createdate: domain.createDate
        ]
    }

}
