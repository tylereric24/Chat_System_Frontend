export default function ProtocolToggle({ value, onChange }) {
  return (
    <div className="flex items-center gap-1 text-xs bg-slate-800 rounded-full px-2 py-1">
      <button
        onClick={() => onChange("TCP")}
        className={`px-2 py-1 rounded-full ${
          value === "TCP" ? "bg-blue-600 text-white" : "text-slate-300"
        }`}
      >
        TCP
      </button>

      <button
        onClick={() => onChange("UDP")}
        className={`px-2 py-1 rounded-full ${
          value === "UDP" ? "bg-blue-600 text-white" : "text-slate-300"
        }`}
      >
        UDP
      </button>
    </div>
  );
}
