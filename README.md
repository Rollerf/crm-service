# crm-service
## Setup:
### Install project:
This is a maven project, you can import it in your favorite ide. You must install the database before run the project.

### DataBase installation:
Create volume:
```bash
docker volume create crm-service-db-data
```

Create postgre database using the volume created before:
```bash
docker run -d -p 33070:5432 --name crm-service-db  -e POSTGRES_PASSWORD=password --mount src=crm-service-db-data,dst=/var/lib/postgres postgres
```

After that you have to make the database inserts, with the roles and admin user for login. I let an insert.sql document for it.

### Deployment project:
After you test the project, you can make a deployment using docker-compose.
First you must generate a jar file. You can use this command for generated it:
```bash
mvn clean install -DskipTests
```

Now you can use docker-compose to run the project:
```bash
docker-compose up 
```

After that you have to make the database inserts, with the roles and admin user for login. I let an insert.sql document for it.

### Tests for the project:
I left some tests in postman:\
[![Run in Postman](https://run.pstmn.io/button.svg)](https://app.getpostman.com/run-collection/11372990-0f9db1e9-2b8c-4355-95dd-5faa226d01e4?action=collection%2Ffork&collection-url=entityId%3D11372990-0f9db1e9-2b8c-4355-95dd-5faa226d01e4%26entityType%3Dcollection%26workspaceId%3D42dae88e-6045-4387-8909-fa1cdd1a02e1#?env%5BLocal%5D=W3sia2V5IjoidXJsIiwidmFsdWUiOiJodHRwOi8vbG9jYWxob3N0OjgwODAiLCJlbmFibGVkIjp0cnVlfV0=)