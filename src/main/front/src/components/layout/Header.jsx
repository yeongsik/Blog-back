import React from "react";
import styled from "styled-components";

const StyledHeader = styled.header`
  width: 100%;
  position: fixed;
  top: 0;
  background-color: antiquewhite;
`;

const Wrapper = styled.div`
  height: 3.5rem;
  display: flex;
  justify-content: space-between;
  align-items: center;
  
  background-color: green;
`;

const Spacer = styled.div`
  height: 3.5rem;
`;


const Header = (props) => {
    return (
        <>
            <StyledHeader>
                <Wrapper>
                    Header
                </Wrapper>
                    {/*헤더에 들어갈 내용 (회원 관련)
                        1. 로그인 버튼 / 로그아웃 버튼
                        2. 마이 페이지 버튼 (프로필 이미지 화면)
                    */}
            </StyledHeader>
            <Spacer />
        </>
    )

};

export default Header;