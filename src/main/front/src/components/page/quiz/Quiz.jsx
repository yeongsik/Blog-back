import React from "react";
import styled from "styled-components";

const Wrapper = styled.div`
    
`;

const Quiz = (props) => {
    return (
        <div>
            {/*
                문제 (문제의 종류가 관건...)
                객관식, 주관식에 따라 다른 폼을 보여야 한다.
                주관식도 단답형과 장문형으로 나뉠 수도 있는데
                장문의 문제의 경우 빈칸 뚫기로 단답형으로 진행해볼까 고민 중
                서버에서 받는 데이터 중 question 타입에 따라 다른 컴포넌트로 렌더링


                button List
                1. 정답 제출(
                2. 다음
                3. 개념 설명

               화면 로직
               1. 문제 렌더링 (타입 별로)
               2. 인풋 혹은 보기 선택하고 정답 제출(3번 기회)
               3. 정답일 경우 다음 버튼과 개념 설명 (내 문제집)
               4. 오답일 경우 alert , 3번 기회 다 쓴 후 다음 버튼과 개념 설명 (내 문제집)
               5. 개념 설명 누르면 해당 문제에 대한 설명 화면 페이지, 다음 문제 버튼 클릭시 다음 문제 진행

               정답과 오답일 때 로그에 기록 answer 로그

            */}
        </div>
    )
};

export default Quiz;