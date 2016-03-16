#version 130
out vec2 texcoord;
out float h;
varying vec3 position;
varying vec3 normal;

void main()
{
    texcoord = gl_MultiTexCoord0.xy;
    h = gl_Vertex.y;
    vec4 vertex = gl_Vertex;
    vertex.y = vertex.y + (vertex.x + vertex.z) / 4;
    gl_Position=gl_ProjectionMatrix * gl_ModelViewMatrix * vertex;
    position = vec3(gl_ModelViewMatrix*gl_Vertex);
    normal = vec3(gl_NormalMatrix*gl_Normal);
}