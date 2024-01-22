import React from "react";
import styled from "styled-components";

const StyledButton = styled.button`
  background-color: ${(props) => props.theme.primaryColor};
`;

const Button = (props) => {
    return (
        <StyledButton theme={props.theme}>
            Button
        </StyledButton>
    );
}

export default Button;