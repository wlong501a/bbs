package mms.member.action;

import java.util.Scanner;

import mms.member.svc.MemberAddService;
import mms.member.util.ConsoleUtil;
import mms.member.vo.Member;

public class MemberAddAction implements Action {

	@Override
	public void execute(Scanner sc) throws Exception {
		//실행명령(consoleUtil)
		ConsoleUtil cu = new ConsoleUtil();
		Member member = cu.getNewMember(sc);
		MemberAddService mas = new MemberAddService();
		boolean bool = mas.addMember(member);
		if(bool) {
			cu.printAddSuccessMessage(member);
		}else {
			cu.printAddFailMessage(member);
			System.out.println("test");
		
		}
	}

}
