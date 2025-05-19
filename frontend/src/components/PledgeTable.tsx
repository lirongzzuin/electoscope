import pledges from "../data/pledges.json";

export default function PledgeTable() {
    return (
        <div className="overflow-x-auto">
            {Object.entries(pledges).map(([category, data]) => (
                <div key={category} className="mb-6">
                    <h3 className="text-xl font-bold mb-2">{category.toUpperCase()}</h3>
                    <table className="min-w-full border text-sm text-left">
                        <thead>
                        <tr className="bg-gray-100">
                            <th className="p-2 border">후보</th>
                            <th className="p-2 border">공약</th>
                        </tr>
                        </thead>
                        <tbody>
                        {Object.entries(data).map(([candidate, pledge]) => (
                            <tr key={candidate}>
                                <td className="p-2 border font-medium">{candidate}</td>
                                <td className="p-2 border">{pledge}</td>
                            </tr>
                        ))}
                        </tbody>
                    </table>
                </div>
            ))}
        </div>
    );
}
