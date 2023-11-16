import React from "react";
import {
    Routes,
    Route
} from "react-router-dom";
import QuizMainPage from "../components/page/quiz/QuizMainPage";
import QuizBook from "../components/page/quiz/QuizBook";
import Quiz from "../components/page/quiz/Quiz";

const MainRoutes = () => {
    <Routes>
        {/*<Route index element={<MainPage />} />*/}
        <Route path="/" element={<QuizMainPage />} />

        {/* 라우터 연결 해놓기 */}
        <Route path="/quiz/quiz-book" element={<QuizBook />} />
        <Route path="/quiz/quiz-book/:quizId" element={<Quiz />} />
    </Routes>
}

export default MainRoutes;