import { useState } from "react";
import axios from "axios";
import { useNavigate } from "react-router-dom";

export default function AddPledgePage() {
    const [candidate, setCandidate] = useState("");
    const [category, setCategory] = useState("");
    const [content, setContent] = useState("");
    const [message, setMessage] = useState("");
    const navigate = useNavigate();

    const handleSubmit = async (e: React.FormEvent) => {
        e.preventDefault();

        try {
            await axios.post(`${import.meta.env.VITE_API_BASE}/api/pledges`, {
                candidate,
                category,
                content,
            });

            setMessage("공약이 성공적으로 등록되었습니다!");
            setTimeout(() => navigate("/compare"), 1500);
        } catch (error) {
            setMessage("❌ 등록 중 오류가 발생했습니다.");
        }
    };

    return (
        <div className="max-w-lg mx-auto px-4 py-8">
            <h2 className="text-2xl font-bold mb-6">📝 공약 수동 등록</h2>

            <form onSubmit={handleSubmit} className="space-y-4">
                <input
                    type="text"
                    placeholder="후보 이름"
                    value={candidate}
                    onChange={(e) => setCandidate(e.target.value)}
                    className="w-full border p-2 rounded"
                    required
                />
                <input
                    type="text"
                    placeholder="카테고리 (예: 청년, 경제, 복지)"
                    value={category}
                    onChange={(e) => setCategory(e.target.value)}
                    className="w-full border p-2 rounded"
                    required
                />
                <textarea
                    placeholder="공약 내용"
                    value={content}
                    onChange={(e) => setContent(e.target.value)}
                    className="w-full border p-2 rounded h-32"
                    required
                />
                <button
                    type="submit"
                    className="bg-blue-600 text-white px-4 py-2 rounded hover:bg-blue-700"
                >
                    등록하기
                </button>
                {message && <p className="text-sm mt-2">{message}</p>}
            </form>
        </div>
    );
}
