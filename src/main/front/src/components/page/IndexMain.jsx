import React from "react";
import {useNavigate} from "react-router-dom";

const IndexMain = (props) => {
    const {isLogin} = props;
    // 로그인 유무 -> 로그인 안되면 quizMainPage, 로그인 안될 시 Login
    const navigate = useNavigate();
    if(isLogin) {
        navigate("/quiz");
    } else {
        navigate("/login");
    }
}

export default IndexMain;