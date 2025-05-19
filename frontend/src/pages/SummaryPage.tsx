import SummaryForm from "../components/SummaryForm";

export default function SummaryPage() {
    return (
        <div className="max-w-3xl mx-auto px-4 py-8">
            <h2 className="text-2xl font-bold mb-4">뉴스 본문 요약</h2>
            <SummaryForm />
        </div>
    );
}
