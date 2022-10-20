## Что я такое?

Простейший Telegram-бот, отправляющий детские анекдоты в ответ на любое сообщение. Сделан для дочери, просто чтоб посмеяться

## Лицензия
Этот проект лицензируется в соответствии с лицензией Apache 2.0

Подробности в файле ```LICENSE```

## Попробовать
[@SashaGagsBot](https://t.me/SashaGagsBot) доступен в Telegram

## Автор
Сергей Козырев

## Контакты для связи
Telegram [@taksebe](https://t.me/taksebe)

## Создано с помощью
Java™ SE Development Kit 11.0.5

[Spring Framework](https://spring.io/)

Git - управление версиями

GitHub - репозиторий

[Telegram Bots](https://core.telegram.org/bots) - взаимодействие с Telegram

[Apache Maven](https://maven.apache.org/) - сборка, управление зависимостями

[Lombok](https://projectlombok.org/) - упрощение кода, замена стандартных java-методов аннотациями

Полный список зависимостей и используемые версии компонентов можно найти в ```pom.xml```

## Сборка и запуск
Перед сборкой необходимо создать бота с помощью [BotFather](https://t.me/botfather) и сохранить его имя и токен (они понадобятся для запуска)

Далее
```
git clone https://github.com/taksebe-official/gagsBot
```

Найти в проекте файл ```src/main/resources/application.yaml``` (или удалить его и создать ```src/main/resources/application.properties```, если Вам так привычнее)

Добавить в него настройки Telegram:
```
telegram.api-url: "https://api.telegram.org/"
telegram.user: "<имя бота, полученное от BotFather>"
telegram.token: "<токен бота, полученный от BotFather>"
telegram.webhook-path: "<см чуть ниже>"
server.port: "<см чуть ниже>"
```
Для получения настроек ```telegram.webhook-path``` и ```server.port``` при локальной отладке можно использовать утилиту [ngrok](https://ngrok.com/):
- скачать и запустить ngrok
- отправить команду ```ngrok http 5000```, вместо 5000 можно использовать другой порт - главное, чтобы тот же, что прописан в настройке ```server.port```
- скопировать ссылку вида https://73c8-80-250-84-162.ngrok.io, вставить её в настройку ```telegram.webhook-path```


Далее можно изменить (если это необходимо) параметры сообщения, отправляемого пользователю в случае возникновения ошибки:
```
message.wtf.text: "<текст, отправлямой пользователю в случае ошибки>"
```

Далее:
```
mvn clean install
java $JAVA_OPTS -jar target/gags-1.0-SNAPSHOT.jar
```

Для проверки работоспособности приложения можно использовать специальный метод для тестирования (код в классе ```TestController```) - введите в браузере http://localhost:5000/gags/test и получите в ответ текст "LOL" (если при создании вебхука в ngrok вы использовали другой порт, не 5000, поменяйте его и в этом url)

Далее необходимо зарегистрировать webhook в Telegram - для этого нужно отправить в любом браузере ссылку вида:
```
https://api.telegram.org/bot<токен бота от Botfather>/setWebhook?url=<URL вебхука, полученного от ngrok>/gags
```

Теперь можно проверять бота непосредственно в Telegram