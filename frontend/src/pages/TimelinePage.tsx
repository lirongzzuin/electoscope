import { useEffect, useState } from "react";
import { useSearchParams } from "react-router-dom";
import axios from "axios";
import {
    LineChart, Line, XAxis, YAxis, CartesianGrid, Tooltip, Legend, ResponsiveContainer
} from "recharts";
import NewsCard from "../components/NewsCard";

interface DailyData {
    date: string;
    [candidate: string]: any;
}

const CANDIDATES = [
    { name: "이재명", color: "#3b82f6" },
    { name: "김문수", color: "#f97316" },
    { name: "이준석", color: "#10b981" }
];

interface News {
    title: string;
    summary: string;
    sentiment: string;
    press: string;
    url: string;
    dateTime: string;
}

export default function TimelinePage() {
    const [searchParams] = useSearchParams();
    const preselected = searchParams.get("candidate");

    const [data, setData] = useState<DailyData[]>([]);
    const [filtered, setFiltered] = useState<DailyData[]>([]);
    const [range, setRange] = useState<"7" | "30">("7");
    const [visibleCandidates, setVisibleCandidates] = useState<string[]>(
        preselected ? [preselected] : CANDIDATES.map(c => c.name)
    );
    const [selectedDate, setSelectedDate] = useState<string | null>(null);
    const [newsList, setNewsList] = useState<News[]>([]);

    useEffect(() => {
        axios
            .get(`${import.meta.env.VITE_API_BASE}/api/stats/mentions/daily`)
            .then((res) => {
                const raw = res.data;
                const dateMap: { [date: string]: any } = {};

                Object.entries(raw).forEach(([candidate, dateCounts]) => {
                    Object.entries(dateCounts as Record<string, number>).forEach(([date, count]) => {
                        if (!dateMap[date]) dateMap[date] = { date };
                        dateMap[date][candidate] = count;
                    });
                });

                const sorted = Object.values(dateMap).sort((a: any, b: any) => a.date.localeCompare(b.date));
                setData(sorted);
            });
    }, []);

    useEffect(() => {
        const days = Number(range);
        const limitDate = new Date();
        limitDate.setDate(limitDate.getDate() - days);

        const filteredData = data.filter(d => {
            const dDate = new Date(d.date);
            return dDate >= limitDate;
        });

        setFiltered(filteredData);
    }, [data, range]);

    const toggleCandidate = (name: string) => {
        setVisibleCandidates((prev) =>
            prev.includes(name)
                ? prev.filter(n => n !== name)
                : [...prev, name]
        );
    };

    const handleDateClick = (date: string) => {
        setSelectedDate(date);
        axios
            .get<News[]>(`${import.meta.env.VITE_API_BASE}/api/news-summaries`)
            .then(res => {
                const filteredNews = res.data.filter(n => n.dateTime.startsWith(date));
                setNewsList(filteredNews);
            });
    };

    return (
        <div className="max-w-5xl mx-auto px-4 py-8">
            <h2 className="text-2xl font-bold mb-6">📈 날짜별 후보 언급량 추이</h2>

            <div className="mb-4 flex gap-2">
                <button
                    className={`px-4 py-1 rounded border text-sm ${range === "7" ? "bg-blue-600 text-white" : "bg-white border-gray-300"}`}
                    onClick={() => setRange("7")}
                >
                    최근 7일
                </button>
                <button
                    className={`px-4 py-1 rounded border text-sm ${range === "30" ? "bg-blue-600 text-white" : "bg-white border-gray-300"}`}
                    onClick={() => setRange("30")}
                >
                    최근 30일
                </button>
            </div>

            <div className="mb-4 flex gap-4">
                {CANDIDATES.map(({ name, color }) => (
                    <label key={name} className="flex items-center gap-1 text-sm">
                        <input
                            type="checkbox"
                            checked={visibleCandidates.includes(name)}
                            onChange={() => toggleCandidate(name)}
                        />
                        <span style={{ color }}>{name}</span>
                    </label>
                ))}
            </div>

            <ResponsiveContainer width="100%" height={350}>
                <LineChart data={filtered} onClick={(e: any) => {
                    if (e && e.activeLabel) handleDateClick(e.activeLabel);
                }}>
                    <CartesianGrid strokeDasharray="3 3" />
                    <XAxis dataKey="date" />
                    <YAxis />
                    <Tooltip />
                    <Legend />
                    {CANDIDATES.filter(c => visibleCandidates.includes(c.name)).map((c) => (
                        <Line key={c.name} type="monotone" dataKey={c.name} stroke={c.color} />
                    ))}
                </LineChart>
            </ResponsiveContainer>

            {selectedDate && (
                <div className="mt-8">
                    <h3 className="text-xl font-semibold mb-4">📰 {selectedDate} 뉴스 요약</h3>
                    {newsList.length === 0 ? (
                        <p className="text-gray-500">해당 날짜의 뉴스가 없습니다.</p>
                    ) : (
                        newsList.map((news, idx) => (
                            <NewsCard key={idx} {...news} />
                        ))
                    )}
                </div>
            )}
        </div>
    );
}
