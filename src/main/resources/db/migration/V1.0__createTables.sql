create table play (
	id bigint not null generated always as identity (start with 1 increment by 1),
	username varchar(255) not null,
	userid int not null,
	playid bigint not null,
	play_date date not null,
	quantity int null,
	length int null,
	incomplete boolean null,
	nowinstats boolean null,
	location varchar(255) null,
	play_comment text null,
	gamename varchar(255) not null,
	gameid int not null);
	
alter table play add constraint pk_play primary key (id);


create table player (
	id bigint not null generated always as identity (start with 1 increment by 1),
	play_id bigint not null,
	username varchar(255) null,
	userid int null,
	name varchar(255) null,
	start_position varchar(255) null,
	color varchar(255) null,
	score double null,
	isnew boolean null,
	rating int null,
	win boolean null);

alter table player add constraint pk_player primary key (id);

alter table player add constraint fk_play foreign key (play_id) references play(id);