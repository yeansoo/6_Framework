package edu.kh.project.member.model.service;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import edu.kh.project.member.model.dao.MyPageDAO;
import edu.kh.project.member.model.vo.Member;

@Service // bean 등록 
public class MyPageServiceImpl implements MyPageService{
	
	@Autowired // DI 
	private MyPageDAO dao;
	
	@Autowired
	private BCryptPasswordEncoder bcrypt;

	// 회원정보 수정 서비스
	@Transactional
	@Override
	public int updateInfo(Member inputMember) {
		int result=dao.updateInfo(inputMember);
		return result;
	}

	// 비밀번호 변경 서비스 
	@Override
	public int changePw(Map<String, Object> paramMap) {
		// 비밀번호 일치 시 새 비밀번호로 변경 
		
		// 1. 회원번호를 이용해서 DB에서 암호화된 비밀번호를 조회
		String encPw=dao.selectEncPw((int)paramMap.get("memberNo"));
		
		// 2. matches(입력PW, 암호화 PW) matches 결과가 true인 경우 
		if(bcrypt.matches((String)paramMap.get("currentPw"), encPw)) {
			// 새 비밀번호 암호화 
			String newPw=bcrypt.encode((String)paramMap.get("newPw"));
			
			paramMap.put("newPw", newPw);
			//Param맴베 존재하는 기존 newPw를 덮어쓰기 
			
			
			// 새 비밀번호로 UPDATE하는 DAO코드 호출 
			int result=dao.changePw(paramMap);
			
			return result;
		}
		
		
		
		return 0;// 비밀번호 불일치시 0 반환 
	}

	
	// 회원 탈퇴 서비스 
	@Transactional
	@Override
	public int memberDelete(int memberNo, String memberPw) {
		// 비밀번호 일치 시 탈퇴 
		
		// 1. 회원번호를 이용해서 DB에서 암호화된 비밀번호를 조회
		String encPw=dao.selectEncPw(memberNo);
		
		// 2. matches(입력PW, 암호화 PW) matches 결과가 true인 경우 
		if(bcrypt.matches(memberPw, encPw)) {
			
			// 회원 상태(MEMBER_DEL_FL)를 바꿈
			int result=dao.changeDelFl(memberNo);
			
			return result;
		}
		
		return 0;// 비밀번호 불일치시 0 반환 
		
	}
}
