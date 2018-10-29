curl -X POST \
  http://localhost:8092/order-api/rest/v1/order-information \
  -H 'Content-Type: application/json' \
  -H 'Postman-Token: 7619c3ae-83c0-4ba8-97eb-390ce90423e8' \
  -H 'cache-control: no-cache' \
  -d '{
	"items": [
		{
			"id": "7ba92093-5397-4d5f-a398-7873a40a3bd0",
			"quantity": 10.4
		},
		{
			"id": "5caca2a8-49f6-4b87-a558-bd22b434d479"
		}
	],
	"customer": {
		"firstName": "Valeriy",
		"lastName": "Mironichev",
		"email": "vmironichev@gmail.com"
	}
}'



{
    "customer": {
        "firstName": "Valeriy",
        "lastName": "Mironichev",
        "email": "vmironichev@gmail.com"
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