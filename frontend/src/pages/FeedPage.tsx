import { useEffect, useState } from "react";
import axios from "axios";
import NewsCard from "../components/NewsCard";
import FilterBar from "../components/FilterBar";
import SearchBar from "../components/SearchBar";

interface News {
    title: string;
    summary: string;
    sentiment: string;
    press: string;
    url: string;
    dateTime: string;
}

export default function FeedPage() {
    const [newsList, setNewsList] = useState<News[]>([]);
    const [filter, setFilter] = useState("전체");
    const [keyword, setKeyword] = useState("");

    useEffect(() => {
        axios
            .get(`${import.meta.env.VITE_API_BASE}/api/news-summaries`)
            .then(res => setNewsList(res.data))
            .catch(() => alert("뉴스 목록 불러오기 실패"));
    }, []);

    const filterSentiment = (label: string) => {
        if (label.includes("5") || label.includes("4")) return "긍정";
        if (label.includes("3")) return "중립";
        if (label.includes("2") || label.includes("1")) return "부정";
        return "기타";
    };

    const matchesKeyword = (news: News) => {
        const lower = keyword.toLowerCase();
        return news.title.toLowerCase().includes(lower) || news.summary.toLowerCase().includes(lower);
    };

    const filteredNews = newsList
        .filter((n) => filter === "전체" || filterSentiment(n.sentiment) === filter)
        .filter((n) => matchesKeyword(n));

    return (
        <div className="max-w-3xl mx-auto px-4 py-8">
            <h2 className="text-2xl font-bold mb-4">📰 요약된 대선 뉴스 피드</h2>
            <FilterBar filter={filter} setFilter={setFilter} />
            <SearchBar keyword={keyword} setKeyword={setKeyword} />
            {filteredNews.length === 0 ? (
                <p className="text-gray-500">해당 조건에 맞는 뉴스가 없습니다.</p>
            ) : (
                filteredNews.map((news, idx) => (
                    <NewsCard key={idx} {...news} />
                ))
            )}
        </div>
    );
}
