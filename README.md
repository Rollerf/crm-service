# crm-service
## Setup:
### Install project:
This is a maven project, you can import it in your favorite ide

### DataBase installation:
Create volume:
```bash
docker volume create crm-service-db-data
```

Create postgre database using the volume created before:
```bash
sudo docker run -d -p 33070:5432 --name crm-service-db  -e POSTGRES_PASSWORD=password --mount src=crm-service-db-data,dst=/var/lib/postgres postgres
```
