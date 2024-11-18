# Spring Boot Starter S3 Load Image

## Описание

`spring-boot-starter-s3-load-image` — это Spring Boot стартер для работы с Amazon S3 и MinIO. Он упрощает загрузку и управление файлами в облачном хранилище.

### Особенности:
- Поддержка Amazon S3 и MinIO.
- Автоматическая конфигурация клиента и пресайнера.
- Легкость интеграции с вашим приложением Spring Boot.

## Установка

Добавьте стартер в ваш проект, используя Maven или Gradle.

### Maven
Шаг 1. Добавьте репозиторий Jetpack в свой файл сборки
```xml
<repositories>
  <repository>
      <id>jitpack.io</id>
      <url>https://jitpack.io</url>
  </repository>
</repositories>
```
Шаг 2. Добавьте зависимость
```xml
<dependency>
    <groupId>com.github.SuzdalevAndrey</groupId>
    <artifactId>spring-boot-starter-s3-load-image</artifactId>
    <version>v1.0.1</version>
</dependency>
```

### Gradle
Шаг 1. Добавьте репозиторий Jetpack в свой файл сборки
```groovy
dependencyResolutionManagement {
		repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
		repositories {
			mavenCentral()
			maven { url 'https://jitpack.io' }
		}
	}
```
Шаг 2. Добавьте зависимость
```groovy
dependencies {
	        implementation 'com.github.SuzdalevAndrey:spring-boot-starter-s3-load-image:v1.0.1'
	}
```

## Конфигурация

Добавьте настройки в ваш `application.yml` или `application.properties`.

### Пример `application.yml`

```yaml
s3-load-image-starter:
  provider: aws # или minio
  access-key: YOUR_ACCESS_KEY
  secret-key: YOUR_SECRET_KEY
  region: YOUR_REGION # Только для AWS
  endpoint: http://localhost:9000 # Только для MinIO
```

### Пример `application.properties`

```properties
s3-load-image-starter.provider=aws
s3-load-image-starter.access-key=YOUR_ACCESS_KEY
s3-load-image-starter.secret-key=YOUR_SECRET_KEY
s3-load-image-starter.region=YOUR_REGION # Только для AWS
s3-load-image-starter.endpoint=http://localhost:9000 # Только для MinIO
```

### Обязательные свойства
- `provider` — выберите `aws` или `minio`. 
- Для AWS:
  - `access-key`
  - `secret-key`
  - `region`
- Для MinIO:
  - `access-key`
  - `secret-key`
  - `endpoint`

Если вы не укажете обязательные свойства, приложение выбросит исключение при запуске.

## Использование

Стартер предоставляет основной сервис:
- `ImageService` — для загрузки изображений.

### Пример использования `ImageService`

```java
@Autowired
private ImageService imageService;

public String getUrlByImageId() {
    return imageService.getImageUrlByImageId("YOUR_IMAGE_Id");
}
```

## Тестирование

Для тестов необходимо указать значения всех обязательных свойств (например, через `application-test.yml` или `application-test.properties`).

## Обратная связь

Если вы нашли ошибку или у вас есть предложения по улучшению, создайте [issue](https://github.com/SuzdalevAndrey/spring-boot-starter-s3-load-image/issues).
