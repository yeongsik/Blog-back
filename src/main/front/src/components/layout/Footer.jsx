import React from "react";
import styled from "styled-components";

const StyledFooter = styled.footer`
  position: fixed;
  bottom: 0;
  background-color: bisque;
  width: 100%;
  height: 5rem;
`;

const Wrapper = styled.div`
    display: flex;
    flex-direction: row;
    align-items: center;
    justify-content: space-evenly;
`;


const Footer = (props) => {
    return (
        <StyledFooter>
            <Wrapper>
                Footer 입니다.
                {/*
                푸터에 들어갈 내용
                1. 문제 푸는 탭
                2. 토론하는 커뮤니티 탭
                3. 리그 탭
                4. ABOUT 탭
                */}
            </Wrapper>
        </StyledFooter>
    )

};

export default Footer;