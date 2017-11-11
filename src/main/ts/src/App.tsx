import * as React from 'react';
import {HttpLink} from "apollo-link-http";
import {ApolloProvider} from "react-apollo";
import {InMemoryCache} from "apollo-cache-inmemory";
import ApolloClient from "apollo-client/ApolloClient";
import Todos from "./Todos";

const httpLink = new HttpLink({uri: 'http://localhost:8080/graphql'});
const client = new ApolloClient({
  link: httpLink,
  cache: new InMemoryCache(),
});


class App extends React.Component<{}, {}> {
  render() {
    return (
      <ApolloProvider client={client}>
        <Todos/>
      </ApolloProvider>
    );
  }
}

export default App;
