package mms.member.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import mms.member.db.JdbcUtil;
import mms.member.vo.Member;

public class MemberDAO {
	
	Connection con;
	PreparedStatement pstmt;
	public MemberDAO(Connection con) {
		this.con = con;
	}
	
	public int insertNewMember(Member newMember) {
		int count = 0;
		String query = "insert into mms_member values(mms_member_id_seq.nextval,?,?,?,?,?)";
		try {
			pstmt = con.prepareStatement(query);
			pstmt.setString(1, newMember.getName());
			pstmt.setString(2, newMember.getAddr());
			pstmt.setString(3, newMember.getNation());
			pstmt.setString(4, newMember.getEmail());
			pstmt.setInt(5, newMember.getAge());
			count = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			
		} JdbcUtil.close(pstmt);
		
		return count;
	}

	public ArrayList<Member> selectMemberList() {
		ArrayList<Member> memberList = new ArrayList<Member>(); 
		try {
			String query = "select*from mms_member";
			pstmt = con.prepareStatement(query);

			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				int id = rs.getInt(1);
				String name = rs.getString(2);
				String addr = rs.getString(3);
				String nation = rs.getString(4);
				String email = rs.getString(5);
				int age = rs.getInt(6);
				memberList.add(new Member(id, name, addr, nation, email, age));
			}
		} catch (SQLException e) {
			
		}
		return memberList;
	}
	
	
	
	
	public Member selectOldMember(String name) {
		
		
		return null;













		
	
	}
	
	public int updateMember(Member updateMember) {
		return 0;








		
		
	}
	
	public int deleteMember(String name) {
		return 0;








		
		
	}

	
	

}
