in vec3 position;
in vec2 textureCord;


uniform mat4 ProjectionMatrix;
uniform mat4 ViewMatrix;

out vec2 texCord;


void main() {
    gl_Position =  ProjectionMatrix * ViewMatrix  * vec4(position, 1.0f) ;
    texCord = textureCord;
}