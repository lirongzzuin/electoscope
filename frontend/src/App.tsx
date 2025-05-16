export default function App() {
    return (
        <main className="min-h-screen bg-white text-gray-800 px-4 py-10">
            <div className="max-w-4xl mx-auto text-center">
                <h1 className="text-4xl md:text-5xl font-bold mb-4">
                    21대 대통령 선거 요약 플랫폼
                </h1>
                <p className="text-lg md:text-xl text-gray-600 mb-8">
                    뉴스가 너무 많아서 혼란스러우신가요?<br />
                    ElectoScope가 주요 후보들의 공약과 뉴스 흐름을 요약해드립니다.
                </p>

                <div className="flex flex-col md:flex-row justify-center gap-4">
                    <a
                        href="#summary"
                        className="bg-blue-600 hover:bg-blue-700 text-white font-semibold py-3 px-6 rounded transition"
                    >
                        오늘의 뉴스 요약 보기
                    </a>
                    <a
                        href="#compare"
                        className="bg-gray-100 hover:bg-gray-200 text-gray-800 font-medium py-3 px-6 rounded transition"
                    >
                        후보별 공약 비교하기
                    </a>
                </div>
            </div>
        </main>
    );
}