# Java 10, Spring boot, Docker, Flyway, H2 DB Phone-App

## API Doc

Phone-App project contains of sub projects:

1) phone-catalog-api
2) order-api

### Phone catalog API

Phone catalog API is a service that provides access to phone models and their prices via REST API. Data is stored in H2 DB.
Phone catalog API provides following endpoints for phones retrieval:

#### Load all phones

Request

```
curl -X GET http://localhost:8091/phone-catalog/rest/v1/phones
```

Response, HTTP 1.1 200 OK "application/json;charset=UTF-8"

```json
[
    {
        "id": "a237864b-7d2a-41a7-8c17-2fdb56a3dc6c",
        "name": "Google Pixel 2",
        "description": "Android 7.1 (Nougat), upgradable to Android 9.0 (Pie) Chipset\\nQualcomm MSM8996 Snapdragon 821 (14 nm) CPU\\nQuad-core (2x2.15 GHz Kryo & 2x1.6 GHz Kryo)GPU Adreno 530",
        "imageUrl": "https://cdn2.gsmarena.com/vv/pics/google/google-pixel-xl-2.jpg",
        "price": "699.00",
        "currency": "USD"
    },
    {
        "id": "8d58766b-44d5-4079-8faf-cf10da10133f",
        "name": "Some new very fancy phone",
        "description": "Coming soon...",
        "imageUrl": "NA",
        "price": "0.00",
        "currency": "USD"
    }
    ...
]
```

#### Load phone by id

Request

```
curl -X GET http://localhost:8091/phone-catalog/rest/v1/phones/a237864b-7d2a-41a7-8c17-2fdb56a3dc6c
```

Response, HTTP 1.1 200 OK "application/json;charset=UTF-8"

```json
{
    "id": "a237864b-7d2a-41a7-8c17-2fdb56a3dc6c",
    "name": "Google Pixel 2",
    "description": "Android 7.1 (Nougat), upgradable to Android 9.0 (Pie) Chipset\\nQualcomm MSM8996 Snapdragon 821 (14 nm) CPU\\nQuad-core (2x2.15 GHz Kryo & 2x1.6 GHz Kryo)GPU Adreno 530",
    "imageUrl": "https://cdn2.gsmarena.com/vv/pics/google/google-pixel-xl-2.jpg",
    "price": "699.00",
    "currency": "USD"
}
```


### Order API

Order API is intended to process order information received from API client, validate data and return order details, such as phone models specs, customer information and total price.

Request
```
curl -X POST http://localhost:8090/order-api/rest/v1/order-information
  -d '{
    "items": [
        {
            "id": "7ba92093-5397-4d5f-a398-7873a40a3bd0",
            "quantity": 10
        },
        {
            "id": "5caca2a8-49f6-4b87-a558-bd22b434d479"
        }
    ],
    "customer": {
        "firstName": "User",
        "lastName": "Foo",
        "email": "foouser@gmail.com"
    }
}'
```


Response, HTTP 1.1 200 OK "application/json;charset=UTF-8"
```json
{
    "customer": {
        "firstName": "User",
        "lastName": "Foo",
        "email": "foouser@gmail.com"
    },
    "items": [
        {
            "quantity": 10,
            "price": "8003.30",
            "phone": {
                "id": "a222bc71-c770-4960-8d9e-532d65286e79",
                "name": "Samsung Galaxy A9 Star Pro",
                "description": "Android 8.0 (Oreo)\\nChipset Qualcomm SDM660 Snapdragon 660 (14 nm)\\nOcta-core (4x2.2 GHz Kryo 260 & 4x1.8 GHz Kryo 260)\\nGPU Adreno 512\\nCard slot microSD, up to 512 GB (dedicated slot)\\n128 GB, 6/8 GB RAM",
                "imageUrl": "https://cdn2.gsmarena.com/vv/pics/samsung/samsung-galaxy-a9-2018-1.jpg",
                "price": "800.33",
                "currency": "USD"
            }
        },
        {
            "quantity": 1,
            "price": "699.00",
            "phone": {
                "id": "4a268f38-953f-4bb0-8935-56732c08b73c",
                "name": "Google Pixel 2",
                "description": "Android 7.1 (Nougat), upgradable to Android 9.0 (Pie) Chipset\\nQualcomm MSM8996 Snapdragon 821 (14 nm) CPU\\nQuad-core (2x2.15 GHz Kryo & 2x1.6 GHz Kryo)GPU Adreno 530",
                "imageUrl": "https://cdn2.gsmarena.com/vv/pics/google/google-pixel-xl-2.jpg",
                "price": "699.00",
                "currency": "USD"
            }
        }
    ],
    "totalPrice": "8702.30",
    "currency": "USD"
}
```


## Build and run using Gradle

This project is using Gradle build tool. In order to build it we need to change dir to phone-app project and invoke gradle wrapper build command from terminal:

```
cd {path-to-phone app}/phone-app
...

./gradlew clean build

```

In order to start both order-api and phone-catalog-api, we need start those service separately in two terminal instances:

#### phone catalog (starts on port 8091 by default):

```
./gradlew :phone-catalog-api:bootRun

```

#### order-api (starts on port 8090 by default):

```
./gradlew :order-api:bootRun

```

Once services started, API are ready to use.

## Build and run Docker image

In order to build Docker image, Docker should be installed on local machine. Ensure that dir is changed to phone-app root folder.

#### Building image for phone-catalog-api

```
docker build --build-arg SERVICE_NAME=phone-catalog-api --build-arg SERVICE_PORT=8091 -t phone-catalog-api .

```

#### Building image for order-api

```
docker build --build-arg SERVICE_NAME=order-api --build-arg SERVICE_PORT=8090 -t order-api .

```

#### Running Docker images

```
docker run -p "8091:8091" -ti phone-catalog-api:latest
```

```
docker run -p "8090:8090" -ti order-api:latest
```

Once images started we are ready to use our APIs.

### TODO
1. Use docker compose
2. Extract resource representation classes into common library
3. etc