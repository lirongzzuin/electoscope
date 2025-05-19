import PledgeTable from "../components/PledgeTable";

export default function ComparePage() {
    return (
        <div className="max-w-4xl mx-auto px-4 py-8">
            <h2 className="text-2xl font-bold mb-6">후보별 공약 비교</h2>
            <PledgeTable />
        </div>
    );
}
