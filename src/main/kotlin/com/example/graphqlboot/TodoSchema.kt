package com.example.graphqlboot

import graphql.Scalars.GraphQLString
import graphql.schema.GraphQLFieldDefinition.newFieldDefinition
import graphql.schema.GraphQLInterfaceType
import graphql.schema.GraphQLList
import graphql.schema.GraphQLObjectType
import graphql.schema.GraphQLObjectType.newObject
import graphql.schema.GraphQLSchema
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration


@Configuration
class TodoSchema {

  data class Todo(val name: String)

  @Bean
  fun schema(): GraphQLSchema {


    val todoType = GraphQLObjectType.newObject()
        .name("Todo")
        .field(
            newFieldDefinition()
                .type(GraphQLString)
                .name("name")
        )
        .build()

    val queryType2 = newObject()
        .name("todo")
        .field(
            newFieldDefinition()
                .type(GraphQLList(todoType))
                .name("todo")
                .dataFetcher({ _ ->
                  listOf(
                      Todo("hello")
                  )
                })
        )
        .build()


    val schema = GraphQLSchema.newSchema()
        .query(queryType2)
        .build()

    return schema
  }

//  @GraphQLSchemaQuery
//  private val root: RootObjectType? = null
//
//  // default value provider for an input mutation parameter
//  val addTodoInputDefaultValue: AddTodoIn
//    get() {
//      val addTodoInput = AddTodoIn()
//      return addTodoInput.copy(text = "--- default text ---")
//    }
//
//  @GraphQLMutation
//  @GraphQLOut("todoEdge")
//  fun addTodoMutation(
//      @GraphQLIn(value = "addTodoInput", defaultProvider = "getAddTodoInputDefaultValue") addTodoInput: AddTodoIn): TodoObjectType.TodoEdgeObjectType {
//
//    // mutation implementaion goes here
//    return todoEdgeObjectType
//  }
}

//data class AddTodoIn (
//  val text: String? = null
//)