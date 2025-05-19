import { useEffect, useState } from "react";
import axios from "axios";

interface Pledge {
    candidate: string;
    category: string;
    content: string;
}

const categories = ["전체", "경제", "복지", "청년", "외교"];

export default function ComparePage() {
    const [data, setData] = useState<Pledge[]>([]);
    const [selectedCategory, setSelectedCategory] = useState("전체");

    useEffect(() => {
        axios.get<Pledge[]>("http://localhost:8080/api/pledges")
            .then(res => setData(res.data))
            .catch(() => alert("공약 데이터를 불러오지 못했습니다."));
    }, []);

    const filtered = selectedCategory === "전체"
        ? data
        : data.filter(p => p.category === selectedCategory);

    const grouped = filtered.reduce((acc: Record<string, Pledge[]>, cur) => {
        if (!acc[cur.category]) acc[cur.category] = [];
        acc[cur.category].push(cur);
        return acc;
    }, {});

    return (
        <div className="max-w-4xl mx-auto px-4 py-8">
            <h2 className="text-2xl font-bold mb-6">📋 후보별 공약 비교</h2>

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
                        </tr>
                        </thead>
                        <tbody>
                        {items.map((p, idx) => (
                            <tr key={idx}>
                                <td className="border p-2 text-center">{p.candidate}</td>
                                <td className="border p-2">{p.content}</td>
                            </tr>
                        ))}
                        </tbody>
                    </table>
                </div>
            ))}
        </div>
    );
}
