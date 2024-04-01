# Курсовая работа по предмету Программная инженерия - Сервис доставки

Пестерников Данил (М8О-114М) - Вариант 6 - Сервис доставки

## Технологии

- MapStruct - для маппинга объектов
- Lombok - инструмент для уменьшения шаблонного кода
- Spring Web - для разработки веб-приложений
- Spring Data Jpa - для упрощения доступа и взаимодействия с базой данных через ORM.
- Spring Security - для обеспечения безопасности веб-приложений
- Springdoc - инструмент для автоматической генерации документации к API
- Postgres - реляционная база данных для хранения данных
- Liquibase - инструмент для управления миграциями баз данных

## Запуск приложения

1. Клонируйте репозиторий: `git clone https://github.com/E6L1PS/delivery-api`
2. Перейдите в директорию проекта: `cd delivery-api`
3. Если требуется, измените файлы конфигурации `.env`, `application.yml`.
4. Запустите контейнеры: `docker compose up --build -d`
5. OpenApi Specification `http://localhost:8080/swagger-ui/index.html`
6. Пользователь с ролью `ADMIN`: `username: admin, password: admin`

P.S В папке ресурсов находятся все основные конфигурационные файлы, скрипты, changelog'и, csv-фалы, index.yaml:
path: `user-service/src/main/resource`
## Структура работы

Task-1:

1. Создать файлы с описанием «архитектуры» согласно вашему варианту задания
   в Structurizr Lite.
2. Требования к диаграммам:
    - Должна быть контекстная диаграмма
    - Должна быть диаграмма контейнеров
    - Должна быть диаграмма развертывания
    - Должно быть несколько динамических диаграмм

Task-2:
Должны выполняться следующие условия:

- Данные должны храниться в СУБД PostgreSQL;
- Должны быть созданы таблицы для каждой сущности из вашего задания;
- Интерфейс к сущностям должен предоставляться в соответствии со стилем REST;
- API должен быть специфицирован в OpenAPI 3.0 (должен хранится в index.yaml);
- Должен быть создан скрипт по созданию базы данных и таблиц, а также
  наполнению СУБД тестовыми значениями;
- Для сущности, отвечающей за хранение данных о пользователе (клиенте), для
  пользователей должен быть реализован интерфейс поиска по маске фамилии и
  имени, а также стандартные CRUD операции.
- Данные о пользователе должны включать логин и пароль. Пароль должен
  храниться в закрытом виде (хэширован)
