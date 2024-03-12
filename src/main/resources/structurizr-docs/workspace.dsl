workspace {

    model {
        user = person "Пользователь"
        courier = person "Курьер"
        postalOperator = person "Оператор почтовой связи"

        notificationSystem = softwareSystem "Система уведомления" {
            tags "notificationSystem"
        }
        configRepo = softwareSystem "GitHub конфиг репозиторий" {
            tags "configRepo"
        }

        deliverySystem = softwareSystem "Система доставки" {

            infra = group "Слой инфраструктурных сервисов" {
                discovery = container "DiscoveryServer" {
                    description "Отвечает за обнаружение и регистрацию микросервисов в распределенной среде."
                }
                config = container "ConfigServer" {
                    description "Централизованное управление конфигурациями микросервисов."
                }
            }

            microservices = group "Слой сервисов" {
                authService = container "AuthenticationService" {
                    tags "service"
                }
                parcelService = container "ParcelService" {
                    tags "service"
                }
                deliveryService = container "DeliveryService" {
                    tags "service"
                }
                courierService = container "CourierService" {
                    tags "service"
                }
            }

            group "Слой данных" {
                userDatabase = container "User Database" {
                    description "База данных с пользователями"
                    technology "PostgreSQL 16"
                    tags "database"
                }
                deliveryDatabase = container "Delivery Database" {
                    description "База данных с доставками"
                    technology "MongoDB 7"
                    tags "database"
                }
                parcelDatabase = container "Parcel Database" {
                    description "База данных с посылками"
                    technology "MongoDB 7"
                    tags "database"
                }
                courierDatabase = container "Courier Database" {
                    description "База данных с курьерами"
                    technology "MongoDB 7"
                    tags "database"
                }
                cashe = container "User Cache" {
                    description "Кеш пользовательских данных для ускорения аутентификации"
                    technology "Redis 7"
                    tags "database"
                }
            }
        }

        config -> configRepo "Использует"
        deliveryService -> courierService "via Kafka Topic A1:Message STATUS CREATED"
        courierService -> deliveryService "via Kafka Topic A1:Message STATUS ON THE WAY"

        deliveryService -> notificationSystem "Уведомить о статусе доставки"
        notificationSystem -> user "Уведомить о статусе доставки" "email/phone number"

        postalOperator -> deliverySystem "Регистрация посылки\nВыдача посылки" "via API-Gateway REST HTTP:8080"

        courier -> courierService "Принять заказ" "via API-Gateway REST HTTP:8080"

        user -> authService "Регистрация нового пользователя" "via API-Gateway, REST HTTP:8080"
        user -> deliveryService "Получение информации о доставке по получателю\nОтслеживание посылки" "via API-Gateway REST HTTP:8080"
        user -> parcelService "Получение информации о доставке по отправителю" "via API-Gateway REST HTTP:8080"

        authService -> cashe "Использует" "TCP 6379"
        authService -> userDatabase "CRUD" "TCP 5432"
        deliveryService -> deliveryDatabase "CRUD" "TCP 27017"
        parcelService -> parcelDatabase "CRUD" "TCP 27017"
        courierService -> courierDatabase "CRUD" "TCP 27017"
    }

    views {

        themes default

        systemContext deliverySystem "Diagram_C1" {
            include *
            autoLayout
        }

        container deliverySystem "Diagram_C2" {
            include *
            autoLayout
        }

        styles {
            element configRepo {
                background gray
            }
            element notificationSystem {
                background gray
            }
            element database {
                shape Cylinder
            }
            element "service" {
                shape Hexagon
            }
        }
    }

}