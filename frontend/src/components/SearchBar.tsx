interface Props {
    keyword: string;
    setKeyword: (value: string) => void;
}

export default function SearchBar({ keyword, setKeyword }: Props) {
    return (
        <div className="mb-4">
            <input
                type="text"
                value={keyword}
                onChange={(e) => setKeyword(e.target.value)}
                placeholder="뉴스 제목 또는 요약 내용 검색..."
                className="w-full p-2 border rounded"
            />
        </div>
    );
}
