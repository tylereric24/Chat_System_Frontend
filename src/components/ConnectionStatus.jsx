export default function ConnectionStatus({ connected }) {
  return (
    <div className="flex items-center gap-2 text-xs">
      <div
        className={`h-2 w-2 rounded-full ${
          connected ? "bg-emerald-400" : "bg-red-500"
        }`}
      />
      {connected ? "Connected" : "Disconnected"}
    </div>
  );
}
