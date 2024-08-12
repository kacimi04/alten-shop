# build Back-end image
- run mvn clean compile install 
- copy target/alten-shop-back-0.0.1-SNAPSHOT.jar to depoly/backend
- upload deploy/backed folder to server using sftp client
- run docker build -t altenshop . to build backed image
# build front-end image
- make sur to change server ip adress in environement.prod.ts according to your server adress
- run ng build --configuration production
- copy dist folder to deploy/frontend
- upload deploy/frontend to server using sftp client 
- run docker build -t altenshopfront . to build front end image
# run app
- copy docker-compose.yml to server using sftp client 
- rund docker-compose up -d to run application
# Important 
- change the value allowed.cors.origins in application-dev.properties according to you server address ip
- on first startup , make sur to connect to the database container remotly and create the database alten , so Back-end can start normally 
