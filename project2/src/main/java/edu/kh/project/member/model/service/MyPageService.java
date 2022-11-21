package edu.kh.project.member.model.service;

import java.util.Map;

import org.springframework.web.multipart.MultipartFile;

import edu.kh.project.member.model.vo.Member;

// 설계적 + 유지보수 + AOP 
public interface MyPageService {

	/** 회원정보 수정 서비스 
	 * @param inputMember
	 * @return result
	 */
	int updateInfo(Member inputMember);

	/** 비밀번호 수정 서비스
	 * @param paramMap
	 * @return result
	 */
	int changePw(Map<String, Object> paramMap);

	/** 회원 탈퇴 서비스 
	 * @param memberNo
	 * @param memberPw
	 * @return result
	 */
	int memberDelete(int memberNo, String memberPw);

	/**
	 * @param webPath
	 * @param filePath
	 * @param profileImage
	 * @param loginMember
	 * @return
	 */
	int updateProfile(String webPath, String filePath, MultipartFile profileImage, Member loginMember) throws Exception;
	
}
