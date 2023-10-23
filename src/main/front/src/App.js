import React from "react";
import {
    BrowserRouter,
    Routes,
    Route
} from "react-router-dom";

import MainRoutes from "./routes/MainRoutes";
import styled from "styled-components";
// Pages
import Header from "./components/layout/Header";
import Footer from "./components/layout/Footer";
import Login from "./components/page/user/Login";
import QuizMainPage from "./components/page/quiz/QuizMainPage";
import QuizBook from "./components/page/quiz/QuizBook";
import Quiz from "./components/page/quiz/Quiz";



const Container = styled.div`
  max-width: 420px;
  display: flex;
  flex-direction: column;
  align-items: center;
  justify-content: center;
`;


function App() {
    document.title = 'Qdev';

  return (
    <BrowserRouter>
        <Header />
        <Routes>
            {/*<Route index element={<MainPage />} />*/}
            <Route path="/" element={<QuizMainPage />} />

            {/* 라우터 연결 해놓기 */}
            <Route path="/quiz/quiz-book" element={<QuizBook />} />
            <Route path="/quiz/quiz-book/:quizId" element={<Quiz />} />
        </Routes>
        <Footer />
    </BrowserRouter>
  );
}

export default App;
