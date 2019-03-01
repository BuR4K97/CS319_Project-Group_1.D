#version 150 core

struct DiffuseLighting {
	vec3 diffuseColor;
	vec3 direction;
	float diffuseIntensity;
};

uniform highp mat4 modelMatrix;
uniform highp mat4 viewMatrix;
uniform highp mat4 projectionMatrix;

//uniform vec3 diffuseColor_in;
//uniform vec3 direction_in;
//uniform float diffuseIntensity_in;

in highp vec4 positionAttr;
in highp vec4 colorAttr;
in highp vec3 vertexNormal;

out highp vec4 frag_color;
out highp vec3 frag_normal;

void main() {

	gl_Position = projectionMatrix * viewMatrix * modelMatrix * positionAttr;
	
	frag_color = colorAttr;
	frag_normal = mat3(modelMatrix) * vertexNormal;
}