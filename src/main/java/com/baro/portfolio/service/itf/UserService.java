package com.baro.portfolio.service.itf;

import com.baro.portfolio.domain.Account;
import com.baro.portfolio.web.dto.EditUserDto;
import com.baro.portfolio.web.dto.SignInDto;
import com.baro.portfolio.web.dto.SignUpDto;
import com.baro.portfolio.web.dto.result.UserInfo;

import java.util.Optional;

public interface UserService {

    /**
     * 회원 정보 등록
     *
     * @param dto   가입할 회원의 정보
     */
    void signUp(SignUpDto dto);

    /**
     * 이메일 중복 확인
     *
     * @param email 확인하고자 하는 이메일
     * @return      중복 여부(true=중복)
     */
    boolean isEmailDuplicated(String email);

    /**
     * 닉네임 중복 확인
     * 닉네임의 경우 수정이 가능하고, 현재 닉네임과 입력된 닉네임이 같으면 false 반환
     *
     * @param nickname          확인하고자 하는 닉네임
     * @param currentNickname   현재 닉네임, 최초 시도인 경우 빈 문자열
     * @return                  중복 여부 반환
     */
    boolean isNicknameDuplicated(String nickname, String currentNickname);

    /**
     * 핸드폰 번호 중복 확인
     *
     * @param phone     확인하고자 하는 번호
     * @return          중복 여부 반환
     */
    boolean isPhoneDuplicated(String phone);

    /**
     * 로그인 시 입력한 이메일과 비밀 번호에 해당하는 계정 반환
     *
     * @param dto   이메일과 비밀 번호 정보
     * @return      성공 시, 세션에 정보를 넣기 위해 Optional을 반환
     */
    Optional<Account> findByEmailAndPassword(SignInDto dto);

    /**
     * 회원 식별자로 회원 정보를 반환
     *
     * @param userSeq   회원의 식별자
     * @return          해당 회원의 닉네임과 식별자 정보 반환
     */
    UserInfo findBySeq(int userSeq);

    /**
     * 회원가 수정할 폼에 담을 기존 정보 반환
     *
     * @param userSeq   해당 회원 식별자
     * @return          수정 가능한 기존 정보
     */
    EditUserDto findEditUserInfoBySeq(int userSeq);

    /**
     * 회원 정보 수정
     *
     * @param userSeq   해당 회원 식별자
     * @param dto       수정할 정보
     */
    void updateBySeq(int userSeq, EditUserDto dto);
}
