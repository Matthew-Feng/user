create table user_detail(
  id IDENTITY  ,
  title VARCHAR(50),
  first_name VARCHAR(100),
  last_name VARCHAR(100),
  gender VARCHAR(10), 
  address_id int
);

create table address(
  id IDENTITY ,
  street VARCHAR(255),
  city VARCHAR(100),
  state VARCHAR(100),
  postcode VARCHAR(10)
);

