Analyze "gestao_vagas": sqp_8fa3411ec07439b04518e62d48d829ae2499c491


sudo docker run -d -e SONAR_ES_BOOTSTRAP_CHECKS_DISABLE=true -p 9000:9000 sonarqube:9.9.0-community

mvn clean verify sonar:sonar   -Dsonar.projectKey=gestao_vagas2   -Dsonar.host.url=http://localhost:9000   -Dsonar.login=sqp_e5873457fd5c9eec852a7cbf261dab80af040d9b