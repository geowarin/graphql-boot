import * as React from 'react';
import ApolloClient, { createNetworkInterface } from 'apollo-client';
import { ApolloProvider } from "react-apollo";

import Todos from "./Todos";

export const networkInterface = createNetworkInterface({ uri: 'http://localhost:8080/graphql' });
export const client = new ApolloClient({ networkInterface });

class App extends React.Component<{}, {}> {
  render() {
    return (
      <ApolloProvider client={client}>
        <Todos />
      </ApolloProvider>
    );
  }
}

export default App;
