in vec4 color;
in vec2 texCord;
in vec3 Normal;
in vec4 WorldPos;
in mat4 viewMatrix;

#define NR_POINT_LIGHTS 100

struct Material {
    sampler2D Texture;
    vec3 ambient;
    sampler2D  diffuse;
    sampler2D  specular;
    float shininess;
    float reflectivenes;
    sampler2D ReflectionsMap;
};

struct Sun {
    vec3 position;
    int intensity;
    vec4 color;
};

struct PointLight {
    vec3 position;
    int intensity;
    vec4 color;
    vec3 attenuation;
};

struct LevelLightData {
    Sun sunlight;
    PointLight pointlights[NR_POINT_LIGHTS];
};


uniform Material material;
/*uniform LevelLightData levelLightData;
uniform int pointLightsIntheLevel;
uniform bool VolumLight;
*/

out vec4 outColor;
/*
vec4 Defuse(vec3 normal, vec3 pos, float intensity, vec4 color) {
    vec3 nomr = normalize(normal);
    vec3 lightpos =  normalize(pos - WorldPos.xyz);
    float dotProdoct = dot(nomr, lightpos);
    float maxproduct = max(dotProdoct, 0.06);

    maxproduct = maxproduct * intensity;

    vec3 result = vec3(maxproduct,maxproduct,maxproduct);

    if(color.xyz != vec3(1,1,1)) {
        result = vec3(maxproduct,maxproduct,maxproduct) * color.xyz;
    }

    return vec4(result, 1.0);
}


vec4 DefuseWithAtten(vec3 normal, vec3 pos, float intensity, vec4 color, vec3 attenuation) {

    vec3 nomr = normalize(normal);
    vec3 lightpos =  normalize(pos - WorldPos.xyz);

    float distanc = length(pos - WorldPos.xyz);
    float atten = attenuation.x + (attenuation.y * distanc) +  (attenuation.z * distanc * distanc);

    float dotProdoct = dot(nomr, lightpos);
    float maxproduct = max(dotProdoct, 0.06);

    maxproduct = maxproduct * intensity;

    vec3 result = vec3(maxproduct,maxproduct,maxproduct);

    if(color.xyz != vec3(1,1,1)) {
        result = vec3(maxproduct,maxproduct,maxproduct) * color.xyz;
    }

    return vec4(result, 1.0) / atten;
}

vec4 calculateSunlight() {
    return Defuse(Normal, levelLightData.sunlight.position, levelLightData.sunlight.intensity, levelLightData.sunlight.color);
}


vec4 calculatespecularLighting(sampler2D specular,vec3 normal, vec3 pos, vec4 color, float intesity) {
    vec3 nomr = normalize(normal);
    vec3 reflectDir = reflect(nomr, pos + WorldPos.xyz);
    float spec = pow(max(dot(nomr, reflectDir), 0.0), material.shininess);
    vec3 s = spec * vec3(texture(material.specular, texCord));


    if(color.xyz != vec3(1,1,1)) {
        s = spec * vec3(texture(material.specular, texCord)) * color.xyz;
    }
    return vec4(s,1.0) * intesity;
}


vec4 calculatespecularLightingWithAten(sampler2D specular,vec3 normal, PointLight light) {
    vec3 nomr = normalize(normal);
    vec3 reflectDir = reflect(nomr, light.position + WorldPos.xyz);
    float spec = pow(max(dot(nomr, reflectDir), 0.0), material.shininess);
    vec3 s = spec * vec3(texture(material.specular, texCord));

    float distanc = length(light.position  - WorldPos.xyz);
    float atten = light.attenuation.x + ( light.attenuation.y * distanc) +  ( light.attenuation.z * distanc * distanc);

    if(color.xyz != vec3(1,1,1)) {
        s = spec * vec3(texture(material.specular, texCord)) * color.xyz;
    }
    return (vec4(s,1.0) * light.intensity) / atten;
}

vec4 reflections(){
    vec3 I      = normalize( WorldPos.xyz);
    vec3 viewR  = reflect(I, normalize(Normal));
    vec3 worldR = inverse(mat3(viewMatrix)) * viewR * material.reflectivenes;
    return vec4(worldR * vec3(texture(material.ReflectionsMap, texCord)),1.0);
}
*/
void main() {
    outColor = texture(material.Texture, texCord);

/*
    if(material.shininess > 0) {
        vec3 ambient =  material.ambient * texture(material.Texture, texCord).xyz;

        vec4 totalLighting = calculateSunlight();

        for (int i = 0; i < pointLightsIntheLevel;i ++) {
            totalLighting = totalLighting + DefuseWithAtten(Normal, levelLightData.pointlights[i].position, levelLightData.pointlights[i].intensity, levelLightData.pointlights[i].color, levelLightData.pointlights[i].attenuation)
            + calculatespecularLightingWithAten(material.specular, Normal, levelLightData.pointlights[i])
            ;
        }

        totalLighting = totalLighting + calculatespecularLighting(material.specular, Normal, levelLightData.sunlight.position, levelLightData.sunlight.color, levelLightData.sunlight.intensity * material.shininess);

        outColor = reflections() +  totalLighting * vec4(ambient, 1.0);
    } else{
        vec3 ambient =  material.ambient * texture(material.Texture, texCord).xyz;

        vec4 totalLighting = calculateSunlight();

        for (int i = 0; i < pointLightsIntheLevel;i ++) {
            totalLighting = totalLighting + DefuseWithAtten(Normal, levelLightData.pointlights[i].position, levelLightData.pointlights[i].intensity, levelLightData.pointlights[i].color, levelLightData.pointlights[i].attenuation);

        }

        outColor =totalLighting * vec4(ambient, 1.0);
    }

*/


}