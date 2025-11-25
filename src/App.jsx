import { useState } from "react";

import ChatSidebar from "./components/ChatSidebar.jsx";
import ChatWindow from "./components/ChatWindow.jsx";
import MessageInput from "./components/MessageInput.jsx";
import StatsPanel from "./components/StatsPanel.jsx";
import ProtocolToggle from "./components/ProtocolToggle.jsx";
import ConnectionStatus from "./components/ConnectionStatus.jsx";

export default function App() {
  const [protocol, setProtocol] = useState("TCP");
  const [connected] = useState(true);
  const [messages, setMessages] = useState([]);
  const [latencyMs, setLatencyMs] = useState(null);
  const [packetLoss, setPacketLoss] = useState(0);

  function handleSend(text) {
    const now = Date.now();

    setMessages((prev) => [
      ...prev,
      { id: now, from: "You", text, me: true, timestamp: now }
    ]);

    // Mock server reply
    const delay = Math.floor(Math.random() * 150) + 50;
    setTimeout(() => {
      const rtt = Date.now() - now;
      setLatencyMs(rtt);
      setPacketLoss((prev) => Math.min(prev + 0.1, 100));

      setMessages((prev) => [
        ...prev,
        {
          id: Date.now(),
          from: "Server",
          text: `[${protocol}] Echo: ${text}`,
          me: false,
          timestamp: Date.now(),
        },
      ]);
    }, delay);
  }

  return (
    <div className="h-screen flex">
      <ChatSidebar />

      <main className="flex flex-col flex-1 border-x border-slate-800">
        <header className="px-4 py-3 border-b border-slate-800 flex items-center justify-between">
          <div>
            <h1 className="text-sm font-semibold">CS576 Chat System</h1>
            <p className="text-xs text-slate-400">TCP / UDP</p>
          </div>

          <div className="flex items-center gap-4">
            <ProtocolToggle value={protocol} onChange={setProtocol} />
            <ConnectionStatus connected={connected} />
          </div>
        </header>

        <ChatWindow messages={messages} />

        <MessageInput onSend={handleSend} />
      </main>

      <StatsPanel
        protocol={protocol}
        latencyMs={latencyMs}
        packetLoss={packetLoss}
      />
    </div>
    
  );
  
}
