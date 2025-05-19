import { BrowserRouter as Router, Route, Routes } from "react-router-dom";
import SummaryPage from "./pages/SummaryPage";
import ComparePage from "./pages/ComparePage";

export default function App() {
    return (
        <Router>
            <Routes>
                <Route path="/" element={<SummaryPage />} />
                <Route path="/compare" element={<ComparePage />} />
            </Routes>
        </Router>
    );
}
