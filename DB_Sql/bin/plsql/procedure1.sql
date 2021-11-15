[결과]
2^3 = 8
I I   O
--a_exponent가 음수이면 값을 0으로 변경하고, 1을 반환
create or replace Procedure compute_power(
	a_num in number,
	a_exponent in out number,
	a_power out number
)
is
begin
	if a_exponent < 0  then
		a_exponent := 0 ;
	end if;
	a_power := 1;
	
	for i in 1..a_exponent loop
		a_power := a_power*a_num;
	end loop;
	
	
end;