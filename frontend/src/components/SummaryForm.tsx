import { useState } from "react";
import axios from "axios";

export default function SummaryForm() {
    const [text, setText] = useState("");
    const [summary, setSummary] = useState("");
    const [sentiment, setSentiment] = useState("");
    const [loading, setLoading] = useState(false);

    const handleSubmit = async (e: React.FormEvent) => {
        e.preventDefault();
        setLoading(true);
        setSummary("");
        setSentiment("");

        try {
            const response = await axios.post(
                `${import.meta.env.VITE_API_BASE}/api/summarize`,
                { text }
            );
            setSummary(response.data.summary);
            setSentiment(response.data.sentiment);
        } catch (error) {
            console.error("요약 에러:", error);
            setSummary("요약 요청 중 오류가 발생했습니다.");
        } finally {
            setLoading(false);
        }
    };

    const sentimentToEmoji = (label: string) => {
        if (label.includes("5")) return "😍 매우 긍정적";
        if (label.includes("4")) return "😄 긍정적";
        if (label.includes("3")) return "😐 중립";
        if (label.includes("2")) return "😠 부정적";
        if (label.includes("1")) return "😡 매우 부정적";
        return "❓ 감성 분석 실패";
    };

    return (
        <form onSubmit={handleSubmit} className="space-y-4">
            <textarea
                className="w-full h-40 p-3 border border-gray-300 rounded"
                placeholder="뉴스 본문을 입력하세요..."
                value={text}
                onChange={(e) => setText(e.target.value)}
                required
            />
            <button
                type="submit"
                className="bg-blue-600 text-white py-2 px-4 rounded hover:bg-blue-700"
                disabled={loading}
            >
                {loading ? "요약 중..." : "요약 요청"}
            </button>

            {summary && (
                <div className="mt-4 p-4 bg-gray-50 border rounded">
                    <h3 className="font-semibold mb-2">요약 결과:</h3>
                    <p className="text-gray-800 whitespace-pre-wrap">{summary}</p>

                    <div className="mt-4">
                        <h4 className="font-semibold">감성 분석 결과:</h4>
                        <span className="inline-block mt-1 px-3 py-1 bg-yellow-100 text-yellow-800 rounded">
                            {sentimentToEmoji(sentiment)}
                        </span>
                    </div>
                </div>
            )}
        </form>
    );
}
