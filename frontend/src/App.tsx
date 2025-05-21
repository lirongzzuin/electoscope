import { BrowserRouter as Router, Route, Routes } from "react-router-dom";
import HomePage from "./pages/HomePage";
import SummaryPage from "./pages/SummaryPage";
import ComparePage from "./pages/ComparePage";
import FeedPage from "./pages/FeedPage";
import StatsPage from "./pages/StatsPage";
import TimelinePage from "./pages/TimelinePage";
import AddPledgePage from "./pages/AddPledgePage";

export default function App() {
    return (
        <Router>
            <Routes>
                <Route path="/" element={<HomePage />} />
                <Route path="/summary" element={<SummaryPage />} />
                <Route path="/compare" element={<ComparePage />} />
                <Route path="/feed" element={<FeedPage />} />
                <Route path="/stats" element={<StatsPage />} />
                <Route path="/stats/timeline" element={<TimelinePage />} />
                <Route path="/add-pledge" element={<AddPledgePage />} />
            </Routes>
        </Router>
    );
}
