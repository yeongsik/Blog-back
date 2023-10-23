import React from "react";
import styled from "styled-components";
import BottomTap from "../ui/BottomTap";

const Wrapper = styled.footer`
  position: fixed;
  bottom: 0;
  left: 0;
  width: 100%;
  background-color: #333;
  color:white;
  padding: 10px;
  height: 50px;
  display: flex;
  justify-content: space-around;
  align-items: center;
`;
  

const Container = styled.div`
    
`;


const Footer = (props) => {
    return (
        <Wrapper>
            <BottomTap icon="퀴즈" onClick={() => {
                alert("퀴즈");
            }} />
            <BottomTap icon="리그" onClick={() => {
                alert("리그");
            }} />
            <BottomTap icon="토론" onClick={() => {
                alert("토론");
            }} />

                {/*
                푸터에 들어갈 내용
                1. 문제 푸는 탭
                2. 토론하는 커뮤니티 탭
                3. 리그 탭
                4. ABOUT 탭
                */}
        </Wrapper>
    )

};

export default Footer;