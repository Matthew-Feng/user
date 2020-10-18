create table user_detail(
  id IDENTITY  ,
  title VARCHAR(255),
  first_name VARCHAR(255),
  last_name VARCHAR(255),
  gender VARCHAR(10), 
  address_id int
);

create table address(
  id IDENTITY ,
  street VARCHAR(255),
  city VARCHAR(255),
  state VARCHAR(255),
  postcode VARCHAR(10)
);

