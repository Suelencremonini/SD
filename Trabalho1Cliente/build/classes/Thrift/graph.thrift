namespace java graph

struct Edges{

    1: i64 V1id,
    2: i64 V2id,
    3: double Weight,
    4: i32 flag,
    5: string description

}

struct Vertex{

    1: i64 name,
    2: i64 color,
    3: string description,
    4: double weight

}

service Graph{

    void CreateEdges(1: Edges edges),
    void CreateVertex(1: Vertex vertex),
    void ReadVertex(1: i32 vertex),
    void ReadEdges(1: i32 vertex1, 2: i32 vertex2),
    void DeleteEdges(1: i32 vertex1, 2: i32 vertex2),
    void DeleteVertex(1: i32 vertex1),
    void GetEdges(),
    void GetVertex(),
    void GetVertexEdges(1: i32 vertex1, 2: i32 vertex2),
    void GetEdgesVertex(1: i32 name),
    void GetAdjacentVertex(1: i32 name),

}
