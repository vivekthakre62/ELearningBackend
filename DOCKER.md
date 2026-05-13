# Docker Setup

## Start the full project

```bash
docker compose --env-file .env.example up --build
```

## Services

- App: http://localhost:8080
- MySQL: localhost:3307
- Redis: localhost:6379

## Notes

- Uploaded files are persisted in the `uploads_data` Docker volume.
- MySQL data is persisted in the `mysql_data` Docker volume.
- Redis data is persisted in the `redis_data` Docker volume.
- Update `.env.example` values or create your own `.env` file before running in shared environments.
