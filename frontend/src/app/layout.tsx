import type { Metadata } from "next";
import "./styles.css";
export const metadata: Metadata = { title: "Clash Coach AI", description: "Explainable Clash of Clans progression coaching" };
export default function RootLayout({ children }: Readonly<{ children: React.ReactNode }>) { return <html lang="en"><body>{children}</body></html>; }
