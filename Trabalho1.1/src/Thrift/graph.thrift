//para rodar, fazer thrift-0.9.3.exe -gen java graph.thrift

namespace java Thrift

struct Edges{

    1: i64 V1id,
    2: i64 V2id,
    3: double Weight,
    4: i32 flag,
    5: string description

}

struct Vertex{

    1: i64 name,
    2: i32 color,
    3: string description,
    4: double weight

}

struct Server{
    1: string ip,
    2: i32 portNumber
}

service Graph{

    void CreateEdges(1: Edges edges),
    void CreateVertex(1: Vertex vertex),
    Vertex ReadVertex(1: i32 vertex),
    Edges ReadEdges(1: Edges edge),
    void DeleteEdges(1: i32 vertex1, 2: i32 vertex2),
    void DeleteVertex(1: i32 vertex1),
    void UpdateEdgesWeight(1: double weight, 2: i32 vertex1, 3: i32 vertex2),
    void UpdateEdgesFlag(1: i32 flag, 2: i32 vertex1, 3: i32 vertex2),
    void UpdateEdgesDescription(1: string description, 2: i32 vertex1, 3: i32 vertex2),
    void UpdateVertexColor(1: i32 color, 2: i32 name),
    void UpdateVertexDescription(1: string description, 2: i32 name),
    void UpdateVertexWeight(1: double weight, 2: i32 name),
    set<Edges> GetEdges(),
    list<Vertex> GetVertex(),
    list<Vertex> GetVertexEdges(1: i32 vertex1, 2: i32 vertex2),
    list<Edges> GetEdgesVertex(1: i32 name),
    list<Vertex> GetAdjacentVertex(1: i32 name),
    void serverConnected(1: Server currentServer),
    map<i64, Server> comunicateConnectionToCentralServer(1: Server server, 2: bool connected),
    void setServersTable(1: map<i64, Server> serversTable),
    list<Vertex> getV(),
    list<Edges> getE(),
    bool existenceVertex(1: i64 name),
    bool existenceEdges(1: Edges edge),
    void actuallyDeleteEdge(1: Edges edge),
    void actuallyDeleteVertex(1: i32 vertex),
    void actuallyCreateEdge(1: Edges edge),
    void actuallyCreateVertex(1: Vertex vertex),
    void actuallyUpdateEdgesWeight(1: double weight, 2: i32 vertex1, 3: i32 vertex2),
    void actuallyUpdateEdgesFlag(1: i32 flag, 2: i32 vertex1, 3: i32 vertex2),
    void actuallyUpdateEdgesDescription(1: string description, 2: i32 vertex1, 3: i32 vertex2),
    void actuallyUpdateVertexColor(1: i32 color, 2: i32 name),
    void actuallyUpdateVertexDescription(1: string description, 2: i32 name),
    void actuallyUpdateVertexWeight(1: double weight, 2: i32 name),
    list<i32> Dijkstra(1: i32 source, 2: i32 goal)
}
