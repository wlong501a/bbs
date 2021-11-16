package mms.member.svc;

import java.sql.Connection;
import mms.member.dao.MemberDAO;
import mms.member.db.JdbcUtil;
import mms.member.vo.Member;

public class MemberAddService {
	
	public boolean addMember(Member newmember) throws Exception {
		boolean isInsertSuccess = false;
		Connection con = JdbcUtil.getConnection();
		MemberDAO dao = new MemberDAO(con);
		
		int count = dao.insertNewMember(newmember);
		if (count > 0) {
			JdbcUtil.commit(con);
			isInsertSuccess = true;
			
		}else {
			JdbcUtil.rollbact(con);
			
		}
		return isInsertSuccess;
	}

}
