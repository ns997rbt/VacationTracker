create table employees (id bigint not null, email varchar(255), password varchar(255), primary key (id));
create table vacation (id bigint not null, end_date varchar(255), start_date varchar(255), employee bigint, primary key (id));
create table vacation_left (employee_id bigint not null, days_left integer, year varchar(255) not null, primary key (employee_id, year));
create table vacation_total (employee_id bigint not null, days_total integer, year varchar(255) not null, primary key (employee_id, year));
alter table if exists vacation add constraint FK8ps480fa6elnycnjc49q7iad4 foreign key (employee) references employees;
alter table if exists vacation_left add constraint FKq97ls653xmbx786fst38ldesf foreign key (employee_id) references employees;
alter table if exists vacation_total add constraint FK7gea23mm57bg07wk20i6tivl7 foreign key (employee_id) references employees;create table employees (id bigserial not null, email varchar(255), password varchar(255), primary key (id));
create table vacation (id bigint not null, end_date varchar(255), start_date varchar(255), employee bigint, primary key (id));
create table vacation_left (employee_id bigint not null, days_left integer, year varchar(255) not null, primary key (employee_id, year));
create table vacation_total (employee_id bigint not null, days_total integer, year varchar(255) not null, primary key (employee_id, year));
alter table if exists vacation add constraint FK8ps480fa6elnycnjc49q7iad4 foreign key (employee) references employees;
alter table if exists vacation_left add constraint FKq97ls653xmbx786fst38ldesf foreign key (employee_id) references employees;
alter table if exists vacation_total add constraint FK7gea23mm57bg07wk20i6tivl7 foreign key (employee_id) references employees;
create table employees (id bigserial not null, email varchar(255), password varchar(255), primary key (id));
create table vacation (id bigint not null, end_date varchar(255), start_date varchar(255), employee bigint, primary key (id));
create table vacation_left (employee_id bigint not null, days_left integer, year varchar(255) not null, primary key (employee_id, year));
create table vacation_total (employee_id bigint not null, days_total integer, year varchar(255) not null, primary key (employee_id, year));
alter table if exists vacation add constraint FK8ps480fa6elnycnjc49q7iad4 foreign key (employee) references employees;
alter table if exists vacation_left add constraint FKq97ls653xmbx786fst38ldesf foreign key (employee_id) references employees;
alter table if exists vacation_total add constraint FK7gea23mm57bg07wk20i6tivl7 foreign key (employee_id) references employees;
create table employees (id bigserial not null, email varchar(255), password varchar(255), primary key (id));
create table vacation (id bigint not null, end_date varchar(255), start_date varchar(255), employee bigint, primary key (id));
create table vacation_left (employee_id bigint not null, days_left integer, year varchar(255) not null, primary key (employee_id, year));
create table vacation_total (employee_id bigint not null, days_total integer, year varchar(255) not null, primary key (employee_id, year));
alter table if exists vacation add constraint FK8ps480fa6elnycnjc49q7iad4 foreign key (employee) references employees;
alter table if exists vacation_left add constraint FKq97ls653xmbx786fst38ldesf foreign key (employee_id) references employees;
alter table if exists vacation_total add constraint FK7gea23mm57bg07wk20i6tivl7 foreign key (employee_id) references employees;
create table employees (id bigserial not null, email varchar(255), password varchar(255), primary key (id));
create table vacation (id bigint not null, end_date varchar(255), start_date varchar(255), employee bigint, primary key (id));
create table vacation_left (employee_id bigint not null, days_left integer, year varchar(255) not null, primary key (employee_id, year));
create table vacation_total (employee_id bigint not null, days_total integer, year varchar(255) not null, primary key (employee_id, year));
alter table if exists vacation add constraint FK8ps480fa6elnycnjc49q7iad4 foreign key (employee) references employees;
alter table if exists vacation_left add constraint FKq97ls653xmbx786fst38ldesf foreign key (employee_id) references employees;
alter table if exists vacation_total add constraint FK7gea23mm57bg07wk20i6tivl7 foreign key (employee_id) references employees;
create table employees (id bigserial not null, email varchar(255), password varchar(255), primary key (id));
create table vacation (id bigint not null, end_date varchar(255), start_date varchar(255), employee bigint, primary key (id));
create table vacation_left (employee_id bigint not null, days_left integer, year varchar(255) not null, primary key (employee_id, year));
create table vacation_total (employee_id bigint not null, days_total integer, year varchar(255) not null, primary key (employee_id, year));
alter table if exists vacation add constraint FK8ps480fa6elnycnjc49q7iad4 foreign key (employee) references employees;
alter table if exists vacation_left add constraint FKq97ls653xmbx786fst38ldesf foreign key (employee_id) references employees;
alter table if exists vacation_total add constraint FK7gea23mm57bg07wk20i6tivl7 foreign key (employee_id) references employees;
create table employees (id bigserial not null, email varchar(255), password varchar(255), primary key (id));
create table vacation (id bigint not null, end_date varchar(255), start_date varchar(255), employee bigint, primary key (id));
create table vacation_left (employee_id bigint not null, days_left integer, year varchar(255) not null, primary key (employee_id, year));
create table vacation_total (employee_id bigint not null, days_total integer, year varchar(255) not null, primary key (employee_id, year));
alter table if exists vacation add constraint FK8ps480fa6elnycnjc49q7iad4 foreign key (employee) references employees;
alter table if exists vacation_left add constraint FKq97ls653xmbx786fst38ldesf foreign key (employee_id) references employees;
alter table if exists vacation_total add constraint FK7gea23mm57bg07wk20i6tivl7 foreign key (employee_id) references employees;
create sequence employees_seq start with 1 increment by 50;
create sequence vacation_seq start with 1 increment by 50;
create table employees (id bigint not null, email varchar(255), password varchar(255), primary key (id));
create table vacation (id bigint not null, end_date varchar(255), start_date varchar(255), employee bigint, primary key (id));
create table vacation_left (employee_id bigint not null, days_left integer, year varchar(255) not null, primary key (employee_id, year));
create table vacation_total (employee_id bigint not null, days_total integer, year varchar(255) not null, primary key (employee_id, year));
alter table if exists vacation add constraint FK8ps480fa6elnycnjc49q7iad4 foreign key (employee) references employees;
alter table if exists vacation_left add constraint FKq97ls653xmbx786fst38ldesf foreign key (employee_id) references employees;
alter table if exists vacation_total add constraint FK7gea23mm57bg07wk20i6tivl7 foreign key (employee_id) references employees;
