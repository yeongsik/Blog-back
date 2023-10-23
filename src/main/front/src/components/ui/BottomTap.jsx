import React from "react";
import styled from "styled-components";


const StyledTap = styled.div`
  border: 1px solid white;  
  width: 100%;
  height: 100%;
  text-align: center;
`;

const BottomTap = (props) => {

    const {icon, title, onClick} = props;
    return (
        <StyledTap onClick={onClick}>
            {icon}
        </StyledTap>
    );
}

export default BottomTap;