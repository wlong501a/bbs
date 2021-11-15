package mms.member.svc;

import java.sql.Connection;

import mms.member.dao.MemberDAO;
import mms.member.db.JdbcUtil;
import mms.member.vo.Member;

public class MemberModifyService {
	
	public Member getOldMember(String name) {
		Connection con = JdbcUtil.getConnection();
		MemberDAO dao = new MemberDAO(con);
		Member member = dao.selectOldMember(name);
		
		return member;

	}

	public boolean modifyMember(Member updateMember) {
		return false;










		
		
	}


}
