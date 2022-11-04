
// JS객체를 이용한 유효성 검사 결과 저장 객체 
// JS객체 = {"K":V, "K":V, "K":V, ...} (Map 형식)

// 변수명.key 또는 변수명["key"]를 이용하면 객체 속성 접근 가능
const checkObj={"memberEmail":false,
                "memberPw":false,
                "memberPwConfirm":false,
                "memberNickname":false,
                "memberTel":false,};

// 회원가입 양식이 제출 되었을 때 
document.getElementById("signUp-frm").addEventListener("submit",function(event){
    
    // checkObj에 속성 중 하나라도 false가 있다면 제출 이벤트 제거 
    
    // for in 구문 : 객체의 key값을 순서대로 접근하는 반복문 
    // [작성법] 
    // for(let 변수명 in 객체명)
    //          == key
    // -> 객체에서 순서대로 key를 하나씩 꺼내 왼쪽 변수에 저장 


    // checkObj 속성 하나를 꺼내 값을 검사했는데 false인 경우 
    for(key in checkObj){
        let str;

        if(!checkObj[key]){
            switch(key){
                case "memberEmail": str="이메일이 유효하지 않습니다."; break;
                case "memberPw": str="비밀번호가 유효하지 않습니다."; break;
                case "memberPwConfirm": str="비밀번호 확인이 유효하지 않습니다."; break;
                case "memberNickname": str="닉네임이 유효하지 않습니다."; break;
                case "memberTel": str="전화번호 유효하지 않습니다."; break;
            }

            alert(str); // 대화상자 출력 
            // 유효하지 않은 입력으로 포커스 이동 
            document.getElementById(key).focus();
            
            event.preventDefault(); // 제출 이벤트 제거 
            return; // 함수 종료
        }
    }

})




// 이메일 유효성 검사 
const memberEmail=document.getElementById("memberEmail");
const emailMessage=document.getElementById("emailMessage");

memberEmail.addEventListener("keyup",function(){
    
    // 문자가 입력되지 않은 경우 
    if(memberEmail.value.trim().length==0){
        emailMessage.innerText="메일을 받을 수 있는 이메일을 입력해주세요.";
        memberEmail.value="";

        // confirm, error 클래스 제거 -> 검정 글씨 만들기
        emailMessage.classList.remove("confirm","error");

        // 유효성 검사 확인 객체에 현재 상태 저장
        checkObj.memberEmail=false;
        return;
    }

    // 정규표현식을 이용한 유효성 검사 
    const regEx=/^[A-Za-z\d\-\_]{4,}@[\w\-\_]+(\.\w+){1,3}$/;

    if(regEx.test(memberEmail.value)){
        emailMessage.innerText="유효한 이메일 형식입니다.";
        emailMessage.classList.add("confirm");
        emailMessage.classList.remove("error");
        checkObj.memberEmail=true;

    }else{
        emailMessage.innerText="이메일 형식이 유효하지 않습니다.";
        emailMessage.classList.add("error");
        emailMessage.classList.remove("confirm");
        checkObj.memberEmail=false;

    }
})

