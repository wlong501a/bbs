create or replace procedure javatest(
  p1 in varchar2,
  p2 in out varchar2,
  p3 out varchar2
  )
as	-- is, as 둘다이용가능
begin
	p2 := p1 || p2;
	p3 := p1;
end;