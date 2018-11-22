CREATE TABLE T_USER (
	id SERIAL PRIMARY KEY,
	name varchar(32) not null,
	surname varchar(32) not null,
	email varchar(64) unique not null,
	address varchar(128) not null,
	role varchar(32) not null,
	created_date timestamp  default NOW() NOT NULL,
  last_modified_date timestamp not null,
  record_version bigint not null
);




CREATE TABLE T_AUTHOR (
	id SERIAL PRIMARY KEY,
	name varchar(32) not null,
	surname varchar(32) not null,
	country varchar(32) not null,
	created_date timestamp  default NOW() NOT NULL,
  last_modified_date timestamp not null,
  record_version bigint not null
);

CREATE TABLE T_BOOK (
	id SERIAL PRIMARY KEY,
	title varchar(128) not null,
	year_published varchar(4) not null,
	author_id integer not null references T_AUTHOR(id),
	created_date timestamp  default NOW() NOT NULL,
  last_modified_date timestamp not null,
  record_version bigint not null
);

CREATE TABLE T_BOOK_DETAIL (
	id SERIAL PRIMARY KEY,
	description varchar(1024) not null,
	number_of_pages integer not null,
	book_id integer not null references T_BOOK(id),
	created_date timestamp  default NOW() NOT NULL,
	last_modified_date timestamp not null,
  record_version bigint not null
);


CREATE TABLE T_USER_BOOKS (
	user_id integer not null references T_USER(id),
	book_id integer not null references T_BOOK(id),
	PRIMARY KEY(user_id, book_id)
);

