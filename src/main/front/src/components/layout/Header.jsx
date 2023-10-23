import React from "react";
import styled from "styled-components";
import Button from "../ui/Button";

const Wrapper = styled.div`
  height: 50px;
  width: 100%;
  max-width: 400px;
  background: green;
  display: flex;
  flex-direction: row;
  justify-content: space-between;
  align-items: center;
`;

const theme = {
    primaryColor: '#3498db',
    secondaryColor: '#2ecc71',
}


const Header = (props) => {
    return (
        <Wrapper>
            <Button theme={theme} />
            {/*헤더에 들어갈 내용 (회원 관련)
                1. 로그인 버튼 / 로그아웃 버튼
                2. 마이 페이지 버튼 (프로필 이미지 화면)
            */}
        </Wrapper>
    )

};

export default Header;