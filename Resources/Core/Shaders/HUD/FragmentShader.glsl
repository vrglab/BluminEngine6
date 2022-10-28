#version 430 core

in vec2 textureCoords;
in vec4 color;

out vec4 outColor;

uniform sampler2D guiTexture;

void main(){
    vec4 tex = vec4(texture(guiTexture, textureCoords).xyz, color.w);


    if(color.w < 0.1 || texture(guiTexture, textureCoords).w < 0.1f) {
        discard;
    } else {
        if(color == vec4(1,1,1,1)) {
            outColor = texture(guiTexture, textureCoords);
        } else{
            outColor = tex * color;
        }
    }
}