CREATE TABLE category(
id IDENTITY,
name VARCHAR(50),
descrption VARCHAR(250),
image_url VARCHAR(50),
is_active BOOLEAN,

CONSTRAINT pk_category_id PRIMARY KEY(id)
;

)