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
		
		JdbcUtil.close(con);
		return member;
	}

	public boolean modifyMember(Member updateMember) {
		Connection con = JdbcUtil.getConnection();
		MemberDAO dao = new MemberDAO(con);
		boolean isModifySuccess = false;
		int updateCount = dao.updateMember(updateMember);
		
		if (updateCount > 0) {
			isModifySuccess = true;
			JdbcUtil.commit(con);
		}else {
			JdbcUtil.rollbact(con);
		}
		JdbcUtil.close(con);
		
		return isModifySuccess;
	}


}
