in vec4 color;
in vec2 texCord;
in vec4 WorldPos;
in mat4 viewMatrix;

struct Material {
    sampler2D Texture;
    sampler2D  diffuse;
    sampler2D  specular;
    sampler2D ReflectionsMap;
};


uniform Material material;
uniform bool DrawTex;


out vec4 outColor;

void main() {
    if(DrawTex){
        outColor = texture(material.Texture, texCord);
    } else {
        outColor = vec4(1,0,0,1);
    }
}