alter table if exists user_roles
  add constraint fk_user_roles_user
  foreign key (role_id) references roles;

alter table if exists user_roles
  add constraint fk_user_roles_roles
  foreign key (user_id) references users;

alter table if exists roles
 add constraint fk_roles_base_entity
 foreign key (id) references base_entity on DELETE CASCADE ON UPDATE RESTRICT;

alter table if exists topic
  add constraint fk_topic_base_entity
  foreign key (id) references base_entity;

alter table if exists users
  add constraint fk_users_base_entity
  foreign key (id) references base_entity;
