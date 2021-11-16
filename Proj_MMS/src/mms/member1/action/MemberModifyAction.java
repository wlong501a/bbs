package mms.member.action;

import java.util.Scanner;

import mms.member.svc.MemberModifyService;
import mms.member.util.ConsoleUtil;
import mms.member.vo.Member;

public class MemberModifyAction implements Action {

	@Override
	public void execute(Scanner sc) throws Exception {
		//실행명령(consoleUtil)
		ConsoleUtil cu = new ConsoleUtil();
		System.out.println("수정할 회원이름을 입력하세요");
		String name = sc.next();
		String msgKind= cu.getName(name, sc);
		MemberModifyService mms = new MemberModifyService();
		Member member = mms.getOldMember(msgKind);
		Member updateMember = cu.getUpdateMember(sc, member);
		cu.printModifySuccessMessage(updateMember);
		
	}

}
