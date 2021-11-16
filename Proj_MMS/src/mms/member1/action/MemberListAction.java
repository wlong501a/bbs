package mms.member.action;

import java.util.ArrayList;
import java.util.Scanner;

import mms.member.svc.MemberListService;
import mms.member.util.ConsoleUtil;
import mms.member.vo.Member;

public class MemberListAction implements Action {

	@Override
	public void execute(Scanner sc) throws Exception {
		//실행명령(consoleUtil)
		ConsoleUtil cu = new ConsoleUtil();
		MemberListService mls = new MemberListService();
		ArrayList<Member> memberList = mls.getMemberList();
		cu.printMemberList(memberList);

	}

}
