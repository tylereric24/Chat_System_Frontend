export default function ChatSidebar() {
  const users = [
    { name: "Alvaro", status: "online" },
    { name: "Jose", status: "online" },
    { name: "Mauro", status: "away" },
    { name: "Tommy", status: "offline" },
    { name: "Eric", status: "online" },
  ];

  return (
    <aside className="w-64 bg-slate-950 border-r border-slate-800 flex flex-col">
      <div className="p-4 border-b border-slate-800">
        <h1 className="text-lg font-semibold">Custom Chat System</h1>
        <p className="text-xs text-slate-400">Java TCP/UDP</p>
      </div>

      <h2 className="p-4 text-xs text-slate-500 uppercase tracking-wide">
        Group Members
      </h2>

      <ul className="flex-1 overflow-y-auto">
        {users.map((u) => (
          <li
            key={u.name}
            className="px-4 py-2 flex items-center gap-2 hover:bg-slate-900"
          >
            <div
              className={`h-2 w-2 rounded-full ${
                u.status === "online"
                  ? "bg-emerald-400"
                  : u.status === "away"
                  ? "bg-yellow-400"
                  : "bg-gray-500"
              }`}
            />
            {u.name}
          </li>
        ))}
      </ul>
    </aside>
  );
}
