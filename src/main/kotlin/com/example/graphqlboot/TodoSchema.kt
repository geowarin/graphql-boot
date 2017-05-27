package com.example.graphqlboot

import graphql.Scalars.GraphQLString
import graphql.schema.GraphQLArgument.newArgument
import graphql.schema.GraphQLFieldDefinition.newFieldDefinition
import graphql.schema.GraphQLList
import graphql.schema.GraphQLObjectType
import graphql.schema.GraphQLObjectType.newObject
import graphql.schema.GraphQLSchema
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration


@Configuration
class TodoSchema {

  val todos = listOf(
      Todo("Hello"),
      Todo(name = "Helli", image = Image("D:/lib/toto.jpg")),
      Todo("Hella")
  )

  data class Todo(val name: String, val image: Image? = null)
  data class Image(val path: String)

  @Bean
  fun schema(): GraphQLSchema {

    val imageType = GraphQLObjectType.newObject()
        .name("Image")
        .field(
            newFieldDefinition()
                .name("path")
                .type(GraphQLString)
        ).
        build()

    val todoType = GraphQLObjectType.newObject()
        .name("Todo")
        .field(
            newFieldDefinition()
                .name("name")
                .type(GraphQLString)
        )
        .field(
            newFieldDefinition()
                .name("image")
                .type(imageType)
        )
        .build()

    val queryType2 = newObject()
        .name("todo")
        .field(
            newFieldDefinition()
                .type(GraphQLList(todoType))
                .name("todo")
                .description("The todos 🤗")
                .argument(
                    newArgument()
                        .name("name")
                        .description("filter by name")
                        .type(GraphQLString)
                )
                .dataFetcher({ env ->
                  val args = env.arguments
                  val name = args["name"] as String?
                  if (name == null) {
                    todos
                  } else {
                    todos.filter { it.name.toLowerCase().contains(name.toLowerCase()) }
                  }
                })
        )
        .build()


    val schema = GraphQLSchema.newSchema()
        .query(queryType2)
        .build()

    return schema
  }
}
