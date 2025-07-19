

sudo docker run -d -e SONAR_ES_BOOTSTRAP_CHECKS_DISABLE=true -p 9000:9000 sonarqube:9.9.0-community

mvn clean verify sonar:sonar   -Dsonar.projectKey=gestao_vagas   -Dsonar.host.url=http://localhost:9000   -Dsonar.login=sqp_fb0953d7d200963e13b2d2b803c1f0d9f9f5c006