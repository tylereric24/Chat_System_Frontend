export default function StatsPanel({ protocol, latencyMs, packetLoss }) {
  return (
    <aside className="w-72 bg-slate-950 border-l border-slate-800 p-4 space-y-4">
      <h2 className="text-sm font-semibold">Performance</h2>

      <div className="text-sm">
        <div className="flex justify-between">
          <span className="text-slate-400">Protocol</span>
          <span>{protocol}</span>
        </div>

        <div className="flex justify-between">
          <span className="text-slate-400">Latency</span>
          <span>{latencyMs == null ? "—" : `${latencyMs} ms`}</span>
        </div>

        <div className="flex justify-between">
          <span className="text-slate-400">Packet Loss</span>
          <span>{packetLoss.toFixed(1)}%</span>
        </div>
      </div>

      <div className="text-xs text-slate-400">
        (Graph placeholder — add later)
        <div className="h-24 bg-slate-900 mt-2 rounded-xl border border-slate-800 flex items-center justify-center">
          Chart here
        </div>
      </div>
    </aside>
  );
}
