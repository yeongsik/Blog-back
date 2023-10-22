import React from "react";
import {
  BrowserRouter,
  Routes,
  Route
} from "react-router-dom";
import styled from "styled-components";
// Pages
import MainPage from "./components/example/page/MainPage";
import PostWritePage from "./components/example/page/PostWritePage";
import PostViewPage from "./components/example/page/PostViewPage";
import Header from "./components/layout/Header";
import Footer from "./components/layout/Footer";
import Login from "./components/page/user/Login";
import QuizMainPage from "./components/page/quiz/QuizMainPage";
import QuizBook from "./components/page/quiz/QuizBook";
import Quiz from "./components/page/quiz/Quiz";



const Container = styled.div`
  padding: 16px;
  width: calc(100% - 32px);
  max-width: 420px;
  display: flex;
  flex-direction: column;
  align-items: center;
  justify-content: center;
`;


function App() {
  return (
    <BrowserRouter>
        <Container>
            <Header />
            <Routes>
                <Route index element={<MainPage />} />
                <Route path="quiz" element={<QuizMainPage />} />

                {/* 라우터 연결 해놓기 */}
                <Route path="quiz/quiz-book" element={<QuizBook />} />
                <Route path="quiz/quiz-book/:quizId" element={<Quiz />} />



            </Routes>
            <Footer />
        </Container>
    </BrowserRouter>
  );
}

export default App;
