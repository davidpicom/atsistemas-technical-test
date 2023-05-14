DROP TABLE IF EXISTS prices;

CREATE TABLE prices (
  id 				BIGINT NOT NULL AUTO_INCREMENT,
  brand_id 			TINYINT NOT NULL,
  start_date 	    TIMESTAMP NOT NULL,
  end_date 	        TIMESTAMP NOT NULL,
  price_list        SMALLINT NOT NULL,
  product_id        INTEGER NOT NULL,
  priority          INTEGER NOT NULL,
  price             REAL NOT NULL,
  curr              VARCHAR(10) NOT NULL
);
