CREATE or replace function AddRoles (role VARCHAR(20)) returns void AS $$
DECLARE
  v  int;
BEGIN
  insert into base_entity(status) values ('ACTIVE') RETURNING id into v ;
  insert into roles (name, id) values (role, v) ;
END ;
$$ LANGUAGE plpgsql
