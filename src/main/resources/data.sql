create table course(
    id number generated always as identity primary key,
    name varchar(50),
    category varchar(50)
);

create table address(
    id number generated always as identity primary key,
    street varchar(100),
    city varchar(50),
    state varchar(2),
    zip number
);

create table student(
    id number generated always as identity primary key,
    name varchar(50),
    address_id number not null,
    constraint student_address_fk foreign key(address_id) references address(id)
);

create table course_student(
    course_id number not null ,
    student_id number not null ,
    constraint course_student_fk1 foreign key(course_id) references course(id),
    constraint course_student_fk2 foreign key(student_id) references student(id)
);

create table author (
    id number generated always as identity primary key ,
    name varchar(50) not null,
    genre varchar(50),
    age number not null
);

create table publisher(
    id number generated always as identity primary key,
    publisher varchar(50) not null
);

create table book (
    id number generated always as identity primary key,
    title varchar(50) not null,
    isbn varchar(50) not null,
    price decimal,
    author_id number,
    publisher_id number,
    constraint book_author_fk foreign key (author_id) references author(id),
    constraint book_publisher_fk foreign key (publisher_id) references publisher(id)
);


insert into course(name, category) values('Software engineering', 'Computer Science');
insert into course(name, category) values('Database management', 'Computer Science');
insert into course(name, category) values('Programming Language', 'Computer Science');

insert into address(street, city, state, zip) values('1 lincoln street', 'Boston', 'MA', 2001);
insert into address(street, city, state, zip) values('20 summer street', 'Boston', 'MA', 2001);
insert into address(street, city, state, zip) values('10 mass av', 'cambridge', 'MA', 2001);
insert into address(street, city, state, zip) values('3 lexington street', 'lexington', 'MA', 2001);
insert into address(street, city, state, zip) values('1 round hill st', 'JP', 'MA', 2001);
insert into address(street, city, state, zip) values('15 huntington ave', 'Boston', 'MA', 2001);
insert into address(street, city, state, zip) values('5 bill street', 'Boston', 'MA', 2001);
insert into address(street, city, state, zip) values('3 old town rd', 'Boston', 'MA', 2001);
insert into address(street, city, state, zip) values('12 canterbury rd', 'woburn', 'MA', 2001);
insert into address(street, city, state, zip) values('1 mass ave', 'Arlington', 'MA', 2001);

insert into student(name, address_id) values('bob', 1);
insert into student(name, address_id) values('carl', 2);
insert into student(name, address_id) values('kim', 3);
insert into student(name, address_id) values('matt', 4);
insert into student(name, address_id) values('simon', 5);
insert into student(name, address_id) values('paul', 6);
insert into student(name, address_id) values('jane', 7);
insert into student(name, address_id) values('liz', 8);
insert into student(name, address_id) values('nick', 9);
insert into student(name, address_id) values('rob', 10);

insert into course_student(course_id, student_id) values(1, 1);
insert into course_student(course_id, student_id) values(1, 2);
insert into course_student(course_id, student_id) values(1, 3);
insert into course_student(course_id, student_id) values(1, 4);
insert into course_student(course_id, student_id) values(1, 5);
insert into course_student(course_id, student_id) values(1, 6);
insert into course_student(course_id, student_id) values(1, 7);
insert into course_student(course_id, student_id) values(1, 8);
insert into course_student(course_id, student_id) values(1, 9);
insert into course_student(course_id, student_id) values(1, 10);

insert into author(name, genre, age) values('Anghel Leonard', 'computer', 44);

insert into publisher(publisher) values ('aPress');

insert into book(title, isbn, price, author_id, publisher_id) values('system design', '3434-3434-BDF', 15.99, 1, 1);
insert into book(title, isbn, price, author_id, publisher_id) values('computer programming', '3434-3434-BDF', 24.99, 1, 1);
insert into book(title, isbn, price, author_id, publisher_id) values('database management', '3434-3434-BDF', 20.99, 1, 1);

