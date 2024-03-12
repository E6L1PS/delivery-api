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
                userCashe = container "User Cache" {
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

        postalOperator -> parcelService "Регистрация посылки" "via API-Gateway REST HTTP:8080"
        postalOperator -> deliveryService "Создание доставки" "via API-Gateway REST HTTP:8080"

        courier -> courierService "Принять заказ" "via API-Gateway REST HTTP:8080"

        user -> authService "Регистрация нового пользователя" "via API-Gateway, REST HTTP:8080"
        user -> deliveryService "Получение информации о доставке по получателю\nОтслеживание посылки" "via API-Gateway REST HTTP:8080"

        authService -> userCashe "Использует" "TCP 6379"
        authService -> userDatabase "PostgreSQL CRUD Operations" "TCP 5432"
        deliveryService -> deliveryDatabase "MongoDB CRUD Operations" "TCP 27017"
        parcelService -> parcelDatabase "MongoDB CRUD Operations" "TCP 27017"
        courierService -> courierDatabase "MongoDB CRUD Operations" "TCP 27017"

        development = deploymentEnvironment "Production" {

            deploymentNode "Auth Service" {
                deploymentNode "Apache Tomcat" {
                    technology "Apache Tomcat 10.x"
                    containerInstance authService
                    properties {
                        "cpu" "4"
                        "ram" "256Gb"
                        "hdd" "4Tb"
                    }
                }
            }

            deploymentNode "Delivery Service" {
                deploymentNode "Apache Tomcat" {
                    technology "Apache Tomcat 10.x"
                    containerInstance deliveryService
                    properties {
                        "cpu" "4"
                        "ram" "256Gb"
                        "hdd" "4Tb"
                    }
                }
            }

            deploymentNode "Parsel Service" {
                deploymentNode "Apache Tomcat" {
                    technology "Apache Tomcat 10.x"
                    containerInstance parcelService
                    properties {
                        "cpu" "4"
                        "ram" "256Gb"
                        "hdd" "4Tb"
                    }
                }
            }

            deploymentNode "Сourier Service" {
                deploymentNode "Apache Tomcat" {
                    technology "Apache Tomcat 10.x"
                    containerInstance courierService
                    properties {
                        "cpu" "4"
                        "ram" "256Gb"
                        "hdd" "4Tb"
                    }
                }
            }

            deploymentNode "databases" {

                deploymentNode "Database Server 1" {
                    containerInstance userDatabase
                    instances 3
                }

                deploymentNode "Database Server 2" {
                    containerInstance deliveryDatabase
                    instances 3
                }

                deploymentNode "Database Server 3" {
                    containerInstance parcelDatabase
                    instances 3
                }

                deploymentNode "Database Server 4" {
                    containerInstance courierDatabase
                    instances 3
                }

                deploymentNode "Cache Server" {
                    containerInstance userCashe
                    instances 6
                }
            }

        }
    }

    views {

        themes default

        systemContext deliverySystem "ContextDiagram" {
            include *
            autoLayout
        }

        container deliverySystem "ContainerDiagram" {
            include *
            autoLayout
        }

        deployment * development "DeploymentDiagram" {
            include *
            autoLayout
        }

        dynamic deliverySystem "UC01" "Регистрация нового пользователя" {
            autoLayout
            user -> authService "Создать нового пользователя (POST /auth/register)"
            authService -> userDatabase "insert into "
            authService -> user "status: 201 created"
        }

        dynamic deliverySystem "UC02" "Аутентификация пользователя" {
            autoLayout
            user -> authService "Аутентифицировать пользователя (POST /auth/login)"
            authService -> userDatabase "select * from users where username = ?"
            userDatabase -> authService "Возвращает данные пользователя"
            authService -> userCashe "Добавить информацию о пользователе в кэш"
            authService -> user "Выдать токен"
        }

        dynamic deliverySystem "UC03" "Получение информации о доставке" {
            autoLayout
            user -> deliveryService "Получение информации о доставке (GET /delivery)"
            deliveryService -> deliveryDatabase "Получить информации о доставке по пользователю"
            deliveryDatabase -> deliveryService "Возвращает информацию о доставке"
            deliveryService -> user "Возвращает информацию о доставке"
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