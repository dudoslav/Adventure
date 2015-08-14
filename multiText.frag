#version 130
uniform sampler2D grass;
uniform sampler2D dirt;
uniform sampler2D rock;
smooth in vec2 texcoord;
smooth in float h;

varying vec3 position;
varying vec3 normal;

//gl_FrontMaterial.ambient
//gl_LightSource[0].ambient

void main()
{
    vec4 texcolor = texture2D(dirt,texcoord);

    float zone0 = -20f;
    float zone1 = 0f;
    float zone2 = 20f;
    float zone3 = 35f;


    if(h < zone0) texcolor = texture2D(grass,texcoord);
    if(h >= zone0 && h < zone1)
    {
        float a1 = (h-zone0)/(zone1-zone0);
        float a2 = 1 - a1;
        texcolor = texture2D(grass,texcoord)*a2+texture2D(dirt,texcoord)*a1;
    }
    if(h >= zone1 && h < zone2) texcolor = texture2D(dirt,texcoord);
    if(h >= zone2 && h < zone3)
    {
        float a1 = (h-zone2)/(zone3-zone2);
        float a2 = 1 - a1;
        texcolor = texture2D(dirt,texcoord)*a2+texture2D(rock,texcoord)*a1;
    }
    if(h >= zone3) texcolor = texture2D(rock,texcoord);

    /*float dist=length(position-gl_LightSource[0].position.xyz);
    float att= 1.0/(1.0+0.1*dist+0.001*dist*dist);*/

    vec3 ambient=texcolor.xyz*gl_LightSource[0].ambient.xyz;

    vec3 surf2light=normalize(gl_LightSource[0].position.xyz-position);
    vec3 norm=normalize(normal);
    float dcont=max(0.0,dot(norm,surf2light));
    vec3 diffuse=dcont*texcolor.xyz*gl_LightSource[0].diffuse.xyz;

    vec3 surf2view=normalize(-position);
    vec3 reflection=reflect(-surf2light,norm);
    float scont=pow(max(0.0,dot(surf2view,reflection)),1.0);
    vec3 specular=scont*gl_FrontMaterial.specular.xyz*gl_LightSource[0].specular.xyz;

    vec4 final = vec4((ambient+diffuse+specular),1.0);

    gl_FragColor=texcolor*final;
}