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
		String name= cu.getName("수정할", sc);
		MemberModifyService mms = new MemberModifyService();
		Member oldmember = mms.getOldMember(name);
		Member updateMember = cu.getUpdateMember(sc, oldmember);
		boolean isModifySuccess = mms.modifyMember(updateMember);
		if (isModifySuccess) {
			cu.printModifySuccessMessage(updateMember);
		}else {
			cu.printModifyFailMessage(updateMember);
		}
		
	}

}
