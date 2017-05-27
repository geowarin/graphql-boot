import gql from "graphql-tag";
import * as React from "react";
import {graphql} from "react-apollo";

interface TodosViewProps {
  data?: any
}

// The data prop, which is provided by the wrapper below contains,
// a `loading` key while the query is in flight and posts when it is ready
export const TodosView = ({data: {loading, todo, error}}: TodosViewProps) => {
  if (loading) return <div>Loading</div>;
  if (error) return <h1>ERROR</h1>;
  return (
    <div>
      <h3>Todos</h3>
      <ul>
        {todo && (
          todo.map((t, index) => (
              <li key={index}>{t.name}</li>
            )
          )
        )}
      </ul>
    </div>
  );
};

export const TODO_QUERY = gql`
  query GetTodos($name: String!) {
    todo(name: $name) {
      name
    }
  }
`;

// The `graphql` wrapper executes a GraphQL query and makes the results
// available on the `data` prop of the wrapped component (Pokemon here)
export const withTodo = graphql(TODO_QUERY, {
  options: {
    variables: {name: "hell"},
  }
});

export default withTodo(TodosView);
