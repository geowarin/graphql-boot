package com.example.graphqlboot

import graphql.GraphQL
import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.junit4.SpringRunner

@RunWith(SpringRunner::class)
@SpringBootTest
class GraphqlBootApplicationTests {

	@Autowired
	lateinit var schema: graphql.schema.GraphQLSchema

	@Test
	fun contextLoads() {
		val execute = GraphQL.newGraphQL(schema).build().execute("""
		{
			todo(name: "helli") {
				image{
					path
				}
			}
		}""")
		val todos: Map<Any, Any> = execute.getData()
		println(todos["todo"])
	}

}
