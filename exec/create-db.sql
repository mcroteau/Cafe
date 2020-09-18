create table accounts (
	id bigint PRIMARY KEY AUTO_INCREMENT,
	name character varying(55),
	username character varying(55) NOT NULL,
	password character varying(155) NOT NULL
);

create table roles (
	id bigint PRIMARY KEY AUTO_INCREMENT,
	name character varying(55) NOT NULL UNIQUE
);

create table user_roles (
	role_id bigint NOT NULL REFERENCES roles(id),
	account_id bigint NOT NULL REFERENCES accounts(id)
);

create table user_permissions (
	account_id bigint REFERENCES accounts(id),
	permission character varying(55)
);

create table posts (
	id bigint PRIMARY KEY AUTO_INCREMENT,
	account_id bigint NOT NULL REFERENCES accounts(id),
	title character varying(172),
	content text,
	date_created bigint NOT NULL
);

insert into accounts values (1, 'owner', 'owner@mail.co', '5e884898da28047151d0e56f8dc6292773603d0d6aabbdd62a11ef721d1542d8');
insert into accounts values (2, 'barista', 'barista@mail.co', '5e884898da28047151d0e56f8dc6292773603d0d6aabbdd62a11ef721d1542d8');

insert into roles values (1, 'ROLE_OWNER');
insert into roles values (2, 'ROLE_BARISTA');

insert into user_roles values (1, 1);
insert into user_roles values (2, 2);

