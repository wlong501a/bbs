create table member3(
	id VARCHAR2(12),
	passwd VARCHAR2(12),
	name VARCHAR2(12),
	age NUMBER,
	addr VARCHAR2(50),
	email VARCHAR2(30)
);
drop procedure user_insert;

--Oracle에서 실행시 프로시저 컴파일 완료됨
set serveroutput on
create or replace procedure user_insert(
	user_id varchar2,
	user_passwd varchar2,
	user_name varchar2,
	user_age number,
	user_addr varchar2,
	user_email varchar2
)
is
begin
	insert into member3
	values(user_id,user_passwd,user_name,user_age,user_addr,user_email);
end ;




