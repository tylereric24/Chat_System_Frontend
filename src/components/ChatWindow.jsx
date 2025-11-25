function formatTime(ts) {
  return new Date(ts).toLocaleTimeString([], {
    hour: "2-digit",
    minute: "2-digit",
  });
}

export default function ChatWindow({ messages }) {
  return (
    <div className="flex-1 overflow-y-auto p-4 space-y-3 bg-slate-900">
      {messages.map((m) => (
        <div
          key={m.id}
          className={`flex ${m.me ? "justify-end" : "justify-start"}`}
        >
          <div
            className={`max-w-xs px-3 py-2 rounded-2xl ${
              m.me ? "bg-blue-600 text-white" : "bg-slate-800"
            }`}
          >
            <div className="text-xs opacity-70 mb-1">
              {m.from} • {formatTime(m.timestamp)}
            </div>
            {m.text}
          </div>
        </div>
      ))}

      {messages.length === 0 && (
        <p className="text-xs text-slate-500 text-center pt-20">
          No messages yet.
        </p>
      )}
    </div>
  );
}
