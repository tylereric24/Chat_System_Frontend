import { useState } from "react";

export default function MessageInput({ onSend }) {
  const [text, setText] = useState("");

  function submit(e) {
    e.preventDefault();
    if (!text.trim()) return;
    onSend(text);
    setText("");
  }

  return (
    <form
      onSubmit={submit}
      className="border-t border-slate-800 px-4 py-3 flex gap-2"
    >
      <input
        className="flex-1 bg-slate-800 text-sm px-3 py-2 rounded-xl border border-slate-700"
        placeholder="Type a message..."
        value={text}
        onChange={(e) => setText(e.target.value)}
      />

      <button className="px-4 py-2 bg-blue-600 rounded-xl text-sm">
        Send
      </button>
    </form>
  );
}
