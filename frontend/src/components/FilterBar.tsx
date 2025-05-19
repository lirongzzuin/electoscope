interface Props {
    filter: string;
    setFilter: (value: string) => void;
}

export default function FilterBar({ filter, setFilter }: Props) {
    const filters = ["전체", "긍정", "중립", "부정"];

    return (
        <div className="flex gap-2 mb-4">
            {filters.map((label) => (
                <button
                    key={label}
                    className={`px-4 py-1 rounded border text-sm ${
                        filter === label ? "bg-blue-600 text-white" : "bg-white border-gray-300"
                    }`}
                    onClick={() => setFilter(label)}
                >
                    {label}
                </button>
            ))}
        </div>
    );
}
