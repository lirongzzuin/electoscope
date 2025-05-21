import { useEffect, useState } from "react";
import axios from "axios";
import {
    BarChart, Bar, XAxis, YAxis, Tooltip, ResponsiveContainer, CartesianGrid
} from "recharts";

export default function StatsPage() {
    type CandidateMention = {
        name: string;
        count: number;
    };

    const [data, setData] = useState<CandidateMention[]>([]);

    useEffect(() => {
        axios
            .get<Record<string, number>>(`${import.meta.env.VITE_API_BASE}/api/stats/mentions`)
            .then((res) => {
                const transformed: CandidateMention[] = Object.entries(res.data).map(([name, count]) => ({
                    name,
                    count,
                }));
                setData(transformed);
            })
            .catch(() => {
                alert("후보 언급량 통계를 불러오지 못했습니다.");
                setData([]);
            });
    }, []);

    return (
        <div className="max-w-3xl mx-auto px-4 py-8">
            <h2 className="text-2xl font-bold mb-6">📊 후보별 언급량 통계</h2>
            <ResponsiveContainer width="100%" height={300}>
                <BarChart data={data}>
                    <CartesianGrid strokeDasharray="3 3" />
                    <XAxis dataKey="name" />
                    <YAxis />
                    <Tooltip />
                    <Bar dataKey="count" fill="#4f46e5" />
                </BarChart>
            </ResponsiveContainer>
        </div>
    );
}
