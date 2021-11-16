package mms.member.svc;

import java.sql.Connection;

import mms.member.dao.MemberDAO;
import mms.member.db.JdbcUtil;

public class MemberRemoveService {

	public boolean removeMember(String name) {
		Connection con = JdbcUtil.getConnection();
		MemberDAO dao = new MemberDAO(con);
		
		boolean isdeletSuccess = false;
		int deleteCount = dao.deleteMember(name);
		if (deleteCount > 0) {
			isdeletSuccess = true;
			JdbcUtil.commit(con);
		}else {
			JdbcUtil.rollbact(con);
		}
		JdbcUtil.close(con);
		
		return isdeletSuccess;
	}

}
