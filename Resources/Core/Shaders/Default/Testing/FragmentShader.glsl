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


out vec4 outColor;

void main() {
    outColor = texture(material.Texture, texCord);
}