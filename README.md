# Курсовая работа по предмету Программная инженерия - Сервис доставки

Пестерников Данил (М8О-114М) - Вариант 6 - Сервис доставки

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
