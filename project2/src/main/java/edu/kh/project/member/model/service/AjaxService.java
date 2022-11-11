package edu.kh.project.member.model.service;

import java.util.List;

import edu.kh.project.member.model.vo.Member;

// 서비스 인터페이스 왜 만들었지?
// 설계, 유지보수성 향상, AOP 때문에 
public interface AjaxService {

	int emailDupCheck(String memberEmail);


	int nicknameDupCheck(String memberNickname);


	Member selectEmail(String email);


	List<Member> selectMemberList();

}
