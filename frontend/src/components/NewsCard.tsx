import { useNavigate } from "react-router-dom";

interface Props {
    title: string;
    summary: string;
    sentiment: string;
    press: string;
    url: string;
    dateTime: string;
}

const CANDIDATES = ["이재명", "김문수", "이준석"];

export default function NewsCard({
                                     title, summary, sentiment, press, url, dateTime
                                 }: Props) {
    const navigate = useNavigate();

    const sentimentToEmoji = (label: string) => {
        if (label.includes("5")) return "😍 매우 긍정적";
        if (label.includes("4")) return "😄 긍정적";
        if (label.includes("3")) return "😐 중립";
        if (label.includes("2")) return "😠 부정적";
        if (label.includes("1")) return "😡 매우 부정적";
        return "❓ 감성 분석 실패";
    };

    const findMentionedCandidates = () => {
        const text = `${title} ${summary}`;
        return CANDIDATES.filter(name => text.includes(name));
    };

    const mentioned = findMentionedCandidates();

    const handleCandidateClick = (name: string) => {
        navigate(`/stats/timeline?candidate=${encodeURIComponent(name)}`);
    };

    return (
        <div className="border p-4 rounded shadow mb-4 bg-white">
            <a href={url} target="_blank" className="text-lg font-bold text-blue-600 hover:underline">{title}</a>
            <p className="text-sm text-gray-500">{press} | {dateTime}</p>
            <p className="mt-2 text-gray-800 whitespace-pre-wrap">{summary}</p>

            <div className="mt-2 text-sm text-yellow-700">
                감성 분석: {sentimentToEmoji(sentiment)}
            </div>

            {mentioned.length > 0 && (
                <div className="mt-2 text-sm text-indigo-700 font-medium">
                    언급된 후보: {mentioned.map((n, i) => (
                    <button
                        key={i}
                        onClick={() => handleCandidateClick(n)}
                        className="mr-2 px-2 py-0.5 bg-indigo-100 rounded hover:bg-indigo-200 transition"
                    >
                        {n}
                    </button>
                ))}
                </div>
            )}
        </div>
    );
}
