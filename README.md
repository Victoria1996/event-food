# event-food
## Проект по спец. курсу "Высоконагруженные системы и анализ данных"

Backend часть проекта в магистратуре по спец курсу высоконагруженных систем. БГУ. 2018 г.

## Технологии
1. Java 8
2. Spring (Data, MVC, Security, Boot)
3. lombok
4. Maven
5. DB (Mssql server 2014)
6. Swagger

## Обязательно к прочтению
1. https://projectlombok.org/setup/maven
2. https://docs.spring.io/spring-boot/docs/current/reference/htmlsingle/
3. https://swagger.io/resources/articles/best-practices-in-api-documentation/

## Запуск

Перед запуском необходимо создать базу данных event-food в Management studio и настроить ее в Intellij IDEA.
Добавляем Data source - SQL Server, url - jdbc:sqlserver://localhost:1433;databaseName=event-food, логин и пароль.

Используемый порт http://localhost:8001.
Для запуска из Intellij IDEA выполнить run.
Для запуска из командной строки можно использовать jar-файл и команду java -jar EventFoodApplication.jar
