import React from "react";
import {
    BrowserRouter,
    Routes,
    Route
} from "react-router-dom";
import styled from "styled-components";
// Pages
import Header from "./components/layout/Header";
import Footer from "./components/layout/Footer";
import Login from "./components/page/user/Login";
import QuizMainPage from "./components/page/quiz/QuizMainPage";
import QuizBook from "./components/page/quiz/QuizBook";
import Quiz from "./components/page/quiz/Quiz";
import IndexMain from "./components/page/IndexMain";
import './default.css';

const Container = styled.div`
  max-width: 420px;
  display: flex;
  flex-direction: column;
  align-items: center;
  justify-content: center;
  background-color: orange;

`;


function App() {
    return (
        <Container>
            <Header/>
            <Routes>
                <Route index element={<IndexMain isLogin={true}/>}/>

                <Route path="/login" element={<Login/>}/>
                <Route path="/quiz" element={<QuizMainPage/>}/>

                {/* 라우터 연결 해놓기 */}
                <Route path="/quiz/quiz-book" element={<QuizBook/>}/>
                <Route path="/quiz/quiz-book/:quizId" element={<Quiz/>}/>
            </Routes>
            <Footer/>
        </Container>
    );
}

export default App;
