in vec3 position;
in vec4 incolor;
in vec2 textureCord;
in vec3 normal;

uniform mat4 transform;
uniform mat4 ProjectionMatrix;
uniform mat4 ViewMatrix;

out vec4 color;
out vec2 texCord;
out vec3 Normal;
out vec4 WorldPos;
out mat4 viewMatrix;

void main() {
    vec4 worldPos =  transform * vec4(position, 1.0f);
    gl_Position =  ProjectionMatrix * ViewMatrix * transform  * vec4(position, 1.0f) ;
    color = incolor;
    texCord = textureCord;
    Normal = vec3(ProjectionMatrix * transform * vec4(normal + position,1.0));
    WorldPos = worldPos;
    viewMatrix = ViewMatrix;
}