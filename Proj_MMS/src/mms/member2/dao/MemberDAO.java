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
	ResultSet rs;

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

		}
		JdbcUtil.close(pstmt);

		return count;
	}

	public ArrayList<Member> selectMemberList() {
		ArrayList<Member> memberList = new ArrayList<Member>();
		try {
			String query = "select*from mms_member";
			pstmt = con.prepareStatement(query);

			rs = pstmt.executeQuery();
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
		JdbcUtil.close(pstmt);
		JdbcUtil.close(rs);

		return memberList;
	}

	public Member selectOldMember(String name) {
		Member member = null;
		try {
			String query = "select*from mms_member where name=?";
			pstmt = con.prepareStatement(query);
			pstmt.setString(1, name);

			rs = pstmt.executeQuery();
			if (rs.next()) {
				String name2 = rs.getString("name");
				String addr = rs.getString("addr");
				String nation = rs.getString("nation");
				String email = rs.getString("email");
				int age = rs.getInt("age");
				
				member = new Member(name2, addr, nation, email, age);
			}
		} catch (SQLException e) {

		}
		JdbcUtil.close(pstmt);
		JdbcUtil.close(rs);

		return member;

	}

	// 회원정보수정
	public int updateMember(Member updateMember) {
		int updateCount = 0;
		String sql = "update mms_member set addr=?, nation=?, email=?, age=?, where name=?";
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, updateMember.getAddr());
			pstmt.setString(2, updateMember.getNation());
			pstmt.setString(3, updateMember.getEmail());
			pstmt.setInt(4, updateMember.getAge());
			pstmt.setString(5, updateMember.getName());

			updateCount = pstmt.executeUpdate();

		} catch (SQLException e) {

		}
		JdbcUtil.close(pstmt);

		return updateCount;

	}

	public int deleteMember(String name) {
		int deleteCount = 0;
		String sql = "delete mms_member where name=?";
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, name);
			
			deleteCount = pstmt.executeUpdate();

		} catch (SQLException e) {

		}
		JdbcUtil.close(pstmt);

		return deleteCount;
	}

}
