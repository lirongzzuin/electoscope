import { useState } from "react";
import axios from "axios";

export default function SummaryForm() {
    const [text, setText] = useState("");
    const [summary, setSummary] = useState("");
    const [loading, setLoading] = useState(false);

    const handleSubmit = async (e: React.FormEvent) => {
        e.preventDefault();
        setLoading(true);
        setSummary("");

        try {
            const response = await axios.post("http://localhost:8080/api/summarize", {
                text,
            });
            setSummary(response.data.summary);
        } catch (error) {
            setSummary("요약 요청 중 오류가 발생했습니다.");
        } finally {
            setLoading(false);
        }
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
                </div>
            )}
        </form>
    );
}
