#version 430 core

in vec2 position;
in vec4 incolor;

uniform vec2 CenterPos;

out vec2 textureCoords;
out vec4 color;

uniform mat4 transformationMatrix;

void main(void){

    gl_Position =  transformationMatrix   * vec4(position, 0.0, 1.0);
    textureCoords = vec2((position.x+1.0)/2.0, 1 - (position.y+1.0)/2.0) + CenterPos;
    color = incolor;
}