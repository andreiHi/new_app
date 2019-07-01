CREATE or replace function AddTopic
(name1 VARCHAR(20), desc1 VARCHAR(255), text1 VARCHAR(5000), text2 VARCHAR (5000)) returns void AS $$
DECLARE
  v  int;
BEGIN
  insert into base_entity(status) values ('ACTIVE') RETURNING id into v ;
  insert into topic (name, description, text_field1, text_field2, id) values (name1, desc1, text1, text2, v) ;
END ;
$$ LANGUAGE plpgsql
