set @price_uuid = uuid();
insert into price (uuid, amount, currency_code) values (@price_uuid, '650', 'USD');
insert into phone (uuid, price_uuid, image_url, name, description)
values (uuid(), @price_uuid, 'https://cdn2.gsmarena.com/vv/pics/google/google-pixel-04.jpg', 'Google Pixel', 'Released 2016, October\n143g, 8.5mm thickness\nAndroid 7.1, up to Android 9.0\n32/128GB storage, no card slot');

set @price_uuid = uuid();
insert into price (uuid, amount, currency_code) values (@price_uuid, '699.00', 'USD');
insert into phone (uuid, price_uuid, image_url, name, description)
values (uuid(), @price_uuid, 'https://cdn2.gsmarena.com/vv/pics/google/google-pixel-xl-2.jpg', 'Google Pixel 2', 'Android 7.1 (Nougat), upgradable to Android 9.0 (Pie) Chipset\nQualcomm MSM8996 Snapdragon 821 (14 nm) CPU\nQuad-core (2x2.15 GHz Kryo & 2x1.6 GHz Kryo)GPU Adreno 530');

set @price_uuid = uuid();
insert into price (uuid, amount, currency_code) values (@price_uuid, '1399.49', 'USD');
insert into phone (uuid, price_uuid, image_url, name, description)
values (uuid(), @price_uuid, 'https://cdn.gsmarena.com/imgroot/reviews/18/apple-iphone-xs-max/gal/-1024w2/gsmarena_009.jpg', 'iPhone XS Max', '157.5 x 77.4 x 7.7 mm (6.20 x 3.05 x 0.30 in)\n208 g (7.34 oz)\nFront/back glass, stainless steel frame\nSingle SIM (Nano-SIM) or Dual SIM (Nano-SIM, dual stand-by) - for China\nIP68 dust/water resistant (up to 2m for 30 mins)\nApple Pay (Visa, MasterCard, AMEX certified)');

set @price_uuid = uuid();
insert into price (uuid, amount, currency_code) values (@price_uuid, '800.33', 'USD');
insert into phone (uuid, price_uuid, image_url, name, description)
values (uuid(), @price_uuid, 'https://cdn2.gsmarena.com/vv/pics/samsung/samsung-galaxy-a9-2018-1.jpg', 'Samsung Galaxy A9 Star Pro', 'Android 8.0 (Oreo)\nChipset Qualcomm SDM660 Snapdragon 660 (14 nm)\nOcta-core (4x2.2 GHz Kryo 260 & 4x1.8 GHz Kryo 260)\nGPU Adreno 512\nCard slot microSD, up to 512 GB (dedicated slot)\n128 GB, 6/8 GB RAM');

set @price_uuid = uuid();
insert into price (uuid, amount, currency_code) values (@price_uuid, '1569.99', 'USD');
insert into phone (uuid, price_uuid, image_url, name, description)
values (uuid(), @price_uuid, 'https://cdn2.gsmarena.com/vv/pics/huawei/huawei-mate20-pro-1.jpg', 'Huawei Mate 20 Pro', 'Android 9.0 (Pie)\nChipset HiSilicon Kirin 980 (7 nm)\nOcta-core (2x2.6 GHz Cortex-A76 & 2x1.92 GHz Cortex-A76 & 4x1.8 GHz Cortex-A55)\nMali-G76 MP10\nNM (Nano Memory), up to 256GB (uses SIM 2)\n256 GB, 8 GB RAM or 128 GB, 6 GB RAM');

set @price_uuid = uuid();
insert into price (uuid, amount, currency_code) values (@price_uuid, '0.00', 'USD');
insert into phone (uuid, price_uuid, image_url, name, description)
values (uuid(), @price_uuid, 'NA', 'Some new very fancy phone', 'Comming soon...');