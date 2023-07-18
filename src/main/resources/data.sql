--insert into member(`id`, `name`, `email`, `create_at`, `update_at`) values (1, '홍길동', 'mars@thejoeun.com', now(), now());
--insert into member(`id`, `name`, `email`, `create_at`, `update_at`) values (2, '홍길동', 'namsun@thejoeun.com', now(), now());
--insert into member(`id`, `name`, `email`, `create_at`, `update_at`) values (3, '홍길동', 'leesunsin@gmail.com', now(), now());
--insert into member(`id`, `name`, `email`, `create_at`, `update_at`) values (4, '강감찬', 'namsun2@thejoeun.com', now(), now());
--insert into member(`id`, `name`, `email`, `create_at`, `update_at`) values (5, '홍길동', 'ryukwansun@thejoeun.com', now(), now());
--
--Insert into users(`id`, `name`, `email`) values (1, '홍길동', 'hong1@abc.com');
--Insert into users(`id`, `name`, `email`) values (2, '홍길은', 'hong2@abc.com');
--Insert into users(`id`, `name`, `email`) values (3, '홍길금', 'hong3@abc.com');
--
--Insert into publisher(`id`, `name`, `create_at`, `update_at`) values (1, '더조은아카데미', now(), now());
--Insert into publisher(`id`, `name`, `create_at`, `update_at`) values (2, '한빛출판사', now(), now());
--
--Insert into book(`id`,`name`, `publisher_id`, `create_at`, `update_at`) values (1, '재미있는 자바', 1, now(), now());
--Insert into book(`id`,`name`, `publisher_id`, `create_at`, `update_at`) values (2, '어려운 DB', 1, now(), now());
--Insert into book(`id`,`name`, `publisher_id`, `create_at`, `update_at`) values (3, '텍스로 디자인 하는 css', 1, now(), now());
--Insert into book(`id`,`name`, `publisher_id`, `create_at`, `update_at`) values (4, '재미있는 자바', 2, now(), now());

Insert into article (title, content) values ('제목 1', '내용 1');
Insert into article (title, content) values ('제목 2', '내용 2');
Insert into article (title, content) values ('제목 3', '내용 3');