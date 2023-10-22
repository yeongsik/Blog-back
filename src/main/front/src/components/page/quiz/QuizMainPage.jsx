import React from "react";
import styled from "styled-components";

const Wrapper = styled.div`

`;

const Container = styled.div`
  
`;
const QuizMainPage = (props) => {
    return (
        <Wrapper>
            <Container>
            {/* 메인페이지 컨텐츠
                1. 오늘의 문제집
                2. 출석 체크 (최근 일주일) - Log
                3. 학습 정보
                4. 자주 틀리는 문제 개념 설명
                5. 오늘의 면접 질문 (recommend)
            */}
                퀴즈 메인 페이지..
            </Container>
        </Wrapper>
    )
};

export default QuizMainPage;