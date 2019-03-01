#version 150 core

struct Lighting { //World-based lighting; apply only modelMatrix to normals but viewMatrix

	vec3 ambient_color;
	float ambient_intensity;
	
	vec3 light_direction;
	vec3 diffuse_color;
	float diffuse_intensity;
	
	vec3 specular_color;
	float specular_intensity;
};

in highp vec4 frag_color;
in highp vec3 frag_normal;

out vec4 out_color;

void main() {
	
	Lighting gLighting;
	gLighting.ambient_color = vec3(1.0f, 1.0f, 1.0f);
	gLighting.ambient_intensity = 0.3f;
	gLighting.light_direction = normalize(vec3(6.0f, 1.0f, 0.0f));
	gLighting.diffuse_color = vec3(1.0f, 1.0f, 1.0f);
	gLighting.diffuse_intensity = 3.0f;
	gLighting.specular_color = vec3(1.0f, 1.0f, 1.0f);
	gLighting.specular_intensity = 0.8f;
	
	float diffuse_factor = dot(normalize(frag_normal), -gLighting.light_direction);
	if(diffuse_factor < 0.0f) diffuse_factor = 0.0f;
	
	vec3 ambient_effect = gLighting.ambient_color * gLighting.ambient_intensity;
	vec3 diffuse_effect = gLighting.diffuse_color * gLighting.diffuse_intensity * diffuse_factor;
	vec4 light_effect = vec4(ambient_effect + diffuse_effect, 1.0f);
	
	out_color = frag_color * light_effect;
}