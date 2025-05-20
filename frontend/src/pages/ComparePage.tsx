import { useEffect, useState } from "react";
import axios from "axios";

interface Pledge {
    candidate: string;
    category: string;
    content: string;
    sentiment: string;
}

const categories = ["전체", "경제", "복지", "청년", "외교"];

export default function ComparePage() {
    const [data, setData] = useState<Pledge[]>([]);
    const [selectedCategory, setSelectedCategory] = useState("전체");

    useEffect(() => {
        axios.get<Pledge[]>("http://localhost:8080/api/pledges")
            .then(res => setData(res.data));
    }, []);

    const filtered = selectedCategory === "전체"
        ? data
        : data.filter(p => p.category === selectedCategory);

    const grouped = filtered.reduce((acc: Record<string, Pledge[]>, cur) => {
        if (!acc[cur.category]) acc[cur.category] = [];
        acc[cur.category].push(cur);
        return acc;
    }, {});

    const sentimentBadge = (sentiment: string) => {
        const base = "px-2 py-0.5 rounded text-xs font-medium";
        switch (sentiment) {
            case "긍정": return <span className={`${base} bg-green-100 text-green-800`}>긍정</span>;
            case "부정": return <span className={`${base} bg-red-100 text-red-800`}>부정</span>;
            case "중립": return <span className={`${base} bg-gray-100 text-gray-800`}>중립</span>;
            default: return <span className={`${base} bg-yellow-100 text-yellow-800`}>분석 실패</span>;
        }
    };

    return (
        <div className="max-w-4xl mx-auto px-4 py-8">
            {/* 제목과 수동 등록 버튼 함께 배치 */}
            <div className="flex justify-between items-center mb-6">
                <h2 className="text-2xl font-bold">📋 후보별 공약 비교</h2>
                <a
                    href="/add-pledge"
                    className="text-sm text-blue-600 hover:underline"
                >
                    + 공약 수동 등록
                </a>
            </div>

            <div className="flex gap-2 mb-6">
                {categories.map((c) => (
                    <button
                        key={c}
                        onClick={() => setSelectedCategory(c)}
                        className={`px-4 py-1 rounded border text-sm ${
                            selectedCategory === c ? "bg-blue-600 text-white" : "bg-white border-gray-300"
                        }`}
                    >
                        {c}
                    </button>
                ))}
            </div>

            {Object.entries(grouped).map(([cat, items]) => (
                <div key={cat} className="mb-6">
                    <h3 className="text-xl font-semibold mb-2">{cat} 분야</h3>
                    <table className="w-full border text-sm">
                        <thead>
                        <tr className="bg-gray-100">
                            <th className="border p-2">후보</th>
                            <th className="border p-2">공약 내용</th>
                            <th className="border p-2">감성</th>
                        </tr>
                        </thead>
                        <tbody>
                        {items.map((p, idx) => (
                            <tr key={idx}>
                                <td className="border p-2 text-center">{p.candidate}</td>
                                <td className="border p-2">{p.content}</td>
                                <td className="border p-2 text-center">{sentimentBadge(p.sentiment)}</td>
                            </tr>
                        ))}
                        </tbody>
                    </table>
                </div>
            ))}
        </div>
    );
}
