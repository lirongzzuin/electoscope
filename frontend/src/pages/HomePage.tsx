import { Link } from "react-router-dom";

export default function HomePage() {
    return (
        <main className="min-h-screen bg-white text-gray-800 px-4 py-10">
            <div className="max-w-4xl mx-auto text-center">
                <h1 className="text-4xl md:text-5xl font-bold mb-4">
                    ElectoScope 2025
                </h1>
                <p className="text-lg md:text-xl text-gray-600 mb-8">
                    뉴스가 너무 많아 혼란스러우신가요? <br />
                    ElectoScope가 후보별 공약, 뉴스 요약, 감성 분석까지 정리해드립니다.
                </p>

                <div className="flex flex-col md:flex-row justify-center gap-4">
                    <Link
                        to="/feed"
                        className="bg-blue-600 hover:bg-blue-700 text-white font-semibold py-3 px-6 rounded transition"
                    >
                        뉴스 요약 보기
                    </Link>
                    <Link
                        to="/compare"
                        className="bg-gray-100 hover:bg-gray-200 text-gray-800 font-medium py-3 px-6 rounded transition"
                    >
                        후보별 공약 비교하기
                    </Link>
                    <Link
                        to="/stats"
                        className="bg-yellow-100 hover:bg-yellow-200 text-yellow-800 font-medium py-3 px-6 rounded transition"
                    >
                        후보 언급 통계 보기
                    </Link>
                </div>
            </div>
        </main>
    );
}
