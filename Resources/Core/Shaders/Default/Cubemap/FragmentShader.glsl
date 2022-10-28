in vec2 texCord;


uniform sampler2D cubeMap;

out vec4 outColor;

void main() {
    outColor = texture(cubeMap, texCord);
}