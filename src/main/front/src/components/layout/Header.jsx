import React from "react";
import styled from "styled-components";

const Wrapper = styled.div`
  
`;

const Container = styled.div`

`;


const Header = (props) => {
    return (
        <Wrapper>
            <Container>
                Header
                {/*헤더에 들어갈 내용 (회원 관련)
                    1. 로그인 버튼 / 로그아웃 버튼
                    2. 마이 페이지 버튼 (프로필 이미지 화면)
                */}
            </Container>
        </Wrapper>
    )

};

export default Header;