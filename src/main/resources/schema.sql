DROP TABLE IF EXISTS PRICES;

CREATE TABLE PRICES (
  BRAND_ID int NOT NULL,
  START_DATE timestamp NOT NULL,
  END_DATE timestamp NOT NULL,
  PRICE_LIST int NOT NULL,
  PRODUCT_ID int NOT NULL,
  PRIORITY int NOT NULL,
  PRICE double NOT NULL,
  CURR varchar(3) NOT NULL,
  PRIMARY KEY (PRICE_LIST)
);