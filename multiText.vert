#version 130
smooth out vec2 texcoord;
smooth out float h;
varying vec3 position;
varying vec3 normal;

void main()
{
    texcoord = gl_MultiTexCoord0.xy;
    h = gl_Vertex.y;
    gl_Position=ftransform();
    position =  vec3(gl_ModelViewMatrix*gl_Vertex);
    normal = vec3(gl_NormalMatrix*gl_Normal);
}